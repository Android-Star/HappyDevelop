package com.example.wilsonhan.happydevelop.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.text.format.Formatter;
import com.unisound.sdk.service.utils.LogMgr;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class CleanDataUtils {
  private CleanDataUtils() {
  }

  /**
   * 需要查下缓存大小
   *
   * @throws Exception
   */
  public static String getTotalCacheSize(Context context) {
    long cacheSize = getFolderSize(context.getCacheDir());
    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
      cacheSize += getFolderSize(context.getExternalCacheDir());
    }
    return getFormatSize(cacheSize);
  }

  /**
   * 需要查下缓存大小
   *
   * @throws Exception
   */
  public static long getTotalCache(Context context) {
    long cacheSize = getFolderSize(context.getCacheDir());
    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
      cacheSize += getFolderSize(context.getExternalCacheDir());
    }
    return cacheSize;
  }

  /**
   * 清空缓存
   */
  public static void clearAllCache(Context context) {
    deleteDir(context.getCacheDir());
    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
      deleteDir(context.getExternalCacheDir());
    }
  }

  private static boolean deleteDir(File dir) {
    if (dir != null && dir.isDirectory()) {
      String[] children = dir.list();
      for (int i = 0; i < children.length; i++) {
        boolean success = deleteDir(new File(dir, children[i]));
        if (!success) {
          return false;
        }
      }
    }
    return dir.delete();
  }

  /**
   * * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache) * *
   */
  public static void cleanInternalCache(Context context) {
    deleteFilesByDirectory(context.getCacheDir());
  }

  /**
   * * 清除本应用所有数据库(/data/data/com.xxx.xxx/databases) * *
   */
  public static void cleanDatabases(Context context) {
    deleteFilesByDirectory(new File("/data/data/" + context.getPackageName() + "/databases"));
  }

  /**
   * * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs) *
   */
  public static void cleanSharedPreference(Context context) {
    deleteFilesByDirectory(new File("/data/data/" + context.getPackageName() + "/shared_prefs"));
  }

  /**
   * * 按名字清除本应用数据库 * *
   */
  public static void cleanDatabaseByName(Context context, String dbName) {
    context.deleteDatabase(dbName);
  }

  /**
   * * 清除/data/data/com.xxx.xxx/files下的内容 * *
   */
  public static void cleanFiles(Context context) {
    deleteFilesByDirectory(context.getFilesDir());
  }

  /**
   * * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache)
   */
  public static void cleanExternalCache(Context context) {
    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
      deleteFilesByDirectory(context.getExternalCacheDir());
    }
  }

  /**
   * * 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除 * *
   */
  public static void cleanCustomCache(String filePath) {
    deleteFilesByDirectory(new File(filePath));
  }

  /**
   * * 清除本应用所有的数据 * *
   */
  public static void cleanApplicationData(Context context, String... filepath) {
    cleanInternalCache(context);
    cleanExternalCache(context);
    cleanDatabases(context);
    cleanSharedPreference(context);
    cleanFiles(context);
    if (filepath == null) {
      return;
    }
    for (String filePath : filepath) {
      cleanCustomCache(filePath);
    }
  }

  /**
   * * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * *
   */
  private static void deleteFilesByDirectory(File directory) {
    if (directory != null && directory.exists() && directory.isDirectory()) {
      for (File item : directory.listFiles()) {
        item.delete();
      }
    }
  }

  // 获取文件
  //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
  //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
  public static long getFolderSize(File file) {
    long size = 0;
    try {
      File[] fileList = file.listFiles();
      for (int i = 0; i < fileList.length; i++) {
        // 如果下面还有文件
        if (fileList[i].isDirectory()) {
          size = size + getFolderSize(fileList[i]);
        } else {
          size = size + fileList[i].length();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return size;
  }

  /**
   * 删除指定目录下文件及目录
   */
  public static void deleteFolderFile(String filePath, boolean deleteThisPath) {
    if (!TextUtils.isEmpty(filePath)) {
      try {
        File file = new File(filePath);
        if (file.isDirectory()) {   // 如果下面还有文件
          File[] files = file.listFiles();
          for (int i = 0; i < files.length; i++) {
            deleteFolderFile(files[i].getAbsolutePath(), true);
          }
        }
        if (deleteThisPath) {
          if (!file.isDirectory()) {    // 如果是文件，删除
            file.delete();
          } else {    // 目录
            if (file.listFiles().length == 0) {   // 目录下没有文件或者目录，删除
              file.delete();
            }
          }
        }
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  /**
   * 格式化单位
   */
  public static String getFormatSize(double size) {
    double kiloByte = size / 1024;
    //        if (kiloByte < 1) {
    //            return size + "Byte";
    //        }

    double megaByte = kiloByte / 1024;
    if (megaByte < 1) {
      BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
      return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "K";
    }

    double gigaByte = megaByte / 1024;
    if (gigaByte < 1) {
      BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
      return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "M";
    }

    double teraBytes = gigaByte / 1024;
    if (teraBytes < 1) {
      BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
      return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "G";
    }
    BigDecimal result4 = new BigDecimal(teraBytes);
    return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "T";
  }

  public static String getCacheSize(File file) {
    return getFormatSize(getFolderSize(file));
  }

  /**
   *   * 获取android当前可用运行内存大小
   *   * @param context
   *   *
   */
  public static String getAvailMemory(Context context) {
    ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
    am.getMemoryInfo(mi);
    // mi.availMem; 当前系统的可用内存
    return Formatter.formatFileSize(context, mi.availMem);  // 将获取的内存大小规格化
  }

  /**
   *   * 获取android总运行内存大小
   *   * @param context
   *   *
   */
  public static String getTotalMemory(Context context) {
    String str1 = "/proc/meminfo";    // 系统内存信息文件
    String str2;
    String[] arrayOfString;
    long initialMemory = 0;
    try {
      FileReader localFileReader = new FileReader(str1);
      BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
      str2 = localBufferedReader.readLine();  // 读取meminfo第一行，系统总内存大小
      arrayOfString = str2.split("\\s+");
      for (String num : arrayOfString) {
        LogMgr.i(str2, num + "\t");
      }
      // 获得系统总内存，单位是KB
      int i = Integer.valueOf(arrayOfString[1]).intValue();
      //int值乘以1024转换为long类型
      initialMemory = new Long((long) i * 1024);
      localBufferedReader.close();
    } catch (IOException e) {
    }
    return Formatter.formatFileSize(context, initialMemory); // Byte转换为KB或者MB，内存大小规格化
  }

  /**
   * SDCard 总容量大小
   *
   * @return MB
   */
  public static long getTotalSize() {
    File file = Environment.getExternalStorageDirectory();
    StatFs statFs = new StatFs(file.getPath());
    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
      //获得sdcard上 block的总数
      long blockCount = statFs.getBlockCount();
      //获得sdcard上每个block 的大小
      long blockSize = statFs.getBlockSize();
      //计算标准大小使用：1024，当然使用1000也可以
      long bookTotalSize = blockCount * blockSize / 1024 / 1024;
      return bookTotalSize;
    } else {
      return -1;
    }
  }

  /**
   * SDCard 总容量大小
   *
   * @return B
   */
  public static long getTotalSdSize() {
    File file = Environment.getExternalStorageDirectory();
    StatFs statFs = new StatFs(file.getPath());
    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
      //获得sdcard上 block的总数
      long blockCount = statFs.getBlockCount();
      //获得sdcard上每个block 的大小
      long blockSize = statFs.getBlockSize();
      //计算标准大小使用：1024，当然使用1000也可以
      long bookTotalSize = blockCount * blockSize;
      return bookTotalSize;
    } else {
      return -1;
    }
  }

  /**
   * 计算Sdcard的剩余大小
   *
   * @return MB
   */
  public static long getAvailableSize() {
    File file = Environment.getExternalStorageDirectory();
    StatFs statFs = new StatFs(file.getPath());
    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
      //获得Sdcard上每个block的size
      long blockSize = statFs.getBlockSize();
      //获取可供程序使用的Block数量
      long blockavailable = statFs.getAvailableBlocks();
      //计算标准大小使用：1024，当然使用1000也可以
      long blockavailableTotal = blockSize * blockavailable / 1024 / 1024;
      return blockavailableTotal;
    } else {
      return -1;
    }
  }
}
