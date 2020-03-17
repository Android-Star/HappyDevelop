package com.example.wilsonhan.happydevelop.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import com.unisound.sdk.service.utils.ContextUtils;
import com.unisound.sdk.service.utils.LogMgr;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileUtils {
  private static final String TAG = "FileUtils";
  private static final String IMAGE_FILE_NAME_TEMPLATE = "Image%s.jpg";
  private static final String IMAGE_FILE_PATH_TEMPLATE = "%s/%s";
  public static final String DATA_BASE_URL =
      Environment.getExternalStorageDirectory() + "/SmartPhone/ScreenShots";
  public static final String DATA_RECORD_URL =
      Environment.getExternalStorageDirectory() + "/Records";
  public static final String DATA_APK_URL =
      Environment.getExternalStorageDirectory() + "/SmartPhone/apk";
  public static final String DATA_IMAGE_URL =
      Environment.getExternalStorageDirectory() + "/SmartPhone/image";

  static {
    File file = new File(DATA_BASE_URL);
    if (!file.exists()) {
      file.mkdirs();
    }
    File file1 = new File(DATA_RECORD_URL);
    if (!file1.exists()) {
      file1.mkdirs();
    }
    File apkFile = new File(DATA_APK_URL);
    if (!apkFile.exists()) {
      apkFile.mkdirs();
    }
    File imageFile = new File(DATA_IMAGE_URL);
    if (!imageFile.exists()) {
      imageFile.mkdirs();
    }
  }

  private FileUtils() {
  }

  public static boolean createNewFile(String destFileName) {
    File file = new File(destFileName);
    if (file.exists()) {
      return false;
    }
    if (destFileName.endsWith(File.separator)) {
      return false;
    }
    //判断目标文件所在的目录是否存在
    if (!file.getParentFile().exists()) {
      //如果目标文件所在的目录不存在，则创建父目录
      if (!file.getParentFile().mkdirs()) {
        return false;
      }
    }
    //创建目标文件
    try {
      return file.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  public static void saveToSD(Bitmap bmp, int maxSize) {
    String filePath = createImagePath();
    File file = new File(createImagePath());
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    LogMgr.d(TAG, "saveToSD--->file AbsolutePath:" + filePath);
    try {
      compressAndGenImage(bmp, filePath, maxSize);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      bmp.recycle();
    }
  }

  public static String createImagePath() {
    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
      String imageDate = TimeUtils.getCurrentDate(TimeUtils.NORMAL_FORMAT);
      String mFileName = String.format(IMAGE_FILE_NAME_TEMPLATE, imageDate);
      String filePath = String.format(IMAGE_FILE_PATH_TEMPLATE, DATA_BASE_URL, mFileName);
      LogMgr.d(TAG, "saveToSD--->file path:" + filePath);
      File file = new File(filePath);
      if (!file.exists()) {
        try {
          file.createNewFile();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return filePath;
    }
    return "";
  }

  public static void compressAndGenImage(Bitmap image, String outPath, int maxSize)
      throws IOException {
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    int options = 100;
    image.compress(Bitmap.CompressFormat.JPEG, options, os);
    if (maxSize != 0) {
      while (os.toByteArray().length / 1024 > maxSize) {
        os.reset();
        options -= 10;
        image.compress(Bitmap.CompressFormat.JPEG, options, os);
      }
    }
    FileOutputStream fos = new FileOutputStream(outPath);
    fos.write(os.toByteArray());
    LogMgr.d(TAG, "compressAndGenImage--->文件大小：" + os.size() + "，压缩比例：" + options);
    fos.flush();
    fos.close();
    updataMediaLibrary(ContextUtils.getContext(), outPath);
  }

  public static void compressAndGenImage(Bitmap image, String outPath) throws IOException {
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    int options = 70;
    image.compress(Bitmap.CompressFormat.JPEG, options, os);

    FileOutputStream fos = new FileOutputStream(outPath);
    fos.write(os.toByteArray());
    LogMgr.d(TAG, "compressAndGenImage--->文件大小：" + os.size() + "，压缩比例：" + options);
    fos.flush();
    fos.close();
    updataMediaLibrary(ContextUtils.getContext(), outPath);
  }

  public static void updataMediaLibrary(final Context context, String path) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      MediaScannerConnection.scanFile(context, new String[] { path }, null,
          new MediaScannerConnection.OnScanCompletedListener() {
            public void onScanCompleted(String path, Uri uri) {
              Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
              mediaScanIntent.setData(uri);
              context.sendBroadcast(mediaScanIntent);
            }
          });
    } else {
      File file = new File(path);
      String relationDir = file.getParent();
      File file1 = new File(relationDir);
      context.sendBroadcast(
          new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.fromFile(file1.getAbsoluteFile())));
    }
  }

  public static String generateFileMD5(String filePath) throws Exception {
    String md5 = "";
    File file = new File(filePath);
    if (file.exists()) {
      MessageDigest messageDigest = getMD5();
      InputStream in = new FileInputStream(file);
      byte[] cache = new byte[2048];
      boolean var6 = false;

      int nRead;
      while ((nRead = in.read(cache)) != -1) {
        messageDigest.update(cache, 0, nRead);
      }

      in.close();
      byte[] data = messageDigest.digest();
      md5 = byteArrayToHexString(data);
    }

    return md5;
  }

  private static String byteArrayToHexString(byte[] data) {
    char[] hexDigits = new char[] {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };
    char[] arr = new char[32];
    int k = 0;

    for (int i = 0; i < 16; ++i) {
      byte b = data[i];
      arr[k++] = hexDigits[b >>> 4 & 15];
      arr[k++] = hexDigits[b & 15];
    }

    return new String(arr);
  }

  private static MessageDigest getMD5() throws NoSuchAlgorithmException {
    return MessageDigest.getInstance("MD5");
  }
}
