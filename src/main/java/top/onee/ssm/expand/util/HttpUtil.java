package top.onee.ssm.expand.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import top.onee.ssm.common.CommonConsts;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;


/**
 * Http请求工具类
 * Created by VOREVER on 13/06/2017.
 */
public class HttpUtil {

    /**
     * JSON形式发送POST请求
     *
     * @param url
     * @param obj
     * @return
     */
    public static String sendJsonPost(String url, Object obj) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        // 设置请求体
        String data = JSON.toJSONString(obj);
        StringEntity entity = new StringEntity(data, CommonConsts.UTF8);
        httpPost.setEntity(entity);

        return sendRequest(HttpClients.createDefault(), httpPost);
    }

    /**
     * XML形式发送POST请求
     *
     * @param url
     * @param obj
     * @return
     */
    public static String sendXmlPost(String url, Object obj) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
        // 设置请求体
        String xml = XmlUtil.objectToCdataXml(obj);
        StringEntity entity = new StringEntity(xml, CommonConsts.UTF8);
        httpPost.setEntity(entity);

        return sendRequest(HttpClients.createDefault(), httpPost);
    }

    /**
     * XML形式发送SSL POST请求
     *
     * @param url
     * @param obj
     * @return
     */
    public static String sendXmlSSLPost(String url, Object obj, String certPath, String certPassword) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
        // 设置请求体
        String xml = XmlUtil.objectToCdataXml(obj);
        StringEntity entity = new StringEntity(xml, CommonConsts.UTF8);
        httpPost.setEntity(entity);

        return sendPostSSLRequest(httpPost, certPath, certPassword);
    }

    /**
     * 发送Get请求
     *
     * @param url
     * @return
     */
    public static String sendGet(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        return sendRequest(HttpClients.createDefault(), httpGet);
    }

    /**
     * 发送SSL POST请求
     *
     * @param httpPost
     * @param certPath
     * @param certPassword
     * @return
     */
    private static String sendPostSSLRequest(HttpPost httpPost, String certPath, String certPassword) throws Exception {
        //指定读取证书格式为PKCS12
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        //读取本机存放的PKCS12证书文件
        FileInputStream instream = new FileInputStream(new File(certPath));
        try {
            // 指定PKCS12的密码(商户ID)
            keyStore.load(instream, certPassword.toCharArray());
        } finally {
            instream.close();
        }
        SSLContext sslContext = SSLContexts.custom().loadKeyMaterial(keyStore, certPassword.toCharArray()).build();
        // 指定TLS版本
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1"},
                null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        // 设置httpclient的SSLSocketFactory
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

        return sendRequest(httpclient, httpPost);
    }

    /**
     * 发送请求
     *
     * @param client
     * @param request
     * @return
     */
    private static String sendRequest(CloseableHttpClient client, HttpUriRequest request) throws IOException {
        CloseableHttpResponse response = client.execute(request);
        try {
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, CommonConsts.UTF8);
        } finally {
            response.close();
            client.close();
        }
    }

}
