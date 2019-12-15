package com.xcbeyond.ctm.entity;

import java.util.List;

/**
 * @Auther: xcbeyond
 * @Date: 2019/12/2 00:37
 */
public class Conference extends BaseEntity {
    // 报告集合
    private List<Talk> talks;
    // 会议总时间
    private Integer totalDuration;
    // 日程总数
    private Integer trackCount;

    public Conference() {
    }

    public Conference(List<Talk> talks, Integer totalDuration, Integer trackCount) {
        this.talks = talks;
        this.totalDuration = totalDuration;
        this.trackCount = trackCount;
    }

    public List<Talk> getTalks() {
        return talks;
    }

    public void setTalks(List<Talk> talks) {
        this.talks = talks;
    }

    public Integer getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Integer totalDuration) {
        this.totalDuration = totalDuration;
    }

    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }

    @Override
    public String toString() {
        return "Conference{" +
                "talks=" + talks +
                ", totalDuration=" + totalDuration +
                ", trackCount=" + trackCount +
                '}';
    }
}
