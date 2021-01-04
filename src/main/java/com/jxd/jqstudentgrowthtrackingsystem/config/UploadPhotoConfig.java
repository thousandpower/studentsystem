package com.jxd.jqstudentgrowthtrackingsystem.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName UploadPhotoConfig
 * @Description TODO
 * @Author liutong
 * @Date 2021/1/4 10:36
 * @Version 1.0
 */
@Component
@ConfigurationProperties(prefix = "upload")
public class UploadPhotoConfig {
    private static String path;

    public static String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
