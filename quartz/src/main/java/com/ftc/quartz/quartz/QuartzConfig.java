package com.ftc.quartz.quartz;

import com.ftc.quartz.quartz.entity.QuartzJobDetailEntity;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2023-02-03 20:51:03
 * @describe: Quartz配置类
 */
@Configuration
public class QuartzConfig {

    /**
     * quartz初始化监听器
     *
     * @return QuartzInitializerListener
     */
    @Bean
    public QuartzInitializerListener listener() {
        return new QuartzInitializerListener();
    }

    /**
     * 定时任务执行器
     *
     * @return JobDetailFactoryBean
     */
    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(QuartzJobDetailEntity.class);
        jobDetailFactoryBean.setDurability(true);
        return jobDetailFactoryBean;
    }
}
