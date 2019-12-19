package com.xcbeyond.ctm.util;

/**
 * 字符串工具类
 * @Auther: xcbeyond
 * @Date: 2019/12/2 17:04
 */
public class  StringUtils {

    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }
}
