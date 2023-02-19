package com.ftc.powerjob;

import org.springframework.stereotype.Component;
import tech.powerjob.worker.core.processor.ProcessResult;
import tech.powerjob.worker.core.processor.TaskContext;
import tech.powerjob.worker.core.processor.sdk.BasicProcessor;
import tech.powerjob.worker.log.OmsLogger;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2023-02-07 10:59:32
 * @describe: 单机处理器Demo
 */
@Component
public class BasicProcessorDemo implements BasicProcessor {

    @Override
    public ProcessResult process(TaskContext context) throws Exception {

        //1.打印在线日志
        OmsLogger omsLogger = context.getOmsLogger();
        omsLogger.info("first power job is running");

        //2.返回结果,该结果会被持久化到数据库,在前端页面直接查看,极为方便
        return new ProcessResult(true, "first power job is running");
    }
}
