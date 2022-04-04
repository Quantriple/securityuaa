package com.uaa.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


public class HttpUtil {

    private final static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    private static CloseableHttpClient httpClient;
    private static Charset UTF_8 = Charset.forName("UTF-8");
    private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // Increase max total connection to 200
        cm.setMaxTotal(200);
        // Increase default max connection per route to 20
        cm.setDefaultMaxPerRoute(20);
        // Increase max connections for localhost:80 to 50
        HttpHost localhost = new HttpHost("localhost", 80);
        cm.setMaxPerRoute(new HttpRoute(localhost), 50);

        httpClient = HttpClients.custom().setConnectionManager(cm).build();
    }

    public static String post(String url, String content) {
        logger.debug("{} - {}", url, content);
        HttpPost httppost = new HttpPost(url);
        httppost.setConfig(requestConfig);
        if (content != null) {
            HttpEntity httpEntity = new StringEntity(content, UTF_8);
            httppost.setEntity(httpEntity);
        }
        return execute(httppost);
    }

    public static String post4GzipResponse(String url, String content) {
        logger.debug("{} - {}", url, content);
        HttpPost httppost = new HttpPost(url);
        httppost.setConfig(requestConfig);
        httppost.addHeader("Accept-Encoding", "gzip");
        if (content != null) {
            HttpEntity httpEntity = new StringEntity(content, UTF_8);
            httppost.setEntity(httpEntity);
        }
        return execute(httppost);
    }

    /**
     * 以JSON的格式发送请求
     *
     * @param url
     * @param content
     * @return
     */
    public static String postAsJson(String url, String content) {
        logger.debug("{} - {}", url, content);
        HttpPost httppost = new HttpPost(url);
        httppost.setConfig(requestConfig);
        HttpEntity httpEntity = new StringEntity(content, ContentType.APPLICATION_JSON);
        httppost.setEntity(httpEntity);
        return execute(httppost);
    }

    public static String get(String url) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        return execute(httpGet);
    }

    private static String execute(HttpRequestBase httpRequestBase) {
        String result = null;
        try {
            HttpResponse response = httpClient.execute(httpRequestBase);
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, UTF_8);
                EntityUtils.consume(resEntity);
                if (result.length() < 2000) {
                    logger.debug(result);
                } else {
                    if (logger.isTraceEnabled()) {
                        logger.trace(result);
                    }
                }
            }
        } catch (IOException e) {
            logger.error("", e);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File("d://jiafu.txt"), true);
        // fos.w
        //63596946
        //https://www.yxlmdl.net/files/article/html555/76189/76189880/63596764.html';
        //https://www.yxlmdl.net/files/article/html555/133/133810/63596764.html
        //https://www.yxlmdl.net/files/article/html555/133/133810/63596766.html  63596946
        int BaseId = 63596764;
        String baseUrl = "https://www.yxlmdl.net/files/article/html555/133/133810/" + BaseId + ".html";
        for (int i = 0; i < 183; i++) {
            BaseId = BaseId + i;
            String res = HttpUtil.get(baseUrl);
            String replace = res.replace("顶置中夺粗功肖功地", "的")
                    .replace("顺困顶枯枵", "是")
                    .replace("夺回顾功带困", "有")
                    .replace("茵右脚楞夺", ",")
                    .replace("\\&nbsp;", "")
                    .replace("&nbsp;", "")
                    .replace("var", "")
                    .replace("cctxt", "")
                    .replace("='", "")
                    .replace("\\=", "")
                    .replace("<br>", "\n")
                    .replace("=.replace(/的/g,'的');", "")
                    .replace("=.replace(/是/g,'是');", "")
                    .replace("=.replace(/有/g,'有');", "")
                    .replace("=.replace(/,/g,'，');", "");
            replace.trim();
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(replace).append("\n").append("\n");
            fos.write(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
        }
    }
}
