package com.ftc.scheduledexecutors;

import cn.hutool.core.date.DateUtil;
import com.ftc.scheduledexecutors.executor.ScheduledExecutors;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class ScheduledExecutorsTest {

    @Autowired
    private ScheduledExecutors scheduledExecutors;

    @Test
    @SneakyThrows(InterruptedException.class)
    void scheduledExecutor() {

        //1.获取定时任务线程池
        ScheduledExecutorService scheduledExecutorService = scheduledExecutors.scheduledExecutor();

        //2.1s后输出时间
        System.out.println(Thread.currentThread().getName() + " task start at:" + DateUtil.now());
        scheduledExecutorService.schedule(() -> {
            System.out.println(Thread.currentThread().getName() + " task end at:" + DateUtil.now());
        }, 1, TimeUnit.SECONDS);

        //3.每隔1s输出一次时间
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println(Thread.currentThread().getName() + " task repeat at:" + DateUtil.now());
        }, 1, 1, TimeUnit.SECONDS);

        //4.主线程睡5s
        TimeUnit.SECONDS.sleep(5);
    }

    @Test
    @SneakyThrows(InterruptedException.class)
    void springScheduled(){
        TimeUnit.SECONDS.sleep(5);
    }
}