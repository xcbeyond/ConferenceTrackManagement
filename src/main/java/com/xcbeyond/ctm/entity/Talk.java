package com.xcbeyond.ctm.entity;

import com.xcbeyond.ctm.constant.Constant;

import java.util.Date;

/**
 * 报告的实体类
 * @Auther: xcbeyond
 * @Date: 2019/11/30 21:33
 */
public class Talk extends BaseEntity {
    private String title;       // 报告标题
    private Integer duration;   // 时长，单位：分钟
    private Date startTime;     // 开始时间
    private Date endTime;       // 结束时间

    public Talk() {
    }

    public Talk(String title, Integer duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return title + " " + (duration == 5 ? Constant.LIGHTNING : duration + Constant.MIN);
    }
}
