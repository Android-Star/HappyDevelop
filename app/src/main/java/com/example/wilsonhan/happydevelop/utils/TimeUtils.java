package com.example.wilsonhan.happydevelop.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtils {

  public static final String NORMAL_FORMAT = "yyyy-MM-dd HH:mm:ss";
  public static final String NORMAL_DATE_FORMAT = "yyyy-MM-dd";
  public static final String NORMAL_DATE_FORMAT1 = "yyyy/MM/dd";
  public static final String NORMAL_TIME_FORMAT = "HH:mm";

  private TimeUtils() {

  }

  public static String getStringByFormat(long milliseconds, String format) {
    String thisDateTime = null;
    try {
      SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
      thisDateTime = mSimpleDateFormat.format(milliseconds);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return thisDateTime;
  }

  public static String getCurrentDate(String format) {
    String curDateTime = null;
    try {
      SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
      Calendar c = new GregorianCalendar();
      curDateTime = mSimpleDateFormat.format(c.getTime());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return curDateTime;
  }

  public static String getCurrentDateWithWeek(String str) {
    String curDateTime = null;
    try {
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeInMillis(System.currentTimeMillis());
      curDateTime = String.format(str, formatMonthNumber(calendar.get(Calendar.MONTH) + 1),
          formatMonthNumber(calendar.get(Calendar.DAY_OF_MONTH)),
          formatWeekNumber(calendar.get(Calendar.DAY_OF_WEEK) - 1));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return curDateTime;
  }

  private static String formatWeekNumber(int curWeekNum) {
    switch (curWeekNum) {
      case 0:
        return "日";
      case 1:
        return "一";
      case 2:
        return "二";
      case 3:
        return "三";
      case 4:
        return "四";
      case 5:
        return "五";
      case 6:
        return "六";
      default:
        return "";
    }
  }

  private static String formatMonthNumber(int curMonthNum) {
    String result;
    if (curMonthNum < 10) {
      result = "0" + curMonthNum;
    } else {

      result = "" + curMonthNum;
    }
    return result;
  }

  public static Date getDateByFormat(String strDate, String format) {
    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
    Date date = null;
    try {
      date = mSimpleDateFormat.parse(strDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }

  public static long getDateLongMills(String format, String dateStr) {
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    Date date = null;
    try {
      date = sdf.parse(dateStr);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    long longDate = date.getTime();
    return longDate;
  }

  public static String getAge(String str, String format) {
    Date dateByFormat = getDateByFormat(str, format);
    Calendar birthday = Calendar.getInstance();
    birthday.setTime(dateByFormat);
    Calendar now = Calendar.getInstance();
    int day = now.get(Calendar.DAY_OF_MONTH) - birthday.get(Calendar.DAY_OF_MONTH);
    int month = now.get(Calendar.MONTH) - birthday.get(Calendar.MONTH);
    int year = now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
    //按照减法原理，先day相减，不够向month借；然后month相减，不够向year借；最后year相减。
    if (day < 0) {
      month -= 1;
      now.add(Calendar.MONTH, -1);    //得到上一个月，用来得到上个月的天数。
      day = day + now.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    if (month < 0) {
      month = (month + 12) % 12;
      year--;
    }
    return year + "岁" + month + "月" + day + "天";
  }

  public static int getOffectDay(long date1, long date2) {
    Calendar calendar1 = Calendar.getInstance();
    calendar1.setTimeInMillis(date1);
    Calendar calendar2 = Calendar.getInstance();
    calendar2.setTimeInMillis(date2);
    //先判断是否同年
    int y1 = calendar1.get(Calendar.YEAR);
    int y2 = calendar2.get(Calendar.YEAR);
    int d1 = calendar1.get(Calendar.DAY_OF_YEAR);
    int d2 = calendar2.get(Calendar.DAY_OF_YEAR);
    int maxDays = 0;
    int day = 0;
    if (y1 - y2 > 0) {
      maxDays = calendar2.getActualMaximum(Calendar.DAY_OF_YEAR);
      day = d1 - d2 + maxDays * (y1 - y2);
    } else if (y1 - y2 < 0) {
      maxDays = calendar1.getActualMaximum(Calendar.DAY_OF_YEAR);
      day = d1 - d2 + maxDays * (y2 - y1);
    } else {
      day = d1 - d2;
    }
    return day;
  }

  public static int getOffsectHour(long date1, long date2) {
    Calendar calendar1 = Calendar.getInstance();
    calendar1.setTimeInMillis(date1);
    Calendar calendar2 = Calendar.getInstance();
    calendar2.setTimeInMillis(date2);
    int h1 = calendar1.get(Calendar.HOUR_OF_DAY);
    int h2 = calendar2.get(Calendar.HOUR_OF_DAY);
    int h = 0;
    int day = getOffectDay(date1, date2);
    h = h1 - h2 + day * 24;
    return h;
  }

  public static int getOffsectMinutes(long date1, long date2) {
    Calendar calendar1 = Calendar.getInstance();
    calendar1.setTimeInMillis(date1);
    Calendar calendar2 = Calendar.getInstance();
    calendar2.setTimeInMillis(date2);
    int m1 = calendar1.get(Calendar.MINUTE);
    int m2 = calendar2.get(Calendar.MINUTE);
    int h = getOffsectHour(date1, date2);
    int m = 0;
    m = m1 - m2 + h * 60;
    return m;
  }

  public static String getOffsetTime(long date1, long date2) {
    Calendar calendar1 = Calendar.getInstance();
    calendar1.setTimeInMillis(date1);
    Calendar calendar2 = Calendar.getInstance();
    calendar2.setTimeInMillis(date2);
    int m1 = calendar1.get(Calendar.MINUTE);
    int m2 = calendar2.get(Calendar.MINUTE);
    int h = getOffsectHour(date1, date2);
    if (h > 0) {
      return h + "小时前";
    } else {
      return (m1 - m2) + "分钟前";
    }
  }

  public static String getOffsetTime(String time1, String time2) {
    Date date1 = getDateByFormat(time1, NORMAL_FORMAT);
    Date date2 = getDateByFormat(time2, NORMAL_FORMAT);
    Calendar calendar1 = Calendar.getInstance();
    calendar1.setTimeInMillis(date1.getTime());
    Calendar calendar2 = Calendar.getInstance();
    calendar2.setTimeInMillis(date2.getTime());
    int m1 = calendar1.get(Calendar.MINUTE);
    int m2 = calendar2.get(Calendar.MINUTE);
    int h = getOffsectHour(date1.getTime(), date2.getTime());
    if (h > 0) {
      return h + "小时前";
    } else {
      return (m1 - m2) + "分钟前";
    }
  }

  public static String getOffsetTime(String time2) {
    long date1 = System.currentTimeMillis();
    Date date2 = getDateByFormat(time2, NORMAL_FORMAT);
    long offset = date1 - date2.getTime();
    long day = offset / (24 * 60 * 60 * 1000);
    if (day > 0) {
      return day + "天前";
    } else {
      long hour = offset / (60 * 60 * 1000);
      if (hour > 0) {
        return hour + "小时前";
      } else {
        long minute = offset / (60 * 1000);
        if (minute > 0) {
          return minute + "分钟前";
        } else {
          return "刚刚";
        }
      }
    }
  }

  public static boolean isOffsetHour12(String time2) {
    long date1 = System.currentTimeMillis();
    Date date2 = getDateByFormat(time2, NORMAL_FORMAT);
    long offset = date1 - date2.getTime();
    long day = offset / (24 * 60 * 60 * 1000);
    if (day > 0) {
      return true;
    } else {
      long hour = offset / (60 * 60 * 1000);
      if (hour > 0) {
        return hour > 12;
      } else {
        return false;
      }
    }
  }

  public static long getTimeOffset(String time) {
    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(NORMAL_TIME_FORMAT);
    Date date;
    try {
      date = mSimpleDateFormat.parse(time);
      Calendar calendar = Calendar.getInstance();
      calendar.set(Calendar.HOUR_OF_DAY, date.getHours());
      calendar.set(Calendar.MINUTE, date.getMinutes());
      long current = System.currentTimeMillis();
      if (calendar.getTimeInMillis() > current) {
        return calendar.getTimeInMillis() - current;
      } else {
        return 24 * 60 * 60 * 1000 - current + calendar.getTimeInMillis();
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return 0;
  }

  public static String getTimeBySecond(long second) {
    if (second < 60) {
      return second + "秒";
    } else if (second < 3600) {
      return (second / 60) + "分钟" + (second % 60) + "秒";
    } else {
      return (second / 3600) + "小时" + (second % 3600 / 60) + "分钟" + (second % 3600 % 60) + "秒";
    }
  }

  public static boolean isToday(Date date) {
    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(NORMAL_DATE_FORMAT);
    String result = mSimpleDateFormat.format(date);
    return result.equals(mSimpleDateFormat.format(new Date()));
  }

  public static String getRecordTime(String strDate) {
    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(NORMAL_FORMAT);
    SimpleDateFormat dateFormat = new SimpleDateFormat(NORMAL_DATE_FORMAT1);
    SimpleDateFormat timeFormat = new SimpleDateFormat(NORMAL_TIME_FORMAT);
    try {
      Date date = mSimpleDateFormat.parse(strDate);
      if (isToday(date)) {
        if (getHour(date) > 12) {
          return "下午 " + timeFormat.format(date);
        } else {
          return "上午 " + timeFormat.format(date);
        }
      } else if (isThisWeek(date.getTime())) {
        return getWeek(date);
      } else {
        return dateFormat.format(date);
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return "";
  }

  public static String getStringTime(String strDate) {
    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(NORMAL_FORMAT);
    SimpleDateFormat timeFormat = new SimpleDateFormat(NORMAL_TIME_FORMAT);
    try {
      Date date = mSimpleDateFormat.parse(strDate);
      if (getHour(date) > 12) {
        return "下午 " + timeFormat.format(date);
      } else {
        return "上午 " + timeFormat.format(date);
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return "";
  }

  public static int getHour(Date date) {
    if (date == null) {
      return 0;
    }
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar.get(Calendar.HOUR_OF_DAY);
  }

  public static boolean isThisWeek(long time) {
    Calendar calendar = Calendar.getInstance();
    int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
    calendar.setTime(new Date(time));
    int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
    return paramWeek == currentWeek;
  }

  public static String getWeek(Date date) {
    String[] weeks = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
    if (week_index < 0) {
      week_index = 0;
    }
    return weeks[week_index];
  }
}
