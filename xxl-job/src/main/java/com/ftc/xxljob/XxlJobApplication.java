package com.ftc.xxljob;

import cn.hutool.log.StaticLog;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2023-02-03 16:55:15
 * @describe: 启动类
 */
@SpringBootApplication
public class XxlJobApplication {
    public static void main(String[] args) {
        SpringApplication.run(XxlJobApplication.class, args);
    }

    @XxlJob(value = "test-job")
    public void testJob() throws Exception {
        StaticLog.info("testJob is running");
        XxlJobHelper.log("testJob is running");
    }
}
