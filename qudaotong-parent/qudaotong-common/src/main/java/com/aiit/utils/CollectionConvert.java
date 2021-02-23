package com.aiit.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

/**
 * @author 呆毛
 */
public class CollectionConvert {
    /**
     * 将 “10001,10002,10003”这样的数据转化为HashSet<String>
     */
    public static HashSet<String> strToHashSet(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        //将集合的toString形态转化为Set
        if (str.charAt(0) == '[') {
            String substring = str.substring(0, str.length() - 1);
            String[] split = substring.split(", ");
            return new HashSet<>(Arrays.asList(split));
        }
        String[] split = str.split(",");
        return new HashSet<>(Arrays.asList(split));
    }


    /**
     * 将list<String> 转化为 String
     */
    public static String collectionToString(Collection<String> collection) {
        if (collection.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String str : collection) {
            sb.append(str).append(',');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }


}
