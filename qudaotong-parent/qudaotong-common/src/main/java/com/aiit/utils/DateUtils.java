package com.aiit.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 呆毛
 * @date 2021/1/13 15:48
 */

public class DateUtils {
    /**
     * 将Date类型转化为13位时间戳，若参数date为null，则返回null
     *
     * @param date
     * @return
     */
    public static Long convertTimestamp(Date date) {
        if (date == null) {
            return null;
        }
        return date.getTime();
    }

    /**
     * 将传入的字符串按照连续的数字分割，区分出年月日时分秒
     *
     * @param str 输入的字符
     * @return
     */
    public static Date toDate(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        //定义一个数组用来装载年月日时分秒
        StringBuilder[] stringBuilders = new StringBuilder[6];
        for (int i = 0; i < stringBuilders.length; i++) {
            stringBuilders[i] = new StringBuilder();
        }

        //表示当前解析的数组是年还是月还是日...
        int currentPosition = 0;
        //表示当前分割的字符连续次数
        int continuousCh = 0;

        //遍历字符串
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                stringBuilders[currentPosition].append(str.charAt(i));
            }
            if (!Character.isDigit(str.charAt(i))) {
                if (continuousCh == 0) {
                    currentPosition++;
                }
                continuousCh++;
            } else {
                continuousCh = 0;
            }
        }
        StringBuilder resultStr = new StringBuilder();
        for (StringBuilder stringBuilder : stringBuilders) {
            resultStr.append(stringBuilder).append("-");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss-");
        try {
            return sdf.parse(resultStr.toString());
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 比较时间大小
     * 若第一个参数小于第二个参数：返回 -1
     * 若第一个参数等于第二个参数：返回 0
     * 若第一个参数大于第二个参数：返回 1
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compare(Date date1, Date date2) {
        return Long.compare(date1.getTime(), date2.getTime());
    }
}
