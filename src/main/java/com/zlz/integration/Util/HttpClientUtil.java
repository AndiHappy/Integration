package com.zlz.integration.Util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HttpClientUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    private static final String UTF_8 = "UTF-8";

    private static final int connectionRequestTimeout = 60000;

    private static final int socketTimeout = 30000;

    private static final int maxTotal = 200;

    private static final int defaultMaxPerRoute = 20;

    /**
     * 连接池
     */
    private static PoolingHttpClientConnectionManager ConnectionManager = null;
    /**
     * 默认连接配置
     */
    private static RequestConfig DefaultRequestConfig =
            RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout).setSocketTimeout(socketTimeout).build();

    static {
        init();
    }

    /**
     * 初始化链接池
     */
    private static void init() {
        LayeredConnectionSocketFactory sslsf = null;
        try {
            sslsf = new SSLConnectionSocketFactory(SSLContexts.custom()
                    .loadTrustMaterial(null, new TrustSelfSignedStrategy()).build(), NoopHostnameVerifier.INSTANCE);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("https", sslsf)
                .register("http", new PlainConnectionSocketFactory())
                .build();
        ConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        ConnectionManager.setMaxTotal(maxTotal);
        ConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
    }


    public static String get(String reqURL) {
        return get(reqURL, null);
    }

    public static String get(String reqURL, Map<String, String> headers) {
        String responseContent = null;
        try {
            HttpClient httpClient = getHttpClient();
            HttpGet httpGet = new HttpGet(reqURL);
            if (headers != null && headers.size() > 0) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpGet.addHeader(entry.getKey(), entry.getValue());
                }
            }
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                responseContent = EntityUtils.toString(entity, UTF_8);
            }
            EntityUtils.consume(entity);
        }catch (Exception e){
            logger.error("IOException:{}", e);
        }
        return responseContent;
    }

    public static String post(String reqURL, Map<String, String> params) {
        return post(reqURL,params,UTF_8);
    }


    public static String post(String reqURL, Map<String, String> params,String urlEncode) {
        String responseContent = null;

        try {
            CloseableHttpClient httpClient = getHttpClient();
            HttpPost httpPost = new HttpPost(reqURL);
            httpPost.setHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8");
            List<NameValuePair> formParams = new ArrayList<>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(formParams, urlEncode));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                responseContent = EntityUtils.toString(entity, urlEncode);
            }
            EntityUtils.consume(entity);
        } catch (Exception e){
            logger.debug("IOException:{}", e);
        }
        return responseContent;
    }


    public static CloseableHttpClient getHttpClient() {
        return HttpClients.custom().setConnectionManager(ConnectionManager).setDefaultRequestConfig(DefaultRequestConfig).build();
    }
}
