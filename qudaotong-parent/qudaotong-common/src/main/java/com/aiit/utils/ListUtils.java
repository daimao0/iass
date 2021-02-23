package com.aiit.utils;

import java.util.List;
import java.util.stream.Collectors;

public class ListUtils {
    /**
     * List去重
     * @param list
     * @return
     */
    public static List<String> delRepeat(List<String> list){
        if (list==null || list.size()<=0){
            return list;
        }
        return list.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 将list ->[1,2,3,4,5]转化为String->"1,2,3,4,5"
     */
    public static String formatSimpleStr(List<String> list){
        if (list==null||list.size()<=0){
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str:list){
            stringBuilder.append(str).append(',');
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }

    /**
     * 判断list不为空
     */
    public static boolean isEmpty(List list){
        return list == null || list.size() <= 0;
    }
}
