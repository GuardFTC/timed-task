package com.ftc.scheduledexecutors.executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2023-02-03 16:24:58
 * @describe: 线程池配置类
 */
@Configuration
public class ScheduledExecutors {

    @Bean("scheduledExecutor")
    public ScheduledExecutorService scheduledExecutor() {
        return Executors.newScheduledThreadPool(4);
    }

    @Bean("springScheduledExecutor")
    public ThreadPoolTaskScheduler springScheduledExecutor() {

        //1.定义线程池
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();

        //2.设置线程池大小
        int cpuCount = Runtime.getRuntime().availableProcessors();
        executor.setPoolSize(cpuCount);

        //3.线程池中的线程名前缀
        executor.setThreadNamePrefix("spring-scheduled-");

        //4.设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());

        //5.设置线程池shutdown时,等待当前被调度的任务完成,默认为false
        executor.setWaitForTasksToCompleteOnShutdown(true);

        //6.初始化线程池
        executor.initialize();

        //7.返回
        return executor;
    }
}
