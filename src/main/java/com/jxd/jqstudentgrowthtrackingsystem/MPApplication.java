package com.jxd.jqstudentgrowthtrackingsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName: MPApplication
 * @Author: fws
 * @Description:
 * @Date: 2020/12/31 10:11
 */

@SpringBootApplication
@MapperScan("com.jxd.jqstudentgrowthtrackingsystem.dao")
public class MPApplication {
    public static void main(String[] args) {
        SpringApplication.run(MPApplication.class);
    }
}
