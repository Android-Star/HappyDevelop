package com.unisound.sdk.service.utils.hardware;

import android.os.Environment;
import com.htfyun.serialport.ServoSingleton;
import com.htfyun.serialport.uartservo.OnServoListener;
import com.htfyun.serialport.uartservo.ServoControlAttr;
import com.htfyun.serialport.uartservo.ServoErrorAttr;
import com.htfyun.serialport.uartservo.ServoInterestedInfo;
import com.unisound.sdk.service.utils.HandlerUtils;
import com.unisound.sdk.service.utils.LogMgr;
import java.io.File;
import java.util.List;

public class HardWare {
  private static final String TAG = "HardWare";
  private static int position = Integer.MAX_VALUE;
  private static boolean isMoving = false;
  private static boolean isInTurnToAngle;
  private static long lastUpdateTime = 0;
  private static final int MIN_AREA = 30;
  private static final int MAX_AREA = 330;

  private static final String LOG_PATH =
      Environment.getExternalStorageDirectory() + File.separator + "serialLog";

  public static void setIsInTurnToAngle(boolean isInTurnToAngle) {
    if (isInTurnToAngle) {
      HardWare.isInTurnToAngle = isInTurnToAngle;
      lastUpdateTime = System.currentTimeMillis();
      LogMgr.d(TAG, "setIsInTurnToAngle:" + isInTurnToAngle);
    } else {
      if (HardWare.isInTurnToAngle && System.currentTimeMillis() - lastUpdateTime > 500) {
        HardWare.isInTurnToAngle = isInTurnToAngle;
        LogMgr.d(TAG, "setIsInTurnToAngle:" + isInTurnToAngle);
      }
    }
  }

  public static boolean isIsInTurnToAngle() {
    return isInTurnToAngle;
  }

  private HardWare() {

  }

  private static OnServoListener servoListener = new OnServoListener() {
    @Override
    public void onServoListener(List<ServoErrorAttr> errorAttrList, ServoControlAttr controlAttr,
        ServoControlAttr.Instruction instruction, ServoInterestedInfo info) {
      if (errorAttrList != null && !errorAttrList.isEmpty()) {
        for (ServoErrorAttr errorAttr : errorAttrList) {
          LogMgr.d(TAG, " errorAttr = " + errorAttr);
        }
      }
      if (controlAttr == null) {
        LogMgr.e(TAG, " controlAttr is null");
        return;
      }
      final ServoInterestedInfo interestedInfo = info;
      final ServoControlAttr cAttr = controlAttr;
      switch (cAttr) {
        case angle_area_min:
          break;
        case angle_area_max:
          break;
        case angular_velocity:
          break;
        case position:
          position = interestedInfo.getPosition();
          LogMgr.e(TAG, "position:" + position);
          setIsMoving(interestedInfo.isMoving());
          break;
        case move_status:
          setIsMoving(interestedInfo.isMoving());
          break;
        case set_feedback_time:
          break;
        default:
          break;
      }
    }
  };

  private static void deleteLogFile() {
    LogMgr.d(TAG, "LOG_PATH:" + LOG_PATH);
    File file = new File(LOG_PATH);
    if (file.exists() && file.isDirectory()) {
      File[] fileArray = file.listFiles();
      if (fileArray != null && fileArray.length > 0) {
        for (File file1 : fileArray) {
          LogMgr.d(TAG, "deleteFile:" + file1.getAbsolutePath());
          file1.delete();
        }
      }
    }
  }

  public static void init() {
    deleteLogFile();
    ServoSingleton.setDebuggable(false);
    ServoSingleton.INSTANCE.init();
    ServoSingleton.INSTANCE.getServoControl().registerServoListener(servoListener);
    HandlerUtils.getMainHandler().postDelayed(new Runnable() {
      @Override public void run() {
        ServoSingleton.INSTANCE.getServoControl().setAngleAreaMin(MIN_AREA);
        ServoSingleton.INSTANCE.getServoControl().setAngleAreaMax(MAX_AREA);
        ServoSingleton.INSTANCE.getServoControl().setFeedbackTime_100ms(2);
        ServoSingleton.INSTANCE.getServoControl().getPosition();
      }
    }, 300);
  }

  public static void turnToMicAngle(int angle) {
    turnToMicAngle(angle, 90);
  }

  public static void turnToMicAngle(int angle, int speed) {
    LogMgr.d(TAG, "turnToMicAngle:" + angle + ",position:" + position + ",speed:" + speed);
    int rotateAngle = 90 - angle;
    if (position != Integer.MAX_VALUE) {
      int rotatePosition = position + rotateAngle;
      rotatePosition = (rotatePosition + 360) % 360;
      LogMgr.d(TAG, "rotatePosition:" + rotatePosition + ",position:" + position);
      if (Math.abs(rotatePosition - position) > 10) {
        setIsInTurnToAngle(true);
        ServoSingleton.INSTANCE.getServoControl().setAngularVelocity(speed);
        ServoSingleton.INSTANCE.getServoControl().setPosition(getPosition(rotatePosition));
      }
    }
  }

  private static int getPosition(int position) {
    LogMgr.d(TAG, "getPosition before:" + position);
    if (position < MIN_AREA) {
      position = MIN_AREA;
    } else if (position > MAX_AREA) {
      position = MAX_AREA;
    }
    LogMgr.d(TAG, "getPosition after:" + position);
    return position;
  }

  public static void setIsMoving(boolean isMoving) {
    LogMgr.d(TAG, "isMoving:" + isMoving);
    HardWare.isMoving = isMoving;
    if (!isMoving) {
      setIsInTurnToAngle(false);
    }
  }

  public static void stop() {
    LogMgr.d(TAG, "stop");
    setIsInTurnToAngle(false);
    ServoSingleton.INSTANCE.getServoControl().servoStop();
  }

  public static void left() {
    LogMgr.d(TAG, "left turn face isMoving:" + isMoving);
    ServoSingleton.INSTANCE.getServoControl().setAngularVelocity(15);
    ServoSingleton.INSTANCE.getServoControl().turnLeft();
  }

  public static void left(int relativePosition) {
    LogMgr.d(TAG, "left turn face isMoving:" + isMoving + " relativePosition:" + relativePosition);
    ServoSingleton.INSTANCE.getServoControl().setAngularVelocity(15);
    ServoSingleton.INSTANCE.getServoControl().turnLeft(relativePosition);
  }

  public static void right() {
    LogMgr.d(TAG, "right turn face isMoving:" + isMoving);
    ServoSingleton.INSTANCE.getServoControl().setAngularVelocity(15);
    ServoSingleton.INSTANCE.getServoControl().turnRight();
  }

  public static void right(int relativePosition) {
    LogMgr.d(TAG, "right turn face isMoving:" + isMoving + " relativePosition:" + relativePosition);
    ServoSingleton.INSTANCE.getServoControl().setAngularVelocity(15);
    ServoSingleton.INSTANCE.getServoControl().turnRight(relativePosition);
  }

  public static HardWareState getHardWareState() {
    LogMgr.d(TAG, "min:" + MIN_AREA + " max:" + MAX_AREA + ",position:" + position);
    if (position <= MIN_AREA + 20) {
      return HardWareState.min;
    }
    if (position >= MAX_AREA - 20) {
      return HardWareState.max;
    }
    return HardWareState.normal;
  }
}
