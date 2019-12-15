package com.xcbeyond.ctm.comparator;

import com.xcbeyond.ctm.entity.Talk;

import java.util.Comparator;

/**
 * Talk比较器
 * @Auther: xcbeyond
 * @Date: 2019/12/1 00:38
 */
public class TalkCompartor implements Comparator<Talk> {

    /**
     * 降序排序
     * @param talk1
     * @param talk2
     * @return
     */
    @Override
    public int compare(Talk talk1, Talk talk2) {
        if (talk1.getDuration() < talk2.getDuration()) {
            return 1;
        } else {
            return -1;
        }
    }
}
