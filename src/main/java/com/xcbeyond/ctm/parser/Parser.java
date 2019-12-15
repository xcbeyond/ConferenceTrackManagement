package com.xcbeyond.ctm.parser;

import java.util.List;

/**
 * 解析器
 * @Auther: xcbeyond
 * @Date: 2019/12/2 17:07
 */
public interface Parser<T1,T2> {
    /**
     * 解析执行
     * @param data 解析数据
     * @return
     */
    List<T1> execute(T2 data);
}
