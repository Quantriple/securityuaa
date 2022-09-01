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

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;


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
     /*           if (result.length() < 2000) {
                    logger.debug(result);
                } else {
                    if (logger.isTraceEnabled()) {
                        logger.trace(result);
                    }
                }*/
            }
        } catch (IOException e) {
            logger.error("", e);
        }
        return result;
    }

    /*public static void doGetjd(int i, int page, FileOutputStream fos) throws Exception {
        String keyword = "java";
        //获取请求连接，需要联网
        String url;
        if (page == 0) {
          //  url = "https://m.qitxt.com/41/60941/" + i + ".html";
           // url="https://m.1dwxw.com/155922/read_"+i+".html";
           // http://www.ywggzy.com/bxwx/33016/4222820_2.html
            url="http://www.ywggzy.com/bxwx/33016/"+i+".html";

        } else {
           // url = "https://m.qitxt.com/41/60941/" + i + "/" + (page + 1) + ".html";
           // url="https://m.1dwxw.com/155922/read_"+i+"/" + (page + 1)+".html";
            url="http://www.ywggzy.com/bxwx/33016/"+i+"_" + (page + 1)+".html";
        }
        // System.out.println(url);
        // final String s = get(url);
        //  System.out.println(s);
        //解析网页（返回浏览器的Document对象）
        Document document = Jsoup.parse(new URL(url), 30000);
        // 初次使用时，如下
       // Map map=new HashMap(16);
       // map.put("Cookie","")
        Map<String, String> map1 = string2Map("fontFamily=null; fontColor=null; fontSize=null; bg=null; Hm_lvt_74e7ec5497e79ec99adee89aaa642f0b=1660962682; bookid=33016; booklist=%257B%2522BookId%2522%253A33016%252C%2522ChapterId%2522%253A4222820%252C%2522ChapterName%2522%253A%2522%25u7B2C1%25u7AE0%2522%257D; Hm_lpvt_74e7ec5497e79ec99adee89aaa642f0b=fontFamily=null; fontColor=null; fontSize=null; bg=null; Hm_lvt_74e7ec5497e79ec99adee89aaa642f0b=1660962682; bookid=33016; booklist=%7B%22BookId%22%3A33016%2C%22ChapterId%22%3A4222821%2C%22ChapterName%22%3A%22%u7B2C2%u7AE0%22%7D; Hm_lpvt_74e7ec5497e79ec99adee89aaa642f0b=fontFamily=null; fontColor=null; fontSize=null; bg=null; Hm_lvt_74e7ec5497e79ec99adee89aaa642f0b=1660962682; bookid=33016; booklist=%7B%22BookId%22%3A33016%2C%22ChapterId%22%3A4222821%2C%22ChapterName%22%3A%22%u7B2C2%u7AE0%22%7D;");
        map1.put("Hm_lpvt_74e7ec5497e79ec99adee89aaa642f0b",String.valueOf(System.currentTimeMillis()));
*//*        String document1 = Jsoup.connect(url).method(Connection.Method.GET)
                .cookies(map1).
                        get().body().text();
        System.out.println(document1);*//*
        //获取div，js里面的方法这里都能用  pt-pop
        Document document1 = Jsoup.connect(url).method(Connection.Method.GET).timeout(30000)
                .cookies(map1).get();
        Element element = document1.getElementById("content");
        //获取div里面的每个li
        String tagName = element.tagName();
        System.out.println(element.getAllElements());
       System.out.println("--------------------------------");
        Elements elements = element.getElementsByTag("content");
        System.out.println(elements.html());
        StringBuilder stringBuilder = new StringBuilder();
        for (Element el : elements) {

            System.out.println(el.data());
            String text = el.getElementsByClass("1660963685").eq(0).text();
            //System.out.println(text);
            System.out.println(text);
            stringBuilder.append(text).append("\n");

        }
        fos.write(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
    }*/

    public static Map<String,String> string2Map(String s){
        String[] split = s.split(";");
        Map map=new HashMap(16);
        for (String s1 : split) {
            String[] split1 = s1.split("\\=");
            // System.out.println(split1[0]+"---"+split1[1]);
            map.put(split1[0],split1[1])  ;
        }
        return map;
    }

    //https://m.qitxt.com/41/60941/24275060.html    https://m.qitxt.com/41/60941/24275060/2.html
// https://m.qitxt.com/41/60941/24311391.html

    //https://m.1dwxw.com/155922/read_113053397.html
    public static void main(String[] args) throws Exception {
       /* FileOutputStream fos = new FileOutputStream(new File("d://123.txt"), true);
        for (int i = 4222820; i <= 4222820; i++) {
            for (int j = 0; j < 1; j++) {
                Thread.sleep(1000);
                doGetjd(i, j, fos);
            }

        }*/

        /* doGetjd(24275060, 0,fos);*/

        // FileOutputStream fos = new FileOutputStream(new File("d://123.txt"), true);
        // fos.w
        //63596946
        //https://www.yxlmdl.net/files/article/html555/76189/76189880/63596764.html';
        //https://www.yxlmdl.net/files/article/html555/133/133810/63596764.html
        //https://www.yxlmdl.net/files/article/html555/133/133810/63596766.html  63596946
      /*  int BaseId = 63596764;
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
        }*/
      /*  String s="fikker-h1J1-2I6s=hghCTOKOhsBerqfcpYXkBGhuTmjw0gt7; fikker-h1J1-2I6s=hghCTOKOhsBerqfcpYXkBGhuTmjw0gt7; Hm_lvt_0e4f867019ac6c1acd53eac3d834df79=1660955542; Hm_lvt_b7136544789c1b32fb0aea005d16087b=1660955542; bookid=155922%2C155922; chapterid=113107025%2C113053397; chaptername=%u7B2C529%u7AE0%2C%u7B2C1%u7AE0; Hm_lpvt_b7136544789c1b32fb0aea005d16087b=1660955554; Hm_lpvt_0e4f867019ac6c1acd53eac3d834df79=1660955554";
        String[] split = s.split(";");
        Map map=new HashMap(16);
        for (String s1 : split) {
            String[] split1 = s1.split("\\=");
           // System.out.println(split1[0]+"---"+split1[1]);
            map.put(split1[0],split1[1])  ;
        }*/
    int mz=423+25208+2040+91+2797+8467;
    int zy=504+4416+5772+1167+372;
        System.out.println(mz);
        System.out.println("\n");
        System.out.println(zy);
    }
}
