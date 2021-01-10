package com.jxd.jqstudentgrowthtrackingsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName UploadFilePathConfig
 * @Description TODO
 * @Author liutong
 * @Date 2021/1/7 8:17
 * @Version 1.0
 */
@Configuration
public class UploadFilePathConfig implements WebMvcConfigurer {

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //  /upload/**为前端URL访问路径  后面 file:xxxx为本地磁盘映射
        registry.addResourceHandler("/upload/**").addResourceLocations("file:D:/photo/");
    }

}
