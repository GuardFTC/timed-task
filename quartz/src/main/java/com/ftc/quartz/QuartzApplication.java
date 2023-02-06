package com.ftc.quartz;

import cn.hutool.core.date.DateUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2023-02-03 16:55:15
 * @describe: 启动类
 */
@SpringBootApplication
public class QuartzApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
    }

    /**
     * 每隔1s输出一次时间
     */
    @Scheduled(cron = "0/1 * * * * ?")
    public void consoleTimeRepeat() {
        System.out.println(Thread.currentThread().getName() + " task repeat at " + DateUtil.now());
    }
}
