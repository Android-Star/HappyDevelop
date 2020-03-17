package com.unisound.sdk.service.utils;

import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;

public class FileUtils {
  private static final String TAG = "FileUtils";
  private static FileUtils instance = new FileUtils();

  public static final String APP_PATH =
      Environment.getExternalStorageDirectory() + File.separator + "shenglvqimeng" + File.separator;

  private FileUtils() {

  }

  public static FileUtils getInstance() {
    return instance;
  }

  public static short[] bytesToShort(byte[] bytes) {
    if (bytes == null) {
      return null;
    }
    short[] shorts = new short[bytes.length / 2];
    ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(shorts);
    return shorts;
  }

  public static byte[] shortToBytes(short[] shorts) {
    if (shorts == null) {
      return null;
    }
    byte[] bytes = new byte[shorts.length * 2];
    ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().put(shorts);
    return bytes;
  }

  public boolean appendFile(String dst, InputStream inputStream) {
    RandomAccessFile randomFile = null;
    try {
      File fileDst = new File(dst);
      if (!fileDst.exists()) {
        fileDst.getParentFile().mkdirs();
      }
      randomFile = new RandomAccessFile(fileDst, "rw");
      long fileLength = randomFile.length();
      randomFile.seek(fileLength);
      byte[] buffer = new byte[4096];
      int read;
      while ((read = inputStream.read(buffer)) != -1) {
        randomFile.write(buffer, 0, read);
      }
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    } finally {
      if (randomFile != null) {
        try {
          randomFile.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public boolean appendFile(String dst, String src) {
    File file = new File(src);
    if (!(file.exists() && file.isFile())) {
      LogMgr.d(TAG, src + "not exist");
      return false;
    }
    try {
      InputStream inputStream = new FileInputStream(file);
      return appendFile(dst, inputStream);
    } catch (Exception e) {
      return false;
    }
  }

  public String getCode(InputStream is) {
    try {
      BufferedInputStream bin = new BufferedInputStream(is);
      int p;
      p = (bin.read() << 8) + bin.read();
      String code;
      switch (p) {
        case 0xefbb:
          code = "UTF-8";
          break;
        case 0xfffe:
          code = "Unicode";
          break;
        case 0xfeff:
          code = "UTF-16BE";
          break;
        default:
          code = "GBK";
      }
      is.close();
      return code;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static boolean deleteSingleFile(String filePath) {
    File file = new File(filePath);
    if (file.isFile() && file.exists()) {
      return file.delete();
    }
    return false;
  }

  public long getAvailableSdCard() {
    if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
      LogMgr.d(TAG, "sd card not exitst");
      return 0;
    }
    File sdcardDir = Environment.getExternalStorageDirectory();
    long availableSpace = sdcardDir.getUsableSpace();
    long totalSpace = sdcardDir.getTotalSpace();
    LogMgr.d(TAG, "availableSpace:" + availableSpace + ",totalSpace:" + totalSpace);
    return availableSpace;
  }

  public long getAvailableSpace(String path) {
    File sdcardDir = new File(path);
    long availableSpace = sdcardDir.getUsableSpace();
    long totalSpace = sdcardDir.getTotalSpace();
    LogMgr.d(TAG,
        "getAvailableSpace:" + availableSpace + ",totalSpace:" + totalSpace + ",path:" + path);
    return availableSpace;
  }

  /**
   * 写入安装文件
   *
   * @throws IOException
   */
  public static void writeInstallationFile(File installation, String saveString)
      throws IOException {
    FileOutputStream out = new FileOutputStream(installation);
    out.write(saveString.getBytes());
    out.close();
  }

  /**
   * 读取安装文件
   *
   * @throws IOException
   */
  public static String readInstallationFile(File installation) throws IOException {
    RandomAccessFile f = new RandomAccessFile(installation, "r");
    byte[] bytes = new byte[(int) f.length()];
    f.readFully(bytes);
    f.close();
    return new String(bytes);
  }

  public static String getFileMD5(File file) {
    LogMgr.d(TAG, "start md5");
    if (file == null || !file.exists() || !file.isFile()) {
      return "";
    }
    MessageDigest digest;
    FileInputStream in;
    byte[] buffer = new byte[1024];
    int len;
    try {
      digest = MessageDigest.getInstance("MD5");
      in = new FileInputStream(file);
      while ((len = in.read(buffer, 0, 1024)) != -1) {
        digest.update(buffer, 0, len);
      }
      in.close();
    } catch (Exception e) {
      e.printStackTrace();
      return "";
    }
    BigInteger bigInt = new BigInteger(1, digest.digest());
    LogMgr.d(TAG, "end md5");
    return bigInt.toString(16);
  }

  public static String readTxtFile(String strFilePath) {
    StringBuilder content = new StringBuilder();
    File file = new File(strFilePath);
    if (file.exists() && file.isFile()) {
      try {
        InputStream inputStream = new FileInputStream(file);
        if (inputStream != null) {
          InputStreamReader inputReader = new InputStreamReader(inputStream);
          BufferedReader bufferReader = new BufferedReader(inputReader);
          String line;
          while ((line = bufferReader.readLine()) != null) {
            content.append(line);
          }
          inputStream.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
        LogMgr.e(TAG, e.toString());
      }
    }
    return content.toString();
  }

  public static String getStoragePath(Context context, boolean isRemove) {
    StorageManager mStorageManager =
        (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
    Class<?> storageVolumeClazz = null;
    try {
      storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
      Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
      Method getPath = storageVolumeClazz.getMethod("getPath");
      Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
      Object result = getVolumeList.invoke(mStorageManager);
      for (int i = 0; i < Array.getLength(result); i++) {
        Object storageVolumeElement = Array.get(result, i);
        String path = (String) getPath.invoke(storageVolumeElement);
        boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
        if (isRemove == removable) {
          return path;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }
}