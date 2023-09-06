package com.zz.common.common.utils;

import com.zz.common.common.config.SystemConfig;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author GR
 * time 2023-05-25-17-39
 **/
@Component
public class ServerImageUtils {

    public static InputStream getImageStream(String path) throws IOException {
        StringBuilder httpUrl = new StringBuilder(SystemConfig.getImageWebsite());
        httpUrl.append(path);
        URL url = new URL(httpUrl.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        //不使用缓存
        connection.setUseCaches(false);
        //连接指定的资源
        connection.connect();
        //获取网络输入流
        return connection.getInputStream();
    }
}
