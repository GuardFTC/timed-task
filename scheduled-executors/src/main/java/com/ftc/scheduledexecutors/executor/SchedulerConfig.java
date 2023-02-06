package com.ftc.scheduledexecutors.executor;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2023-02-03 17:05:17
 * @describe: SpringScheduler配置类
 */
@Configuration
@RequiredArgsConstructor
public class SchedulerConfig implements SchedulingConfigurer {

    private final ThreadPoolTaskScheduler springScheduledExecutor;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setTaskScheduler(springScheduledExecutor);
    }
}
