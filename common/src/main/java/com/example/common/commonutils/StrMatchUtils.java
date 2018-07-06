package com.example.common.commonutils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrMatchUtils {

    static final String REGEX_NUM_LETTER_8_16 = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
    static final String REGEX_NUM_LETTER = "[0-9]|[A-Z]|[a-z]";
    //过滤特殊符号
    static final String REGEX_BAD_LETTER = "\"[/\\\\:*?<>|,，\\\"\\n\\t]\"";
    //过滤Emoji表情
    static final String REGEX_EMO = "[^\\u0000-\\uFFFF]";
    //过滤Emoji表情和颜文字
    static final String REGEX_EMO_FACE_CODE = "[\\ud83c\\udc00-\\ud83c\\udfff]|[\\ud83d\\udc00-\\ud83d\\udfff]|[\\u2600-\\u27ff]|[\\ud83e\\udd00-\\ud83e\\uddff]|[\\u2300-\\u23ff]|[\\u2500-\\u25ff]|[\\u2100-\\u21ff]|[\\u0000-\\u00ff]|[\\u2b00-\\u2bff]|[\\u2d06]|[\\u3030]";

    public static boolean matchNumLetter(String content) {
        Pattern pattern = Pattern.compile(REGEX_NUM_LETTER);
        Matcher matcher = pattern.matcher(content);
        return matcher.find();
    }

    public static boolean matchEmoAndBadLetter(String content) {
        Pattern pattern1 = Pattern.compile(REGEX_EMO_FACE_CODE);
        Matcher matcher1 = pattern1.matcher(content);

        Pattern pattern2 = Pattern.compile(REGEX_BAD_LETTER);
        Matcher matcher2 = pattern2.matcher(content);
        return matcher1.find() || matcher2.find();
    }
}
