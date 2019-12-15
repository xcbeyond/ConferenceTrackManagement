package com.xcbeyond.ctm.manager;

import com.xcbeyond.ctm.comparator.TalkCompartor;
import com.xcbeyond.ctm.constant.Constant;
import com.xcbeyond.ctm.entity.Conference;
import com.xcbeyond.ctm.entity.Talk;
import com.xcbeyond.ctm.parser.Parser;
import com.xcbeyond.ctm.parser.TalkParser;
import com.xcbeyond.ctm.util.FileUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Auther: xcbeyond
 * @Date: 2019/12/1 21:11
 */
public class ConferenceManager implements Manager{
    private List<String> scheduleTalks = new ArrayList<>();

    @Override
    public Conference readData(String filename, boolean isInput) {
        List<String> inputs = FileUtils.readFile(filename);
        if (isInput) {
            inputDisplay(inputs);
        }

        Parser parser = new TalkParser();
        List<Talk> talks = parser.execute(inputs);

        Collections.sort(talks, new TalkCompartor());

        // 计算会议总时间
        Integer totalDuration = 0;
        for (Talk talk : talks) {
            totalDuration += talk.getDuration();
        }

        Integer trackCount;
        if (0 == totalDuration % Constant.DAY_DURATION) {
            trackCount = totalDuration / Constant.DAY_DURATION;
        } else {
            trackCount = totalDuration / Constant.DAY_DURATION + 1;
        }

        return new Conference(talks, totalDuration, trackCount);
    }

    /**
     * 显示输入内容
     * @param inputs
     */
    private void inputDisplay(List<String> inputs) {
        System.out.println("Test input:");
        inputs.forEach(line -> System.out.println(line));
        System.out.println();
    }

    @Override
    public int schedule(int trackNo, Conference conference, int talkIndex) {
        scheduleTalks.add("Track:" + (trackNo + 1));

        int morningDuration = 180;
        int afternoonDuration = 240;

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa",Locale.ENGLISH);
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR, 9);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.AM_PM, Calendar.AM);

        List<Talk> talks = conference.getTalks();
        String schedule;
        // 上午
        for (;talkIndex<talks.size();talkIndex++) {
            if (morningDuration >= talks.get(talkIndex).getDuration()) {
                morningDuration = morningDuration -  talks.get(talkIndex).getDuration();

                schedule = sdf.format(cal.getTime()).replaceAll(" ", "") + " " + talks.get(talkIndex).getTitle() + " " + talks.get(talkIndex).getDuration() + "min";
                scheduleTalks.add(schedule);
                cal.add(Calendar.MINUTE, talks.get(talkIndex).getDuration());
            }

            if (morningDuration < talks.get(talkIndex).getDuration()) {
                break;
            }

            if (morningDuration > 0) {
                continue;
            }

            if (morningDuration <= 0) {
                break;
            }
        }

        // 午饭时段
        schedule = "12:00PM" + " " + "Lunch";
        scheduleTalks.add(schedule);
        cal.add(Calendar.MINUTE, 60);

        talkIndex++;
        // 下午
        for (; talkIndex < talks.size();talkIndex++) {
            if (afternoonDuration >= talks.get(talkIndex).getDuration()) {
                afternoonDuration = afternoonDuration -  talks.get(talkIndex).getDuration();
                schedule = sdf.format(cal.getTime()).replaceAll(" ", "") + " " + talks.get(talkIndex).getTitle() + " " + talks.get(talkIndex).getDuration() + "min";
                scheduleTalks.add(schedule);
                cal.add(Calendar.MINUTE, talks.get(talkIndex).getDuration());
            }
            if (afternoonDuration < talks.get(talkIndex).getDuration()) {
                break;
            }
            if (afternoonDuration > 0) {
                continue;
            }
            if (afternoonDuration <= 0) {
                break;
            }
        }

        schedule = "05:00PM" + " " + "Networking Event";
        scheduleTalks.add(schedule + "\n");

        return talkIndex;
    }

    @Override
    public void displaySchedule() {
        System.out.println("Test output:");
        scheduleTalks.forEach(schedule -> System.out.println(schedule));
    }
}