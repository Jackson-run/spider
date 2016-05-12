package com.xuxue.download.util;

/**
 * Created by HanHan on 2016/5/5.
 */
public class Args {

    public static void notNULL(Object obj) {
        if (obj == null)
            throw new IllegalArgumentException("obj is null");
    }

}
