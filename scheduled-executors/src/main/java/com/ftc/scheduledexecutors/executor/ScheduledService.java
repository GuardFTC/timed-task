package com.ftc.scheduledexecutors.executor;

import cn.hutool.core.date.DateUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2023-02-03 16:55:15
 * @describe: 定时任务业务类
 */
@Component
public class ScheduledService {

    /**
     * 每隔1s输出一次时间
     */
    @Scheduled(cron = "0/1 * * * * ?")
    public void consoleTimeRepeat() {
        System.out.println(Thread.currentThread().getName() + " task repeat at " + DateUtil.now());
    }
}
