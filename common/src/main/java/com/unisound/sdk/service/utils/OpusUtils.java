package com.unisound.sdk.service.utils;

import cn.yunzhisheng.utils.codec.UniCodec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class OpusUtils {

  private static UniCodec uniCodec;

  static {
    uniCodec = new UniCodec(true);
  }

  private OpusUtils() {

  }

  public static byte[] pcmToOpus(int sampleRate, byte[] dataPcm) {
    try {
      byte[] dataOpus = uniCodec.deal(dataPcm);
      return dataOpus;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static File pcmToOpus(int sampleRate, File fileIn) {
    try {
      InputStream inputStream = new FileInputStream(fileIn);
      int available = inputStream.available();
      byte[] dataPcm = new byte[available];
      inputStream.read(dataPcm, 0, available);
      byte[] dataOpus = OpusUtils.pcmToOpus(sampleRate, dataPcm);
      File fileOpus = new File(fileIn.getAbsolutePath() + "opus");
      OutputStream outputStream = new FileOutputStream(fileOpus);
      outputStream.write(dataOpus, 0, dataOpus.length);
      outputStream.flush();
      outputStream.close();
      return fileOpus;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
