package cn.smallpotato.jd.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.UUID;

/**
 * @author Panjb
 */
@Component
public class HttpUtils {

    private final PoolingHttpClientConnectionManager cm;

    public HttpUtils() {
        this.cm = new PoolingHttpClientConnectionManager();
        // 设置最大连接数
        this.cm.setMaxTotal(100);
        // 设置每个主机的最大连接数
        this.cm.setDefaultMaxPerRoute(10);
    }

    /**
     * 根据请求地址下载页面数据
     * @param url url
     * @return 页面数据
     */
    public String doGetHtml(String url) {
        // 获取HttpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();

        // 创建httpGet请求对象, 设置url地址
        HttpGet httpGet = new HttpGet(url);

        // 设置请求信息
        httpGet.setConfig(getConfig());

        // 设置请求头, 伪装用户
        setHeaders(httpGet);

        CloseableHttpResponse response= null;

        try {
            // 使用HttpClient发起请求, 获取响应
            response = httpClient.execute(httpGet);

            // 解析响应, 返回结果
            if (response.getStatusLine().getStatusCode() == 200) {
                // 判断响应体Entity是否不为空, 如果不为空就可以使用EntityUtils
                if (response.getEntity() != null) {
                    return EntityUtils.toString(response.getEntity(), "utf8");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭response
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 返回空串
        return "";
    }

    /**
     * 下载图片
     * @param url url
     * @return 图片名称
     */
    public String doGetImage(String url) {
        // 获取HttpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();

        // 创建httpGet请求对象, 设置url地址
        HttpGet httpGet = new HttpGet(url);

        // 设置请求信息
        httpGet.setConfig(getConfig());

        // 设置请求头, 伪装用户
        setHeaders(httpGet);

        CloseableHttpResponse response= null;

        try {
            // 使用HttpClient发起请求, 获取响应
            response = httpClient.execute(httpGet);

            // 解析响应, 返回结果
            if (response.getStatusLine().getStatusCode() == 200) {
                // 判断响应体Entity是否不为空, 如果不为空就可以使用EntityUtils
                if (response.getEntity() != null) {
                    // 下载图片
                    // 获取图片的后缀
                    String extName = url.substring(url.lastIndexOf("."));
                    // 创建图片名, 重命名图片
                    String picName = UUID.randomUUID() + extName;
                    // 下载图片
                    // 声明OutPutStream
                    OutputStream outputStream = Files.newOutputStream(
                            new File("D:\\projects\\learning\\crawler-jd\\src\\main\\resources\\static\\images\\" + picName).toPath());
                    response.getEntity().writeTo(outputStream);
                    // 返回图片名称
                    return picName;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭response
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 如果下载失败, 返回空串
        return "";
    }

    private RequestConfig getConfig() {
        return RequestConfig.custom()
                .setConnectTimeout(1000)
                .setConnectionRequestTimeout(500)
                .setSocketTimeout(10000)
                .build();
    }

    private void setHeaders(HttpGet httpGet) {
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
    }
}