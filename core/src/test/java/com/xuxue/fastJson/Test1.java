package com.xuxue.fastJson;


import org.json.JSONArray;

/**
 * Created by HanHan on 2016/5/13.
 */
public class Test1 {

    public static void main(String[] args) {
        String s = "[{\"CityId\":18,\"CityName\":\"西安\"," +
                "\"ProvinceId\":27,\"CityOrder\":1}," +
                "{\"CityId\":53,\"CityName\":\"广州\",\"ProvinceId\":27," +
                "\"CityOrder\":1}]";
        JSONArray array = new JSONArray(s);
    }

}
