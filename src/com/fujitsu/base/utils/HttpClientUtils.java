package com.fujitsu.base.utils;

import com.fujitsu.base.exception.ConnectionFailedException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 123 on 2016/4/21.
 */
public class HttpClientUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    public static String get(String url, String charset) throws ConnectionFailedException {
        return doGet(url, null, charset);
    }

    public static String get(String url, Map<String, String> params, String charset) throws ConnectionFailedException {

        List<NameValuePair> valuePairs = new ArrayList<NameValuePair>(params.size());
        for (Map.Entry<String, String> entry : params.entrySet()) {
            NameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()));
            valuePairs.add(nameValuePair);
        }
        String param = URLEncodedUtils.format(valuePairs, charset);

        return doGet(url, param, charset);
    }

    public static String put(String url, Map<String, String> params, String charset) throws ConnectionFailedException {

        List<NameValuePair> valuePairs = new ArrayList<NameValuePair>(params.size());
        for (Map.Entry<String, String> entry : params.entrySet()) {
            NameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()));
            valuePairs.add(nameValuePair);
        }
        String param = URLEncodedUtils.format(valuePairs, charset);

        return doPut(url, param, charset);
    }

    public static String put(String url, String charset) throws ConnectionFailedException {

        return doPut(url, charset);
    }

    public static String delete(String url, String charset) throws ConnectionFailedException {

        return doDelete(url, charset);
    }

    public static String get(String url, String params, String charset) throws ConnectionFailedException {

        return doGet(url, params, charset);

    }

    public static String post(String url, String charset) throws ConnectionFailedException {
        return doPost(url, null, charset);
    }

    public static String post(String url, Map<String, String> params, String charset) throws ConnectionFailedException {
        UrlEncodedFormEntity formEntity = null;
        try {
            if (null != params && !params.isEmpty()) {
                List<NameValuePair> valuePairs = new ArrayList<NameValuePair>(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    NameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()));
                    valuePairs.add(nameValuePair);
                }

                formEntity = new UrlEncodedFormEntity(valuePairs, charset);
            }
        } catch (UnsupportedEncodingException e) {
            throw new ConnectionFailedException(e);
        }
        return doPost(url, formEntity, charset);
    }

    public static String post(String url, Map<String, String> params, String charset, String ContentType) throws ConnectionFailedException {
        UrlEncodedFormEntity formEntity = null;
        try {
            if (null != params && !params.isEmpty()) {
                List<NameValuePair> valuePairs = new ArrayList<NameValuePair>(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    NameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()));
                    valuePairs.add(nameValuePair);
                }

                formEntity = new UrlEncodedFormEntity(valuePairs, charset);
                if (null != ContentType && !ContentType.isEmpty()) {
                    formEntity.setContentType(ContentType);
                }
            }
        } catch (UnsupportedEncodingException e) {
            throw new ConnectionFailedException(e);
        }
        return doPost(url, formEntity, charset);
    }

    public static String post(String url, String params, String charset) throws ConnectionFailedException, UnsupportedEncodingException {
        StringEntity stringEntity = null;
        if (null != params && !params.isEmpty()) {
            stringEntity = new StringEntity(params, charset);
        }
        return doPost(url, stringEntity, charset);

    }

    public static String post(String url, String params, String charset, String ContentType) throws ConnectionFailedException, UnsupportedEncodingException {
        StringEntity stringEntity = null;
        if (null != params && !params.isEmpty()) {
            stringEntity = new StringEntity(params, charset);
            if (null != ContentType && !ContentType.isEmpty()) {
                stringEntity.setContentType(ContentType);
            }
        }
        return doPost(url, stringEntity, charset);

    }

    public static String upload(String url, Map<String, String> params, InputStream inputStream, String fileName, String charset) throws ConnectionFailedException, UnsupportedEncodingException {

        InputStreamBody isb = new InputStreamBody(inputStream, fileName);

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addPart("file", isb);

        if (null != params && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.addPart(entry.getKey(), new StringBody(String.valueOf(entry.getValue()), Charset.forName(charset)));
            }
        }
        HttpEntity reqEntity = builder.build();
        return doPost(url, reqEntity, charset);
    }

    public static String forward(String url, String charset, HttpServletResponse httpServletResponse) throws ConnectionFailedException {
        return doForward(url, null, charset, httpServletResponse);
    }


    protected static String doPost(String url, HttpEntity sEntity, String charset) throws ConnectionFailedException {
        PoolingHttpClientConnectionManager connManager = null;
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        String respStr = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new CustomX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());

            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", new SSLConnectionSocketFactory(sslContext))
                    .build();

            connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            httpclient = HttpClients.custom().setConnectionManager(connManager).build();

            // 从上述SSLContext对象中得到SSLSocketFactory对象
            HttpPost httpPost = new HttpPost(url); // 设置响应头信息

            if (null != sEntity) {
                httpPost.setEntity(sEntity);
            }

            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            respStr = EntityUtils.toString(response.getEntity(), charset);
            EntityUtils.consume(entity);
            httpPost.abort();
            response.close();
            httpclient.close();

        } catch (Exception e) {
            throw new ConnectionFailedException(e);
        }
        return respStr;

    }

    protected static String doGet(String url, String param, String charset) throws ConnectionFailedException {
        PoolingHttpClientConnectionManager connManager = null;
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        String respStr = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new CustomX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());

            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", new SSLConnectionSocketFactory(sslContext))
                    .build();

            connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            httpclient = HttpClients.custom().setConnectionManager(connManager).build();

            HttpGet httpGet = null;
            if (null != param) {
                httpGet = new HttpGet(url + "&" + param);

            } else {
                httpGet = new HttpGet(url);
            }

            response = httpclient.execute(httpGet);

            HttpEntity entity = response.getEntity();
            respStr = EntityUtils.toString(entity, charset).trim();
            httpGet.abort();
            response.close();
            httpclient.close();
        } catch (Exception e) {
            throw new ConnectionFailedException(e);
        }
        return respStr;
    }


    protected static String doPut(String url, String param, String charset) throws ConnectionFailedException {
        PoolingHttpClientConnectionManager connManager = null;
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        String respStr = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new CustomX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());

            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", new SSLConnectionSocketFactory(sslContext))
                    .build();

            connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            httpclient = HttpClients.custom().setConnectionManager(connManager).build();

            HttpPut httpPut = null;
            if (null != param) {
                httpPut = new HttpPut(url + "&" + param);

            } else {
                httpPut = new HttpPut(url);
            }

            response = httpclient.execute(httpPut);

            HttpEntity entity = response.getEntity();
            respStr = EntityUtils.toString(entity, charset).trim();
            httpPut.abort();
            response.close();
            httpclient.close();
        } catch (Exception e) {
            throw new ConnectionFailedException(e);
        }
        return respStr;
    }

    protected static String doPut(String url, String charset) throws ConnectionFailedException {
        PoolingHttpClientConnectionManager connManager = null;
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        String respStr = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new CustomX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());

            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", new SSLConnectionSocketFactory(sslContext))
                    .build();

            connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            httpclient = HttpClients.custom().setConnectionManager(connManager).build();

            HttpPut httpPut = null;
            httpPut = new HttpPut(url);


            response = httpclient.execute(httpPut);
            HttpEntity entity = response.getEntity();
            respStr = EntityUtils.toString(entity, charset).trim();
            httpPut.abort();
            response.close();
            httpclient.close();
        } catch (Exception e) {
            throw new ConnectionFailedException(e);
        }
        return respStr;
    }

    protected static String doDelete(String url, String charset) throws ConnectionFailedException {
        PoolingHttpClientConnectionManager connManager = null;
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        String respStr = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new CustomX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());

            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", new SSLConnectionSocketFactory(sslContext))
                    .build();

            connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            httpclient = HttpClients.custom().setConnectionManager(connManager).build();

            HttpDelete httpDelete = null;
            httpDelete = new HttpDelete(url);
            response = httpclient.execute(httpDelete);
            HttpEntity entity = response.getEntity();
            respStr = EntityUtils.toString(entity, charset).trim();
            httpDelete.abort();
            response.close();
            httpclient.close();
        } catch (Exception e) {
            throw new ConnectionFailedException(e);
        }
        return respStr;
    }

    protected static String doForward(String url, String param, String charset, HttpServletResponse httpServletResponse) throws ConnectionFailedException {
        PoolingHttpClientConnectionManager connManager = null;
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        //String respStr = null;
        HttpEntity entity;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new CustomX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());

            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", new SSLConnectionSocketFactory(sslContext))
                    .build();

            connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            httpclient = HttpClients.custom().setConnectionManager(connManager).build();

            HttpGet httpGet = null;
            if (null != param) {
                httpGet = new HttpGet(url + "&" + param);

            } else {
                httpGet = new HttpGet(url);
            }

            response = httpclient.execute(httpGet);
            Header[] contentDisposition = response.getHeaders("Content-disposition");
            for (Header cd : contentDisposition) {
                httpServletResponse.setHeader("Content-disposition", cd.getValue());
            }
            Header[] contentType = response.getHeaders("Content-Type");
            for (Header ct : contentType) {
                httpServletResponse.setHeader("Content-Type", ct.getValue());
            }
            Header[] contentLength = response.getHeaders("Content-Length");
            for (Header cl : contentLength) {
                httpServletResponse.setHeader("Content-Length", cl.getValue());
            }
            entity = response.getEntity();

            InputStream inputStream = entity.getContent();
            OutputStream os = httpServletResponse.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }

            // 这里主要关闭。
            os.close();

            inputStream.close();
            //respStr = EntityUtils.toString(entity, charset).trim();
            httpGet.abort();
            response.close();
            httpclient.close();
        } catch (Exception e) {
            throw new ConnectionFailedException(e);
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
