package com.ftc.xxljob.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2023-02-06 14:19:01
 * @describe: 执行器属性配置
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "xxl-job")
public class XxlJobExecutorProperties {

    /**
     * 执行器通讯TOKEN
     */
    private String accessToken;

    /**
     * 调度中心部署根地址,如调度中心集群部署存在多个地址则用逗号分隔
     */
    private String adminAddresses;

    /**
     * 执行器AppName
     */
    private String executorAppName;

    /**
     * 执行器注册地址
     */
    private String executorAddress;

    /**
     * 执行器IP
     */
    private String executorIp;

    /**
     * 执行器端口号
     */
    private int executorPort;

    /**
     * 执行器运行日志文件存储磁盘路径
     */
    private String executorLogPath;

    /**
     * 执行器日志文件保存天数
     */
    private int executorLogRetentionDays;
}
