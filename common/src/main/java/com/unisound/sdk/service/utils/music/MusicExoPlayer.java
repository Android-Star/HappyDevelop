package com.unisound.sdk.service.utils.music;

import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.SurfaceView;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.DefaultSsChunkSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoListener;
import com.unisound.sdk.service.utils.ContextUtils;
import com.unisound.sdk.service.utils.LogMgr;
import com.unisound.sdk.service.utils.http.ResponseCallBack;
import com.unisound.sdk.service.utils.kar.basebean.KarBaseResponse;
import com.unisound.sdk.service.utils.kar.menu.AudioHttpUtils;
import com.unisound.sdk.service.utils.kar.menu.response.AlbumContentResponse;
import com.unisound.sdk.service.utils.kar.menu.response.GetDataLinkResponse;
import com.unisound.sdk.service.utils.nlu.AudioResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MusicExoPlayer {
  private static final String TAG = "MusicExoPlayer";
  private boolean musicPause = false;
  private static MusicExoPlayer musicExoPlayer = new MusicExoPlayer();
  private SimpleExoPlayer simpleExoPlayer;
  private DefaultTrackSelector trackSelector;
  private DataSource.Factory mediaDataSourceFactory;
  private DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
  private ExoPlayStateListener exoPlayStateListener = new ExoPlayStateListener();
  private Object tag;
  private int playIndex = 0;
  private PlayMode playMode = PlayMode.MODE_ONCE;
  private String playTag = null;
  private List dataSource = new ArrayList();
  private int resourceType; //1 视频 2 音频 3 视频和音频
  private AudioResult audioResult;
  public static final int STATE_IDLE = Player.STATE_IDLE;

  public static final int STATE_BUFFERING = Player.STATE_BUFFERING;

  public static final int STATE_READY = Player.STATE_READY;

  public static final int STATE_ENDED = Player.STATE_ENDED;

  public static final int STATE_ERROR = 5;

  private static final int VOLUME_COUNT = 16;
  private int nowCount = 0;
  private float volumeNow = 0;
  private float toVolume = 0;
  private MediaSource mediaSource;
  private PlayState lastPlayState = PlayState.STATE_IDLE;

  private MusicExoPlayer() {
    TrackSelection.Factory videoTrackSelectionFactory =
        new AdaptiveTrackSelection.Factory(bandwidthMeter);
    trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
    simpleExoPlayer =
        ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(ContextUtils.getContext()),
            trackSelector, new PlayerLoadControl());
    mediaDataSourceFactory = buildDataSourceFactory(true);
    simpleExoPlayer.setVolume(1.0f);
    simpleExoPlayer.addListener(exoPlayStateListener);
    int streamType = AudioManager.STREAM_MUSIC;
    int usage = Util.getAudioUsageForStreamType(streamType);
    int contentType = Util.getAudioContentTypeForStreamType(streamType);
    AudioAttributes audioAttributes =
        new AudioAttributes.Builder().setUsage(usage).setContentType(contentType).build();
    simpleExoPlayer.setAudioAttributes(audioAttributes);
    exoPlayStateListener.addPlayStateListener(new PlayStateListener() {
      @Override public void onPlayStateChanged(PlayState playState, String playTag) {
        if (lastPlayState == PlayState.STATE_ENDED && playState == PlayState.STATE_ENDED) {
          return;
        }
        if (dataSource == null || dataSource.size() == 0) {
          return;
        }
        lastPlayState = playState;
        if (playState == PlayState.STATE_ENDED) {
          LogMgr.d(TAG, "play end play mode:" + playMode);
          if (playMode == PlayMode.MODE_ONCE) {
            LogMgr.d(TAG, "play mode:" + playMode);
          }
          if (playMode == PlayMode.MODE_SINGLE) {
            musicExoPlayer.play(playIndex);
          }
          if (playMode == PlayMode.MODE_RANDOM) {
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % dataSource.size();
            if (index == playIndex) {
              index = (playIndex + 1) % dataSource.size();
            }
            musicExoPlayer.play(index);
          }
          if (playMode == PlayMode.MODE_ORDER) {
            playIndex = (playIndex + 1) % dataSource.size();
            musicExoPlayer.play(playIndex);
          }
          if (playMode == PlayMode.MODE_SINGLE_ORDER) {
            if (playIndex + 1 < dataSource.size()) {
              playIndex = (playIndex + 1) % dataSource.size();
              musicExoPlayer.play(playIndex);
            } else {
              LogMgr.d(TAG, "MODE_SINGLE_ORDER play end:" + playIndex);
            }
          }
        }
      }
    });
  }

  public Object getTag() {
    return tag;
  }

  public void setTag(Object tag) {
    this.tag = tag;
  }

  public int getResourceType() {
    return resourceType;
  }

  public void setResourceType(int resourceType) {
    this.resourceType = resourceType;
  }

  public AudioResult getAudioResult() {
    return audioResult;
  }

  public void setAudioResult(AudioResult audioResult) {
    this.audioResult = audioResult;
  }

  public static MusicExoPlayer getInstance() {
    return musicExoPlayer;
  }

  public void play(List<String> urls, boolean playWhenReady) {
    LogMgr.d(TAG, "urls:" + Arrays.asList(urls) + "playWhenReady:" + playWhenReady);
    dataSource.clear();
    dataSource.addAll(urls);
    this.audioResult = null;
    playIndex = 0;
    prepare(playIndex, playWhenReady, true, true);
  }

  public int getPlayIndex() {
    return playIndex;
  }

  public void setPlayIndex(int playIndex) {
    this.playIndex = playIndex;
  }

  public void play(String url, boolean playWhenReady) {
    LogMgr.d(TAG, "url:" + url + "playWhenReady:" + playWhenReady);
    dataSource.clear();
    dataSource.add(url);
    this.audioResult = null;
    playIndex = 0;
    prepare(playIndex, playWhenReady, true, true);
  }

  public void play(Object url, boolean playWhenReady, SurfaceView surfaceView,
      VideoListener videoListener) {
    resourceType = 1;
    simpleExoPlayer.addVideoListener(videoListener);
    simpleExoPlayer.setVideoSurfaceView(surfaceView);
    dataSource.clear();
    dataSource.add(url);
    this.audioResult = null;
    playIndex = 0;
    prepare(playIndex, playWhenReady, true, true);
  }

  public void play(Object url, boolean playWhenReady, PlayerView playerView,
      VideoListener videoListener) {
    resourceType = 1;
    simpleExoPlayer.addVideoListener(videoListener);
    playerView.setPlayer(simpleExoPlayer);
    dataSource.clear();
    dataSource.add(url);
    this.audioResult = null;
    playIndex = 0;
    prepare(playIndex, playWhenReady, true, true);
  }

  private void prepare(int index, final boolean playWhenReady, final boolean resetPosition,
      final boolean resetState) {
    simpleExoPlayer.setPlayWhenReady(false);
    if (dataSource.size() > index) {
      playTag = dataSource.get(index).toString();
      exoPlayStateListener.setPlayTag(playTag);
      Object data = dataSource.get(index);
      if (data instanceof String) {
        LogMgr.e(TAG, "prepare url:" + data);
        mediaSource = buildMediaSource((String) data, null);
        simpleExoPlayer.prepare(mediaSource, resetPosition, resetState);
        simpleExoPlayer.setPlayWhenReady(playWhenReady);
        return;
      }
      String dataSourceCode = "";
      String dataType = "";
      if (data instanceof AlbumContentResponse.ListBean || data instanceof AudioResult.Audio) {
        long fid = 0;
        if (data instanceof AlbumContentResponse.ListBean) {
          fid = ((AlbumContentResponse.ListBean) data).getFid();
          dataSourceCode = ((AlbumContentResponse.ListBean) data).getDataSourceCode();
          dataType = ((AlbumContentResponse.ListBean) data).getDataType();
        }
        if (data instanceof AudioResult.Audio) {
          fid = ((AudioResult.Audio) data).getId();
          if (fid == 0) {
            try {
              fid = Long.parseLong(((AudioResult.Audio) data).getThirdApiId());
            } catch (Exception e) {
              e.printStackTrace();
              fid = 0;
            }
          }
          dataSourceCode = ((AudioResult.Audio) data).getDataSourceCode();
          dataType = ((AudioResult.Audio) data).getDataType();
        }
        LogMgr.e(TAG, "prepare id:" + fid);
        if (audioResult != null) {
          dataSourceCode = audioResult.getDataSourceCode();
          dataType = audioResult.getDataType();
        }
        AudioHttpUtils.findChildLinkByIdHaveDefaultValue(fid, resourceType, dataSourceCode,
            dataType, new ResponseCallBack<KarBaseResponse<GetDataLinkResponse>>() {
              @Override public void onResponse(KarBaseResponse<GetDataLinkResponse> response) {
                if (response != null && response.getData() != null && !TextUtils.isEmpty(
                    response.getData().getUrl())) {
                  String url = response.getData().getUrl();
                  mediaSource = buildMediaSource(url, null);
                  simpleExoPlayer.prepare(mediaSource, resetPosition, resetState);
                  simpleExoPlayer.setPlayWhenReady(playWhenReady);
                } else {
                  simpleExoPlayer.setPlayWhenReady(false);
                  exoPlayStateListener.onStateChanged(PlayState.STATE_ERROR, playTag);
                }
              }

              @Override public void onError(String error) {
                simpleExoPlayer.setPlayWhenReady(false);
                exoPlayStateListener.onStateChanged(PlayState.STATE_ERROR, playTag);
              }
            });
      }
    }
  }

  public void play(int index) {
    if (dataSource.size() > index) {
      playIndex = index;
      prepare(playIndex, true, true, true);
    }
  }

  public void handPlay(int index) {
    if (dataSource.size() > index) {
      setMusicPause(false);
      play(index);
    }
  }

  public void pause() {
    if (dataSource.size() > 0) {
      if (simpleExoPlayer.getPlayWhenReady()) {
        simpleExoPlayer.setPlayWhenReady(false);
        exoPlayStateListener.onStateChanged(PlayState.STATE_PAUSE, playTag);
      }
    }
  }

  public void stop() {
    AudioHttpUtils.cancelAll();
    if (dataSource.size() > 0) {
      mediaSource = null;
      playIndex = 0;
      dataSource.clear();
      simpleExoPlayer.setPlayWhenReady(false);
      exoPlayStateListener.onStateChanged(PlayState.STATE_STOP, playTag);
    }
  }

  public void resume(boolean reset) {
    if (dataSource.size() > 0) {
      if (!isPlaying()) {
        if (mediaSource != null) {
          if (reset) {
            simpleExoPlayer.prepare(mediaSource, false, false);
          }
          simpleExoPlayer.setPlayWhenReady(true);
        } else {
          prepare(playIndex, true, true, true);
        }
      }
    }
  }

  public void handPlay(boolean reset) {
    if (dataSource.size() > 0) {
      setMusicPause(false);
      resume(reset);
    }
  }

  public void handPause() {
    if (dataSource.size() > 0) {
      setMusicPause(true);
      pause();
    }
  }

  public void handPrev() {
    if (dataSource.size() > 0) {
      setMusicPause(false);
      previous();
    }
  }

  public void handNext() {
    if (dataSource.size() > 0) {
      setMusicPause(false);
      next();
    }
  }

  public void seek(long positionMs) {
    if (dataSource.size() > 0) {
      pause();
      simpleExoPlayer.seekTo(positionMs);
      simpleExoPlayer.setPlayWhenReady(true);
    }
  }

  public void seek(long positionMs, boolean autoPlay) {
    if (dataSource.size() > 0) {
      pause();
      simpleExoPlayer.seekTo(positionMs);
      simpleExoPlayer.setPlayWhenReady(autoPlay);
    }
  }

  public void next() {
    if (dataSource.size() > 1) {
      playIndex++;
      playIndex = playIndex % dataSource.size();
      prepare(playIndex, true, true, true);
    }
  }

  public void previous() {
    if (dataSource.size() > 1) {
      playIndex--;
      if (playIndex < 0) {
        playIndex = dataSource.size() - 1;
      } else {
        playIndex = playIndex % dataSource.size();
      }
      prepare(playIndex, true, true, true);
    }
  }

  public long getDuration() {
    if (dataSource.size() > 0) {
      return simpleExoPlayer.getDuration();
    }
    return 0;
  }

  public long getCurrentPosition() {
    if (dataSource.size() > 0) {
      return simpleExoPlayer.getCurrentPosition();
    }
    return 0;
  }

  private MediaSource buildMediaSource(String url, @Nullable String overrideExtension) {
    Uri uri = Uri.parse(url);
    @C.ContentType int type = Util.inferContentType(uri, overrideExtension);
    switch (type) {
      case C.TYPE_DASH:
        return new DashMediaSource.Factory(
            new DefaultDashChunkSource.Factory(mediaDataSourceFactory),
            buildDataSourceFactory(false)).createMediaSource(uri);
      case C.TYPE_SS:
        return new SsMediaSource.Factory(new DefaultSsChunkSource.Factory(mediaDataSourceFactory),
            buildDataSourceFactory(false)).createMediaSource(uri);
      case C.TYPE_HLS:
        return new HlsMediaSource.Factory(mediaDataSourceFactory).createMediaSource(uri);
      case C.TYPE_OTHER:
        return new ExtractorMediaSource.Factory(mediaDataSourceFactory).createMediaSource(uri);
      default: {
        throw new IllegalStateException("Unsupported type: " + type);
      }
    }
  }

  private DataSource.Factory buildDataSourceFactory(boolean useBandwidthMeter) {
    return DataSourceFactory.buildDataSourceFactory(useBandwidthMeter ? bandwidthMeter : null);
  }

  public void addPlayStateListener(PlayStateListener playStateListener) {
    exoPlayStateListener.addPlayStateListener(playStateListener);
  }

  public void removePlayStateListener(PlayStateListener playStateListener) {
    exoPlayStateListener.removePlayStateListener(playStateListener);
  }

  public void setPlayMode(PlayMode mode) {
    playMode = mode;
  }

  public PlayMode getPlayMode() {
    return playMode;
  }

  public String getPlayModeValue() {
    switch (playMode) {
      case MODE_ONCE:
        return "singleOnce";
      case MODE_ORDER:
        return "listOrder";
      case MODE_RANDOM:
        return "listShuffled";
      case MODE_SINGLE:
        return "singleLoop";
      default:
        return "";
    }
  }

  public boolean isPlaying(String tag) {
    return (exoPlayStateListener.getPlayState() == PlayState.STATE_PLAYING) && playTag.equals(tag);
  }

  public boolean isPlaying() {
    LogMgr.d(TAG, "state:" + exoPlayStateListener.getPlayState());
    if (dataSource.size() > 0) {
      return (exoPlayStateListener.getPlayState() == PlayState.STATE_PLAYING);
    }
    return false;
  }

  public List getDataSource() {
    return dataSource;
  }

  public void setDataSource(List dataSource, int resourceType) {
    this.resourceType = resourceType;
    playIndex = 0;
    this.dataSource.clear();
    this.dataSource.addAll(dataSource);
    this.audioResult = null;
  }

  public void setDataSource(List dataSource, AudioResult audioResult, int resourceType) {
    this.resourceType = resourceType;
    playIndex = 0;
    this.dataSource.clear();
    this.dataSource.addAll(dataSource);
    this.audioResult = audioResult;
  }

  public void setVolumeSlow(final float volume2) {
    LogMgr.d(TAG, "setVolumeSlow:" + volume2);
    final float volume = formatVolume(volume2);
    volumeHandler.removeCallbacksAndMessages(null);
    volumeNow = simpleExoPlayer.getVolume();
    if (volume <= volumeNow) {
      simpleExoPlayer.setVolume(volume);
    } else {
      toVolume = volume;
      nowCount = 1;
      volumeHandler.sendEmptyMessageDelayed(0, 100);
    }
  }

  public void setVolumeImmediately(float volume) {
    LogMgr.d(TAG, "setVolumeImmediately:" + volume);
    volume = formatVolume(volume);
    volumeHandler.removeCallbacksAndMessages(null);
    simpleExoPlayer.setVolume(volume);
  }

  private float formatVolume(float volume) {
    if (volume < 0f) {
      volume = 0f;
    }
    if (volume > 1.0f) {
      volume = 1.0f;
    }
    return volume;
  }

  public float getVolume() {
    return simpleExoPlayer.getVolume();
  }

  private Handler volumeHandler = new Handler(Looper.getMainLooper()) {
    @Override public void handleMessage(Message msg) {
      simpleExoPlayer.setVolume(volumeNow + (toVolume - volumeNow) * nowCount / VOLUME_COUNT);
      if (nowCount < VOLUME_COUNT) {
        volumeHandler.sendEmptyMessageDelayed(0, 100);
      }
      nowCount++;
    }
  };

  public SimpleExoPlayer getSimpleExoPlayer() {
    return simpleExoPlayer;
  }

  public boolean isMusicPause() {
    return musicPause;
  }

  public void setMusicPause(boolean musicPause) {
    LogMgr.d(TAG, "setMusicPause:" + musicPause);
    this.musicPause = musicPause;
  }
}
