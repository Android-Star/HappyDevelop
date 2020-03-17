package com.unisound.sdk.service.utils;

import android.util.Base64;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DesUtils {
  public static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";
  private static final String KEY = "flashTokenSecret";

  private DesUtils() {

  }

  public static String encode(String data) {
    return encode(KEY, data.getBytes());
  }

  private static String encode(String key, byte[] data) {
    try {
      DESKeySpec dks = new DESKeySpec(key.getBytes());
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
      // key的长度不能够小于8位字节
      Key secretKey = keyFactory.generateSecret(dks);
      Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
      IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());
      AlgorithmParameterSpec paramSpec = iv;
      cipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);
      byte[] bytes = cipher.doFinal(data);
      return Base64.encodeToString(bytes, 0);
    } catch (Exception e) {
      return "";
    }
  }

  private static byte[] decode(byte[] data) throws Exception {
    try {
      DESKeySpec dks = new DESKeySpec(KEY.getBytes());
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
      // key的长度不能够小于8位字节
      Key secretKey = keyFactory.generateSecret(dks);
      Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
      IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());
      AlgorithmParameterSpec paramSpec = iv;
      cipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);
      return cipher.doFinal(data);
    } catch (Exception e) {
      throw new Exception(e);
    }
  }

  public static String decode(String data) {
    byte[] datas;
    String value = null;
    try {
      if (System.getProperty("os.name") != null && (
          "sunos".equalsIgnoreCase(System.getProperty("os.name")) || "linux".equalsIgnoreCase(
              System.getProperty("os.name")))) {
        datas = decode(Base64.decode(data, 0));
      } else {
        datas = decode(Base64.decode(data, 0));
      }
      value = new String(datas);
    } catch (Exception e) {
      value = "";
    }
    return value;
  }
}
