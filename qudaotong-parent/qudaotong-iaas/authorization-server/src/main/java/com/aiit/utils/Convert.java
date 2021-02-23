package com.aiit.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Convert {

    /**
     * 将String "1,2,3,4,5,6" -> List {1,2,3,4,5,6}
     * String用','隔开
     * @param str
     * @return
     */
    public static List<String> toList(String str){
        if (StringUtils.isEmpty(str)){
            return null;
        }
        String[] split = str.split(",");
        return new ArrayList<>(Arrays.asList(split));
    }
}
