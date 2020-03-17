package com.unisound.sdk.service.utils.mqtt;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.unisound.sdk.service.utils.JsonTool;
import com.unisound.sdk.service.utils.LogMgr;
import com.unisound.sdk.service.utils.NetUtils;
import com.unisound.sdk.service.utils.SystemUtils;
import com.unisound.sdk.service.utils.callback.NetWorkChangeCallBack;
import com.unisound.sdk.service.utils.constants.UrlConstant;
import com.unisound.sdk.service.utils.http.HttpUtils;
import com.unisound.sdk.service.utils.http.ResponseCallBack;
import com.unisound.sdk.service.utils.mqtt.bean.MqttClientParam;
import com.unisound.sdk.service.utils.mqtt.bean.MqttReceiveMessage;
import com.unisound.sdk.service.utils.mqtt.bean.RegisterParam;
import com.unisound.sdk.service.utils.mqtt.bean.RegisterResult;
import com.unisound.sdk.service.utils.mqtt.bean.ReportMessage;
import com.unisound.sdk.service.utils.mqtt.impl.MqttConnectCallBack;
import com.unisound.sdk.service.utils.mqtt.impl.MqttMsgCallBack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttHelp implements NetWorkChangeCallBack {
  private static final String TAG = "MqttHelp";
  private static final String BASE_REGISTER = "register";
  private static final String BASE_SUB_SCRIBE = "s2c/msg/";
  private static final String BASE_PUBLISH = "c2s/msg/";
  private static final int MQTT_MESSAGE_RECEIVED = 1000;
  private static final int MQTT_MESSAGE_CONNECTED = 1001;
  private static final int MQTT_MESSAGE_CONNECT_FAIL = 1002;
  private static MqttHelp mqttHelp = new MqttHelp();
  private static MqttAsyncClient mqttAsyncClient;
  private List<MqttConnectCallBack> mqttConnectCallBackList = new ArrayList<>();
  private boolean isConnecting = false;
  private boolean needConnect = true;
  private RegisterParam registerParam;
  private MqttClientParam mqttClientParam;
  private HandlerThread connectThread;
  private Handler connectHandler;

  public void putMqttMsgCallBack(MqttMsgCallBack mqttMsgCallBack) {
    this.mqttMsgCallBack = mqttMsgCallBack;
  }

  public void clearMqttMsgCallBack(MqttMsgCallBack mqttMsgCallBack) {
    if (this.mqttMsgCallBack == mqttMsgCallBack) {
      this.mqttMsgCallBack = null;
    }
  }

  private MqttMsgCallBack mqttMsgCallBack;
  private Handler handler = new Handler() {
    @Override public void handleMessage(Message msg) {
      try {
        switch (msg.what) {
          case MQTT_MESSAGE_RECEIVED:
            String content = (String) msg.obj;
            LogMgr.d(TAG, "mqtt content:" + content);
            if (content == null) {
              return;
            }
            MqttReceiveMessage mqttReceiveMessage =
                JsonTool.fromJson(content, MqttReceiveMessage.class);
            LogMgr.d(TAG, "mqttReceiveMessage:" + mqttReceiveMessage.getMsg());
            if (mqttMsgCallBack != null) {
              mqttMsgCallBack.dealMqttMsg(mqttReceiveMessage.getMsg());
            }
            break;
          case MQTT_MESSAGE_CONNECTED:
            onConnectSuccess();
            break;
          case MQTT_MESSAGE_CONNECT_FAIL:
            onConnectFail();
            break;
          default:
            break;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  };

  private MqttHelp() {
    connectThread = new HandlerThread("connectMqtt");
    connectThread.start();
    connectHandler = new Handler(connectThread.getLooper());
  }

  public static MqttHelp getInstance() {
    return mqttHelp;
  }

  public void addMqttCallBack(MqttConnectCallBack callBack) {
    if (callBack != null && !mqttConnectCallBackList.contains(callBack)) {
      mqttConnectCallBackList.add(callBack);
    }
  }

  public void removeMqttCallBack(MqttConnectCallBack callBack) {
    mqttConnectCallBackList.remove(callBack);
  }

  public void init(String appKey, String appSecret, String token) {
    disconnect();
    needConnect = true;
    LogMgr.d(TAG, "mqtt init:" + token);
    NetUtils.init();
    NetUtils.addNetWorkChangeCallBack(this);
    registerParam = new RegisterParam();
    registerParam.setAppKey(appKey);
    registerParam.setAppSecret(appSecret);
    registerParam.setToken(token);
    registerParam.setUdid(SystemUtils.getDeviceId());
    registerMqtt(registerParam);
  }

  public void init(RegisterParam registerParam) {
    disconnect();
    needConnect = true;
    NetUtils.init();
    NetUtils.addNetWorkChangeCallBack(this);
    this.registerParam = registerParam;
    registerMqtt(registerParam);
  }

  public void registerMqtt(RegisterParam param) {
    if (!NetUtils.isNetworkAvailable()) {
      return;
    }
    isConnecting = true;
    LogMgr.d(TAG, "registerMqtt registerAddress:" + UrlConstant.getInstance().getMsgCenterUrl()
        + BASE_REGISTER);
    param.setTimestamp(MqttSignUtils.getCurrentUnixTimestamp());
    final String paramString = param.formatParam();
    LogMgr.d(TAG, "registerMqtt paramString:" + paramString);
    HashMap<String, String> headMap = new HashMap<>();
    headMap.put("accept", "*/*");
    headMap.put("connection", "close");

    HttpUtils.post(TAG, UrlConstant.getInstance().getMsgCenterUrl() + BASE_REGISTER, headMap,
        HttpUtils.MEDIA_TYPE_WWW, paramString, false, new ResponseCallBack<RegisterResult>() {
          @Override public void onResponse(RegisterResult response) {
            if (response.isSuccess()) {
              RegisterResult.ResultBean.ConnectionBean connectionInfo =
                  response.getResult().getConnection();
              mqttClientParam = new MqttClientParam();
              mqttClientParam.setUserName(connectionInfo.getUsername());
              mqttClientParam.setPassWord(connectionInfo.getPassword());
              String clientId = response.getResult().getClientId();
              mqttClientParam.setClientId(clientId);
              mqttClientParam.setSubscribe(new String[] { BASE_SUB_SCRIBE + clientId });
              mqttClientParam.setPublish(BASE_PUBLISH + clientId);
              mqttClientParam.setConnectUrl(
                  connectionInfo.getProtocol() + "://" + connectionInfo.getIp() + ":"
                      + connectionInfo.getPort());
              startConnect(mqttClientParam);
            } else {
              onError(response.getReturnCode());
            }
          }

          @Override public void onError(String error) {
            LogMgr.d(TAG, "registerMqtt onError:" + error);
            try {
              Thread.sleep(5000);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            isConnecting = false;
            registerMqtt(registerParam);
          }
        });
  }

  private void onConnectSuccess() {
    for (MqttConnectCallBack mqttConnectCallBack : mqttConnectCallBackList) {
      mqttConnectCallBack.onConnectSuccess();
    }
  }

  private void onConnectFail() {
    for (MqttConnectCallBack mqttConnectCallBack : mqttConnectCallBackList) {
      mqttConnectCallBack.onConnectFail();
    }
  }

  private boolean connect(MqttClientParam param) {
    if (isConnectSuccess()) {
      return true;
    }
    try {
      LogMgr.d(TAG, "param.getConnectUrl():" + param.getConnectUrl());
      mqttAsyncClient = new MqttAsyncClient(param.getConnectUrl(), param.getClientId(), null);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    MqttConnectOptions options = new MqttConnectOptions();
    options.setCleanSession(false);
    options.setUserName(param.getUserName());
    options.setPassword(param.getPassWord().toCharArray());
    // 设置超时时间，默认30s
    options.setConnectionTimeout(30);
    // 设置会话keep alive心跳时间，默认 60 seconds
    options.setKeepAliveInterval(30);
    mqttAsyncClient.setCallback(new MyMqttCallBack());
    try {
      IMqttToken mqttToken = mqttAsyncClient.connect(options);
      mqttToken.waitForCompletion();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public void disconnect() {
    try {
      needConnect = false;
      isConnecting = false;
      mqttClientParam = null;
      connectHandler.removeCallbacksAndMessages(null);
      HttpUtils.cancel(TAG);
      if (isConnectSuccess()) {
        Log.d(TAG, "client disconnect mqtt");
        mqttAsyncClient.disconnect();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void startConnect(final MqttClientParam mqttClientParam) {
    if (!NetUtils.isAllLevelConnected()) {
      isConnecting = false;
      LogMgr.d(TAG, "startConnect net not available");
      return;
    }
    LogMgr.d(TAG, "needConnect:" + needConnect);
    if (!needConnect) {
      return;
    }
    connectHandler.post(new Runnable() {
      @Override public void run() {
        boolean isSuccess = connect(mqttClientParam);
        IMqttToken mqttToken = null;
        if (isSuccess) {
          mqttToken = subscriber(mqttClientParam.getSubscribe(), new int[] { 2 });
          if (mqttToken != null) {
            isConnecting = false;
            LogMgr.d(TAG, "connect success");
            handler.removeMessages(MQTT_MESSAGE_CONNECTED);
            handler.sendEmptyMessage(MQTT_MESSAGE_CONNECTED);
            return;
          }
        }
        LogMgr.d(TAG, "connect fail isSuccess:" + isSuccess + ",mqttToken:" + mqttToken);
        isConnecting = true;
        handler.removeMessages(MQTT_MESSAGE_CONNECT_FAIL);
        handler.sendEmptyMessage(MQTT_MESSAGE_CONNECT_FAIL);
        connectHandler.postDelayed(new Runnable() {
          @Override public void run() {
            startConnect(mqttClientParam);
          }
        }, 5000);
      }
    });
  }

  public IMqttToken subscriber(String[] topics, int[] qos) {
    try {
      if (mqttAsyncClient != null) {
        return mqttAsyncClient.subscribe(topics, qos);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public void onNetWorkAvailable() {
    LogMgr.d(TAG, "network available isConnecting:" + isConnecting);
    reconnect();
  }

  @Override public void onNetWorkUnAvailable() {

  }

  public boolean isConnectSuccess() {
    return mqttAsyncClient != null && mqttAsyncClient.isConnected();
  }

  public boolean sendMessage(Object message) {
    try {
      if (isConnectSuccess()) {
        ReportMessage reportMessage = new ReportMessage();
        reportMessage.setToken(registerParam.getOriginalToken());
        String content = JsonTool.toJson(message);
        reportMessage.setMsg(content);
        LogMgr.d(TAG, "sendData dataString :" + content);
        IMqttDeliveryToken iMqttToken =
            sendMessage(mqttClientParam.getPublish(), JsonTool.toJson(reportMessage).getBytes());
        if (iMqttToken != null) {
          LogMgr.d(TAG, "send message:" + iMqttToken.getMessageId());
          return true;
        }
      } else {
        LogMgr.d(TAG, "sendMessage disconnect");
      }
    } catch (Exception e) {
    }
    return false;
  }

  private IMqttDeliveryToken sendMessage(String topic, byte[] message) {
    try {
      if (isConnectSuccess()) {
        return mqttAsyncClient.publish(topic, message, 1, false);
      } else {
        LogMgr.d(TAG, "sendMessage disconnect");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public void reconnect() {
    LogMgr.d(TAG, "reconnect:" + isConnecting);
    if (!NetUtils.isAllLevelConnected()) {
      LogMgr.d(TAG, "isNetworkAvailable false");
      return;
    }
    LogMgr.d(TAG, "isConnecting:" + isConnecting + ",needConnect:" + needConnect);
    if (isConnecting) return;
    if (!needConnect) return;
    if (isConnectSuccess()) {
      LogMgr.d(TAG, "mqttAsyncClient is connected");
      return;
    }
    if (mqttClientParam == null) {
      LogMgr.d(TAG, "reconnect registerMqtt");
      registerMqtt(registerParam);
    } else {
      LogMgr.d(TAG, "reconnect startConnect");
      isConnecting = true;
      startConnect(mqttClientParam);
    }
  }

  private class MyMqttCallBack implements MqttCallback {
    @Override public void connectionLost(Throwable cause) {
      LogMgr.d(TAG, "connectionLost cause:" + cause);
      reconnect();
    }

    @Override
    public void messageArrived(String topic, org.eclipse.paho.client.mqttv3.MqttMessage message) {
      String content = new String(message.getPayload());
      LogMgr.d(TAG, "messageArrived topic:" + topic + ",message:" + content);
      Message msg = handler.obtainMessage();
      msg.what = MQTT_MESSAGE_RECEIVED;
      msg.obj = content;
      handler.sendMessage(msg);
    }

    @Override public void deliveryComplete(IMqttDeliveryToken token) {
      try {
        LogMgr.d(TAG, "deliveryComplete message:" + token.getMessage() + ",messageId:"
            + token.getMessageId());
      } catch (MqttException e) {
        e.printStackTrace();
      }
    }
  }
}
