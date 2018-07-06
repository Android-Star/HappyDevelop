package com.example.wilsonhan.happydevelop.utils;

/**
 * Created by wilsonhan on 2017/10/11.
 */

public class RandomUUIDUtils {

    public static String getRandomValue(int numSize) {
        String str = "";
        for (int i = 0; i < numSize; i++) {
            char temp = 0;
            int key = (int) (Math.random() * 2);
            switch (key) {
                case 0:
                    temp = (char) (Math.random() * 10 + 48);//产生随机数字
                    break;
                case 1:
                    temp = (char) (Math.random() * 6 + 'a');//产生a-f
                    break;
                default:
                    break;
            }
            str = str + temp;
        }
        return str;
    }
}
