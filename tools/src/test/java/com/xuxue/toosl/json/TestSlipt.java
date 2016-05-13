package com.xuxue.toosl.json;

import org.junit.Test;

/**
 * Created by HanHan on 2016/5/13.
 */
public class TestSlipt {

    @Test
    public void test() {
        String s = "[1][2][3]";
        System.out.println(s);
        s = s.substring(1, s.length() - 1);
        String[] paths = s.split("\\]\\[");
        for (String ss : paths) {
            System.out.println(ss);
        }
    }

}
