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
    public Conference readData(String filename, boolean idDispaly) {
        List<String> inputs = FileUtils.readFile(filename);
        if (idDispaly) {
            inputDisplay(inputs);
        }

        Parser<Talk, List<String>> parser = new TalkParser();
        List<Talk> talks = parser.execute(inputs);

        Collections.sort(talks, Comparator.comparingInt(Talk::getDuration));

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
        inputs.forEach(System.out::println);
        System.out.println();
    }

    @Override
    public int schedule(int trackNo, Conference conference, int talkIndex) {
        scheduleTalks.add("Track:" + (trackNo + 1));

        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR, 9);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.AM_PM, Calendar.AM);

        List<Talk> talks = conference.getTalks();
        String schedule;
        // 上午
        talkIndex = subSchedule(talkIndex, Constant.MORNING_DURATION, cal, talks);

        // 午饭时段
        schedule = "12:00PM" + " " + "Lunch";
        scheduleTalks.add(schedule);
        cal.add(Calendar.MINUTE, 60);

        talkIndex++;
        // 下午
        talkIndex = subSchedule(talkIndex, Constant.AFTERNOON_DURATION, cal, talks);

        // 网络会议
        schedule = "05:00PM" + " " + "Networking Event";
        scheduleTalks.add(schedule + "\n");

        return talkIndex;
    }

    private int subSchedule(int talkIndex, int duration, Calendar cal, List<Talk> talks) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa",Locale.ENGLISH);

        for (; talkIndex < talks.size(); talkIndex++) {
            int currDuration = talks.get(talkIndex).getDuration();
            if (duration >= currDuration) {
                duration = duration - currDuration;

                String schedule = sdf.format(cal.getTime()).replaceAll(" ", "") + " "
                        + talks.get(talkIndex).getTitle() + " " + currDuration + "min";
                scheduleTalks.add(schedule);
                cal.add(Calendar.MINUTE, currDuration);
            }
            if (duration < currDuration) {
                break;
            }


            if (duration > 0) {
                continue;
            }

            break;
        }

        return talkIndex;
    }

    @Override
    public void displaySchedule() {
        System.out.println("Test output:");
        scheduleTalks.forEach(schedule -> System.out.println(schedule));
    }
}