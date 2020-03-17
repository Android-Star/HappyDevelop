package com.unisound.sdk.service.utils;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.util.Hashtable;

public class QRCodeUtils {
  private QRCodeUtils() {
  }

  public static Bitmap createQRCodeBitmap(String content, int width, int height) {
    return createQRCodeBitmap(content, width, height, "UTF-8", "H", "2", Color.BLACK, Color.WHITE);
  }

  @Nullable public static Bitmap createQRCodeBitmap(String content, int width, int height,
      @Nullable String characterSet, @Nullable String errorCorrection, @Nullable String margin,
      @ColorInt int colorBlack, @ColorInt int colorWhite) {
    if (TextUtils.isEmpty(content)) {
      return null;
    }
    if (width < 0 || height < 0) {
      return null;
    }
    try {
      Hashtable<EncodeHintType, String> hints = new Hashtable<>();
      if (!TextUtils.isEmpty(characterSet)) {
        hints.put(EncodeHintType.CHARACTER_SET, characterSet);
      }
      if (!TextUtils.isEmpty(errorCorrection)) {
        hints.put(EncodeHintType.ERROR_CORRECTION, errorCorrection);
      }
      if (!TextUtils.isEmpty(margin)) {
        hints.put(EncodeHintType.MARGIN, margin);
      }
      BitMatrix bitMatrix =
          new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
      int[] pixels = new int[width * height];
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          if (bitMatrix.get(x, y)) {
            pixels[y * width + x] = colorBlack;
          } else {
            pixels[y * width + x] = colorWhite;
          }
        }
      }
      Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
      bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
      return bitmap;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
