package com.unisound.sdk.service.utils.nlu;

/**
 * Created by liuxuancen on 16/4/2.
 */
public class APPIntent implements Intent {
  public static final int SKIP_TIP_PACKAGE = 1;
  public static final int SKIP_TIP_ACTIVITY = 2;
  public static final int SKIP_TIP_HTML = 3;
  public static final String FUNC_IMAGE_CAPTURE = "FUNC_IMAGE_CAPTURE";
  public static final String FUNC_IMAGE_VIEW = "FUNC_IMAGE_VIEW";
  public static final String FUNC_AUDIO_CAPTURE = "FUNC_AUDIO_CAPTURE";
  public static final String FUNC_VIDEO_CAPTURE = "FUNC_VIDEO_CAPTURE";
  public static final String FUNC_MUSIC_PLAY = "FUNC_MUSIC_PLAY";
  public static final String FUNC_OPEN_CONTACT = "FUNC_OPEN_CONTACT";
  public static final String FUNC_OPEN_MAIL = "FUNC_OPEN_MAIL";
  public static final String FUNC_SETTING = "FUNC_SETTING";
  public static final String FUNC_OPEN_WEB = "FUNC_OPEN_WEB";
  public static final String FUNC_OPEN_MAP = "FUNC_OPEN_MAP";
  public static final String FUNC_SEARCH = "FUNC_SEARCH";
  public static final String FUNC_OPEN_CALENDAR = "FUNC_OPEN_CALENDAR";
  public static final String FUNC_OPEN_RADIO = "FUNC_OPEN_RADIO";

  private KarProCallbackBean karProCallback;
  private IntentBean intent;

  public KarProCallbackBean getKarProCallback() {
    return karProCallback;
  }

  public void setKarProCallback(KarProCallbackBean karProCallback) {
    this.karProCallback = karProCallback;
  }

  public IntentBean getIntent() {
    return intent;
  }

  public void setIntent(IntentBean intent) {
    this.intent = intent;
  }

  public static class KarProCallbackBean {

    private String appName;         //应用名称
    private String activityName;    //activity跳转名称
    private String htmlUrl;         //html跳转地址
    private String extras;          //附加信息（暂时为空）
    private long id;
    private String packageName;     //package跳转名称
    private int redirectType;       //跳转类型 1:package 2:activity 3:h5
    private boolean useAccessToken; //是否需要AccessToken false:不需要 true:需要

    public String getAppName() {
      return appName;
    }

    public void setAppName(String appName) {
      this.appName = appName;
    }

    public String getActivityName() {
      return activityName;
    }

    public void setActivityName(String activityName) {
      this.activityName = activityName;
    }

    public String getHtmlUrl() {
      return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
      this.htmlUrl = htmlUrl;
    }

    public String getExtras() {
      return extras;
    }

    public void setExtras(String extras) {
      this.extras = extras;
    }

    public long getId() {
      return id;
    }

    public void setId(long id) {
      this.id = id;
    }

    public String getPackageName() {
      return packageName;
    }

    public void setPackageName(String packageName) {
      this.packageName = packageName;
    }

    public int getRedirectType() {
      return redirectType;
    }

    public void setRedirectType(int redirectType) {
      this.redirectType = redirectType;
    }

    public boolean isUseAccessToken() {
      return useAccessToken;
    }

    public void setUseAccessToken(boolean useAccessToken) {
      this.useAccessToken = useAccessToken;
    }
  }

  public static class IntentBean {

    private String name;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }
}
