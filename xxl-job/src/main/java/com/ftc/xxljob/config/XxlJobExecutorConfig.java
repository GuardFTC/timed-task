package com.ftc.xxljob.config;

import cn.hutool.log.StaticLog;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2023-02-06 14:09:19
 * @describe: 执行器实例配置
 */
@Configuration
@RequiredArgsConstructor
public class XxlJobExecutorConfig {

    private final XxlJobExecutorProperties executorProperties;

    @Bean("xxlJobExecutor")
    public XxlJobSpringExecutor xxlJobExecutor() {
        StaticLog.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> JobExecutor config init start");

        //1.创建执行器
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();

        //2.设置执行器属性
        xxlJobSpringExecutor.setAccessToken(executorProperties.getAccessToken());
        xxlJobSpringExecutor.setAdminAddresses(executorProperties.getAdminAddresses());
        xxlJobSpringExecutor.setAppname(executorProperties.getExecutorAppName());
        xxlJobSpringExecutor.setIp(executorProperties.getExecutorIp());
        xxlJobSpringExecutor.setAddress(executorProperties.getExecutorAddress());
        xxlJobSpringExecutor.setLogPath(executorProperties.getExecutorLogPath());
        xxlJobSpringExecutor.setLogRetentionDays(executorProperties.getExecutorLogRetentionDays());
        xxlJobSpringExecutor.setPort(executorProperties.getExecutorPort());

        //3.返回
        return xxlJobSpringExecutor;
    }
}
