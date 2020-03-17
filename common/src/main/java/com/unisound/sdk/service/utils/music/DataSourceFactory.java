package com.unisound.sdk.service.utils.music;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.android.exoplayer2.ext.okhttp.OkHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.unisound.sdk.service.utils.ContextUtils;
import okhttp3.OkHttpClient;

public class DataSourceFactory {

  protected static String userAgent = getUserAgent("ExoPlayerDemo");

  private DataSourceFactory() {

  }

  public static String getUserAgent(String applicationName) {
    String versionName;
    try {
      String packageName = ContextUtils.getContext().getPackageName();
      PackageInfo info =
          ContextUtils.getContext().getPackageManager().getPackageInfo(packageName, 0);
      versionName = info.versionName;
    } catch (PackageManager.NameNotFoundException e) {
      versionName = "?";
    }
    return applicationName + "/" + versionName + " (Linux;Android " + Build.VERSION.RELEASE + ") "
        + ExoPlayerLibraryInfo.VERSION_SLASHY;
  }

  public static DataSource.Factory buildDataSourceFactory(DefaultBandwidthMeter bandwidthMeter) {
    return new DefaultDataSourceFactory(ContextUtils.getContext(), bandwidthMeter,
        buildHttpDataSourceFactory(bandwidthMeter));
  }

  public static HttpDataSource.Factory buildHttpDataSourceFactory(
      DefaultBandwidthMeter bandwidthMeter) {
    return new OkHttpDataSourceFactory(new OkHttpClient(), userAgent, bandwidthMeter);
  }
}
