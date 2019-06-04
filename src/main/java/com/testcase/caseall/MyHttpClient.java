package com.testcase.caseall;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyHttpClient {

    private String url;
    private ResourceBundle boundle;
    private static CookieStore cookieStore;

    @BeforeTest
    public void beforeTest() {
        boundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = boundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {
        String result;
        String uri = boundle.getString("getCookies.uri");
        String testUrl = this.url + uri;
        HttpClientContext httpClientContext = new HttpClientContext();
        cookieStore = new BasicCookieStore();
        httpClientContext.setCookieStore(cookieStore);

        HttpGet get = new HttpGet(testUrl);
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        CloseableHttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
        cookieStore = httpClientContext.getCookieStore();
        for (Cookie cookie : cookieStore.getCookies()) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("name:" + name + ";value:" + value);
        }
        client.close();
    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void postWithCookies() throws IOException {
        String uil = "http://localhost:8998/postWithHeader";
        HttpPost post = new HttpPost(uil);
        CloseableHttpClient closeableHttpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        //添加请求头
        post.setHeader("content-type", "application/json");
        //设置参数
        JSONObject param = new JSONObject();
        param.put("name", "susan");
        param.put("age", "18");
        //添加参数
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
        //获取结果
        CloseableHttpResponse response = closeableHttpClient.execute(post);
        String result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
    }

    @Test
    public void gameCenter_login() throws IOException {
        String uil = "http://m.api.4399.cn/user/box/android/v1.0/log-in.html";
        //添加sign和时间戳
        String username = "18202823820";
        long time = System.currentTimeMillis();
        String dataline = String.valueOf(time / 1000);
        System.out.println(dataline);
        String stringsign = dataline + "8689300230290681" + "FfQn1pwmgRY=" + username + "ef2vx#sf*^FlklSD*9sdf(m$&qw%d7po";
        System.out.println(stringsign);
        String sign = DigestUtils.md5Hex(stringsign);
        System.out.println(sign);

        HttpPost post = new HttpPost(uil);
        CloseableHttpClient client = HttpClients.custom().build();

        //添加请求头
        post.addHeader("User-Agent", "4399GameCenter/5.0.0.22(android;MI 5;8.0.0;1080x1920;WIFI;1348.384;gongce4399)");
        post.addHeader("mareacode", "350200");
        post.addHeader("a-id", "6b0570c4e82d4e16");
        post.addHeader("e-id", "868930023029068");
        post.addHeader("m-id", "B0%3AE2%3A35%3AC2%3A5F%3ADB");
        post.addHeader("s-id:", "460026002314811");
        post.addHeader("mauth", "");
        post.addHeader("mudid", "1077ggZydmGYgWy9vyu0f9f67");
        post.addHeader("pauth", "");
        post.addHeader("SM-DEVICEID", "20180706034731fe88f112c60aa77520290816896c0d4a0152f32534022fb4");
        post.addHeader("mdeviceId", "868930023029068");
        post.addHeader("mauthcode", "");
        post.addHeader("Content-Type", "application/x-www-form-urlencoded");
        post.addHeader("Accept-Encoding", "gzip");
        post.addHeader("Connection", "keep-alive");



        //添加参数
        List<NameValuePair> nameValuePairList = new LinkedList<NameValuePair>(
        );
        nameValuePairList.add(new BasicNameValuePair("password", "FfQn1pwmgRY="));
        nameValuePairList.add(new BasicNameValuePair("deviceIdentifier", "868930023029068"));
        nameValuePairList.add(new BasicNameValuePair("dateline", dataline));
        nameValuePairList.add(new BasicNameValuePair("sign", sign));
        nameValuePairList.add(new BasicNameValuePair("model", "MI 5"));
        nameValuePairList.add(new BasicNameValuePair("username", username));
        nameValuePairList.add(new BasicNameValuePair("info", "1"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairList, Consts.UTF_8);
        entity.setContentType("application/x-www-form-urlencoded");

        post.setEntity(entity);
        //执行请求
        CloseableHttpResponse response = client.execute(post);

        String result = EntityUtils.toString(response.getEntity());

        System.out.println(result);
        response.close();
        client.close();
    }


    @Test
    public void test1() throws IOException {
        String result;
        HttpGet get = new HttpGet("http://www.baidu.com");
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
    }

}
