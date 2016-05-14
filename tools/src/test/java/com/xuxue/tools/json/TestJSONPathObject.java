package com.xuxue.tools.json;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by HanHan on 2016/5/13.
 */
public class TestJSONPathObject {

    private JSONPathObject jpath;

    @Before
    public void init() {
        String s = "[{\"CityId\":18,\"CityName\":\"西安\"," +
                "\"ProvinceId\":27,\"CityOrder\":1}," +
                "{\"CityId\":53,\"CityName\":\"广州\",\"ProvinceId\":27," +
                "\"CityOrder\":1}]";
        jpath = new JSONPathObject(s);
    }

    @Test
    public void test1() {
        String path = "[0]['ProvinceId']";
        System.out.println(jpath.searchPath(path));
        String path2 = "[1]['ProvinceId']";
        System.out.println(jpath.searchPath(path2));
    }

}
