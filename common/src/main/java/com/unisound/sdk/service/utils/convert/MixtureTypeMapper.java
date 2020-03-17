package com.unisound.sdk.service.utils.convert;

import com.google.gson.reflect.TypeToken;
import com.unisound.sdk.service.utils.JsonTool;
import com.unisound.sdk.service.utils.nlu.APPIntent;
import com.unisound.sdk.service.utils.nlu.AlarmIntent;
import com.unisound.sdk.service.utils.nlu.AudioIntent;
import com.unisound.sdk.service.utils.nlu.AudioResult;
import com.unisound.sdk.service.utils.nlu.BroadcastIntent;
import com.unisound.sdk.service.utils.nlu.CallIntent;
import com.unisound.sdk.service.utils.nlu.ContactIntent;
import com.unisound.sdk.service.utils.nlu.CookBookIntent;
import com.unisound.sdk.service.utils.nlu.CookBookResult;
import com.unisound.sdk.service.utils.nlu.DictionaryIntent;
import com.unisound.sdk.service.utils.nlu.FlightIntent;
import com.unisound.sdk.service.utils.nlu.HotelIntent;
import com.unisound.sdk.service.utils.nlu.Intent;
import com.unisound.sdk.service.utils.nlu.LocalSearchIntent;
import com.unisound.sdk.service.utils.nlu.MapIntent;
import com.unisound.sdk.service.utils.nlu.Mixture;
import com.unisound.sdk.service.utils.nlu.MusicIntent;
import com.unisound.sdk.service.utils.nlu.MusicResult;
import com.unisound.sdk.service.utils.nlu.NewsIntent;
import com.unisound.sdk.service.utils.nlu.NewsResult;
import com.unisound.sdk.service.utils.nlu.NoteIntent;
import com.unisound.sdk.service.utils.nlu.PoiResult;
import com.unisound.sdk.service.utils.nlu.ReminderIntent;
import com.unisound.sdk.service.utils.nlu.Result;
import com.unisound.sdk.service.utils.nlu.SMSIntent;
import com.unisound.sdk.service.utils.nlu.SName;
import com.unisound.sdk.service.utils.nlu.SettingExtIntent;
import com.unisound.sdk.service.utils.nlu.SettingIntent;
import com.unisound.sdk.service.utils.nlu.StockIntent;
import com.unisound.sdk.service.utils.nlu.StockResult;
import com.unisound.sdk.service.utils.nlu.TrafficControlIntent;
import com.unisound.sdk.service.utils.nlu.TrafficControlResult;
import com.unisound.sdk.service.utils.nlu.TrafficIntent;
import com.unisound.sdk.service.utils.nlu.TranslateIntent;
import com.unisound.sdk.service.utils.nlu.TranslateResult;
import com.unisound.sdk.service.utils.nlu.VideoIntent;
import com.unisound.sdk.service.utils.nlu.VideoResult;
import com.unisound.sdk.service.utils.nlu.WeatherIntent;
import com.unisound.sdk.service.utils.nlu.WeatherResult;
import com.unisound.sdk.service.utils.nlu.WechatIntent;
import com.unisound.sdk.service.utils.nlu.WechatResult;
import com.unisound.sdk.service.utils.nlu.YPCallIntent;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class MixtureTypeMapper {
  private MixtureTypeMapper() {
  }

  private static final Type VIDEO_CALL = new TypeToken<Mixture<CallIntent, Result.NullResult>>() {
      }.getType();
  private static final Type MUSIC = new TypeToken<Mixture<MusicIntent, MusicResult>>() {
      }.getType();
  private static final Type NEWS = new TypeToken<Mixture<NewsIntent, NewsResult>>() {
      }.getType();
  private static final Type AUDIO = new TypeToken<Mixture<AudioIntent, AudioResult>>() {
      }.getType();
  private static final Type VIDEO = new TypeToken<Mixture<VideoIntent, VideoResult>>() {
      }.getType();
  private static final Type DICTIONARY = new TypeToken<Mixture<DictionaryIntent, Result.NullResult>>() {
      }.getType();
  private static final Type ALARM = new TypeToken<Mixture<AlarmIntent, Result.NullResult>>() {
      }.getType();
  private static final Type SETTING = new TypeToken<Mixture<SettingIntent, Result.NullResult>>() {
      }.getType();
  private static final Type SETTING_EXT = new TypeToken<Mixture<SettingExtIntent, Result.NullResult>>() {
      }.getType();
  private static final Type REMINDER = new TypeToken<Mixture<ReminderIntent, Result.NullResult>>() {
      }.getType();
  private static final Type STOCK = new TypeToken<Mixture<StockIntent, StockResult>>() {
      }.getType();
  private static final Type WEATHER = new TypeToken<Mixture<WeatherIntent, WeatherResult>>() {
      }.getType();
  private static final Type APP = new TypeToken<Mixture<APPIntent, Result.NullResult>>() {
      }.getType();
  private static final Type ANSWER = new TypeToken<Mixture<Intent.NullIntent, Result.NullResult>>() {
      }.getType();
  private static final Type CALL = new TypeToken<Mixture<CallIntent, Result.NullResult>>() {
      }.getType();
  private static final Type CONTACT = new TypeToken<Mixture<ContactIntent, Result.NullResult>>() {
      }.getType();
  private static final Type SMS = new TypeToken<Mixture<SMSIntent, Result.NullResult>>() {
      }.getType();
  private static final Type FLIGHT = new TypeToken<Mixture<FlightIntent, Result.NullResult>>() {
      }.getType();
  private static final Type LOCAL_SEARCH = new TypeToken<Mixture<LocalSearchIntent, Result.NullResult>>() {
      }.getType();
  private static final Type MAP = new TypeToken<Mixture<MapIntent, PoiResult>>() {
      }.getType();
  private static final Type TRAFFIC = new TypeToken<Mixture<TrafficIntent, Result.NullResult>>() {
      }.getType();
  private static final Type WIFI_CONNECT = new TypeToken<Mixture<Intent.NullIntent, Result.NullResult>>() {
      }.getType();
  private static final Type TRAFFIC_CONTROL = new TypeToken<Mixture<TrafficControlIntent, TrafficControlResult>>() {
      }.getType();
  private static final Type TRAFFIC_VIOLATION = new TypeToken<Mixture<TrafficControlIntent, Result.NullResult>>() {
      }.getType();
  private static final Type BROADCAST = new TypeToken<Mixture<BroadcastIntent, Result.NullResult>>() {
      }.getType();
  private static final Type WAKEUP_WORD = new TypeToken<Mixture<Intent.NullIntent, Result.NullResult>>() {
      }.getType();
  private static final Type WECHAT = new TypeToken<Mixture<WechatIntent, WechatResult>>() {
      }.getType();
  private static final Type YP_CALL = new TypeToken<Mixture<YPCallIntent, Result.NullResult>>() {
      }.getType();
  private static final Type YP_TRANSLATE = new TypeToken<Mixture<TranslateIntent, TranslateResult>>() {
      }.getType();
  private static final Type SETTING_MAP = new TypeToken<Mixture<Intent.NullIntent, Result.NullResult>>() {
      }.getType();
  private static final Type ERROR_REPORT = new TypeToken<Mixture<Intent.NullIntent, Result.NullResult>>() {
      }.getType();
  private static final Type POI = new TypeToken<Mixture<MapIntent, PoiResult>>() {
      }.getType();
  private static final Type NOTE = new TypeToken<Mixture<NoteIntent, PoiResult>>() {
      }.getType();
  private static final Type COOKBOOK = new TypeToken<Mixture<CookBookIntent, CookBookResult>>() {
      }.getType();
  private static final Type HOTEL = new TypeToken<Mixture<HotelIntent, Result.NullResult>>() {
      }.getType();
  private static final Type HELP = new TypeToken<Mixture<HotelIntent, Result.NullResult>>() {
      }.getType();
  private static final Type CHAT = Mixture.class;

  private static Map<String, Type> serviceTypes = new HashMap<>();

  static {
    serviceTypes.put(SName.NEWS, NEWS);
    serviceTypes.put(SName.MUSIC, AUDIO);
    serviceTypes.put(SName.AUDIO, AUDIO);
    serviceTypes.put(SName.VIDEO, VIDEO);
    serviceTypes.put(SName.POEM, AUDIO);
    serviceTypes.put(SName.SETTING, SETTING_EXT);
    serviceTypes.put(SName.SETTING_COMMON, SETTING_EXT);
    serviceTypes.put(SName.SETTING_AIR, SETTING_EXT);
    serviceTypes.put(SName.SETTING_THERMOSTAT, SETTING_EXT);
    serviceTypes.put(SName.SETTING_MP, SETTING_EXT);
    serviceTypes.put(SName.SETTING_MAP, SETTING_EXT);
    serviceTypes.put(SName.ALARM, ALARM);
    serviceTypes.put(SName.REMINDER, REMINDER);
    serviceTypes.put(SName.STOCK, STOCK);
    serviceTypes.put(SName.WEATHER, WEATHER);
    serviceTypes.put(SName.APP, APP);
    serviceTypes.put(SName.CHAT, CHAT);
    serviceTypes.put(SName.HELP, HELP);
    serviceTypes.put(SName.HOTEL, HOTEL);
    serviceTypes.put(SName.CALL, CALL);
    serviceTypes.put(SName.CONTACT, CONTACT);
    serviceTypes.put(SName.SMS, SMS);
    serviceTypes.put(SName.FLIGHT, FLIGHT);
    serviceTypes.put(SName.LOCALSEARCH, LOCAL_SEARCH);
    serviceTypes.put(SName.MAP, MAP);
    serviceTypes.put(SName.TRAFFIC, TRAFFIC);
    serviceTypes.put(SName.WIFI_CONNECT, WIFI_CONNECT);
    serviceTypes.put(SName.BROADCAST, BROADCAST);
    serviceTypes.put(SName.TRAFFIC_CONTROL, TRAFFIC_CONTROL);
    serviceTypes.put(SName.TRAFFIC_VIOLATION, TRAFFIC_VIOLATION);
    serviceTypes.put(SName.WAKEUP_WORD, WAKEUP_WORD);
    serviceTypes.put(SName.YP_CALL, YP_CALL);
    serviceTypes.put(SName.TRANSLATION, YP_TRANSLATE);
    serviceTypes.put(SName.NOTE, NOTE);
    serviceTypes.put(SName.ERROR, ERROR_REPORT);
    serviceTypes.put(SName.COOKBOOK, COOKBOOK);
    serviceTypes.put(SName.DICTIONARY, DICTIONARY);
  }

  private static Map<String, Type> getServiceTypes() {
    return serviceTypes;
  }

  public static Mixture getMixture(String sName, String result) {
    Mixture mixture = null;
    if (getServiceTypes().containsKey(sName)) {
      Type type = getServiceTypes().get(sName);
      mixture = JsonTool.fromJson(result, type);
    }
    return mixture;
  }
}
