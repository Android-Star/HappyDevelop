package com.unisound.sdk.service.utils;

import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class AssetsUtils {
  private final static String TAG = "AssetsUtils";

  private AssetsUtils() {
  }

  public static boolean copyAssetsDirectory(String oldDirectory, String newDirectory,
      boolean ttsModel, boolean ignoreSameSizeFile, boolean deleteOldFile) {
    try {
      String[] files = ContextUtils.getContext().getAssets().list(oldDirectory);
      if (files == null || files.length == 0) {
        return true;
      }
      for (String file : files) {
        String newFilePath = newDirectory + "/" + file;
        String oldFilePath = oldDirectory + "/" + file;
        File newFileParentPath = new File(newDirectory);
        newFileParentPath.mkdirs();
        copyAssetsFile(oldFilePath, newFilePath, ttsModel, deleteOldFile, ignoreSameSizeFile);
      }
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public static boolean copyAssetsFile(String oldPath, String newPath, boolean isTtsModel,
      boolean deleteOldFile, boolean ignoreSameSizeFile) {
    Log.d(TAG, "copy path:" + oldPath + " new path:" + newPath);
    RandomAccessFile randomFile = null;
    InputStream inputStream = null;
    try {
      File fileNew = new File(newPath);
      if (fileNew.exists()) {
        if (fileNew.isFile()) {
          if (isTtsModel) {
            if (isSameTtsFile(oldPath, newPath)) {
              return true;
            }
          }
          if (ignoreSameSizeFile) {
            if (isSameSizeFile(oldPath, newPath)) {
              return true;
            }
          }
          if (deleteOldFile) {
            fileNew.delete();
          } else {
            return true;
          }
        } else {
          fileNew.delete();
        }
      }
      fileNew.getParentFile().mkdirs();
      inputStream = ContextUtils.getContext().getAssets().open(oldPath);
      randomFile = new RandomAccessFile(fileNew, "rw");
      byte[] buffer = new byte[4096];
      int read;
      while ((read = inputStream.read(buffer)) != -1) {
        randomFile.write(buffer, 0, read);
      }
      return true;
    } catch (Exception e) {
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

  private static boolean isSameSizeFile(String oldPath, String newPath) {
    try {
      InputStream inputStreamOld = ContextUtils.getContext().getAssets().open(oldPath);
      long oldFileSize = inputStreamOld.available();
      File destFile = new File(newPath);
      long destFileSize = destFile.length();
      LogMgr.d(TAG, "oldFileSize:" + oldFileSize + ",destFileSize:" + destFileSize);
      return oldFileSize == destFileSize;
    } catch (Exception e) {
      return false;
    }
  }

  private static boolean isSameTtsFile(String oldPath, String newPath) {
    try {
      byte[] inHeaderByte = new byte[256];
      byte[] destHeaderByte = new byte[256];
      InputStream inputStreamOld = ContextUtils.getContext().getAssets().open(oldPath);
      inputStreamOld.read(inHeaderByte);
      FileInputStream destFileStream = new FileInputStream(newPath);
      destFileStream.read(destHeaderByte);
      inputStreamOld.close();
      destFileStream.close();
      if (Arrays.equals(inHeaderByte, destHeaderByte)) {
        return true;
      }
      return false;
    } catch (Exception e) {
      return false;
    }
  }
}
