package com.xuxue.tools.stream;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by HanHan on 2016/5/14.
 */
public class TestReadStream {

    InputStream inputStream = null;

    @Before
    public void befor() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet("http://www.baidu.com");
        CloseableHttpResponse res = client.execute(get);
        inputStream = res.getEntity().getContent();
    }

    @Test
    public void test() throws IOException {
        System.out.println(ReadStream.toDocument(inputStream, "http://www.baidu.com"));
    }


}
