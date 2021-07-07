package com.zlz.integration.signal;

import com.zlz.integration.Util.HttpClientUtil;

public class HTTPClientTest {
    public static void main(String[] args) {
        System.out.println("keep Happy boy");
        String value = HttpClientUtil.get("http://www.dvdspring.com/b/141630/");
        System.out.println(value);
    }
}
