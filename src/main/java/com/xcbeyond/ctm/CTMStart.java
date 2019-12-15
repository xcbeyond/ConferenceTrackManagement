package com.xcbeyond.ctm;

import com.xcbeyond.ctm.constant.Constant;
import com.xcbeyond.ctm.entity.Conference;
import com.xcbeyond.ctm.manager.ConferenceManager;
import com.xcbeyond.ctm.manager.Manager;

/**
 * CTM程序入口
 * @Auther: xcbeyond
 * @Date: 2019/11/30 22:24
 */
public class CTMStart {

    public static void main (String[] agrs) {
        Manager manager = new ConferenceManager();
        Conference conference = manager.readData(Constant.INPUT_FILE_NAME, true);

        int talkIndex = 0;
        for (int trackNo = 0;trackNo< conference.getTrackCount(); trackNo++) {
            talkIndex = manager.schedule(trackNo, conference, talkIndex);
        }

        manager.displaySchedule();
    }
}