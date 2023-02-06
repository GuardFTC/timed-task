package com.ftc.scheduledexecutors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2023-02-03 16:55:15
 * @describe: 启动类
 */
@EnableScheduling
@SpringBootApplication
public class ScheduledExecutorsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScheduledExecutorsApplication.class, args);
    }
}
