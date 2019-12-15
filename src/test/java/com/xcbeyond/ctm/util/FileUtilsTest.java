package com.xcbeyond.ctm.util;

import com.xcbeyond.ctm.constant.Constant;
import org.junit.Test;

import java.util.List;

/**
 * @Auther: xcbeyond
 * @Date: 2019/11/30 22:09
 */
public class FileUtilsTest {

    @Test
    public void readFileTest() {
        List<String> inputs = FileUtils.readFile(Constant.INPUT_FILE_NAME);
        inputs.forEach(line -> System.out.println(line));
    }
}
