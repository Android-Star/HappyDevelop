package com.unisound.sdk.service.utils;

import android.graphics.Color;
import android.text.Spannable;
import android.text.TextPaint;
import android.view.View;
import com.unisound.sdk.service.utils.callback.LinkTextCallback;

public class LinkTextUtils {
  private LinkTextUtils() {

  }

  public static String formatString(String text) {
    String result = "";
    String strExclude = ".?,。";  //排除的标点符号
    String[] strArray = text.split(" ");
    for (String str : strArray) {
      boolean isAdd = true;
      for (char c : strExclude.toCharArray()) {
        if (str.contains(String.valueOf(c))) {
          isAdd = false;
          str = str.replace(String.valueOf(c), " ");
          String[] cList = str.split(" ");
          if (cList.length == 0) {
            result += c;
          } else {
            for (String cStr : cList) {
              if (!cStr.equals(cList[cList.length - 1]) || cList.length == 1) {
                result += " <a href=\"" + cStr + "\">" + cStr + "</a>" + String.valueOf(c);
              } else {
                result += " <a href=\"" + cStr + "\">" + cStr + "</a>";
              }
            }
          }
        }
      }
      if (isAdd) {
        result += " <a href=\"" + str + "\">" + str + "</a>";
      }
    }
    return result;
  }

  public static android.text.SpannableStringBuilder getTextSpannable(CharSequence text,
      final LinkTextCallback listener, final String color) {
    android.text.SpannableStringBuilder style = null;
    if (text instanceof Spannable) {
      int end = text.length();
      Spannable sp = (Spannable) text;
      android.text.style.URLSpan[] urls = sp.getSpans(0, end, android.text.style.URLSpan.class);
      style = new android.text.SpannableStringBuilder(text);
      style.clearSpans();
      for (final android.text.style.URLSpan url : urls) {
        style.setSpan(new android.text.style.ClickableSpan() {
          @Override public void onClick(View widget) {
            listener.onLinkTextItemClick(url.getURL());
          }

          @Override public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
            ds.setColor(Color.parseColor(color));
          }
        }, sp.getSpanStart(url), sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
      }
    }
    return style;
  }
}
