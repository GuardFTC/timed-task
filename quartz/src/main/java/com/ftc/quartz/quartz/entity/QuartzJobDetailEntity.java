package com.ftc.quartz.quartz.entity;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.log.StaticLog;
import lombok.Data;
import lombok.SneakyThrows;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2023-02-03 20:31:40
 * @describe: Quartz定时任务详情实体类
 */
@Data
public class QuartzJobDetailEntity extends QuartzJobBean {

    /**
     * 目标类
     */
    private String targetObject;

    /**
     * 目标方法
     */
    private String targetMethod;

    /**
     * 方法执行参数
     */
    private Map<String, Object> extra;

    @Override
    @SneakyThrows({NoSuchMethodException.class, IllegalAccessException.class, InvocationTargetException.class})
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        //1.判定extra是否为空
        if (MapUtil.isEmpty(extra)) {
            extra = MapUtil.newHashMap(0);
        }

        //2.打印开始日志
        StaticLog.info("[Quartz定时任务] 开始执行: TriggerId:[{}] Object:[{}] Method:[{}]", extra.get("triggerName"), targetObject, targetMethod);

        //3.获取定时任务执行类以及执行参数
        Object methodParam = extra.get("param");
        Object bean = SpringUtil.getBean(targetObject);

        //4.执行定时任务
        if (extra.isEmpty() || ObjectUtil.isNull(methodParam)) {
            Method method = bean.getClass().getMethod(targetMethod);
            method.invoke(bean);
        } else {
            Method method = bean.getClass().getMethod(targetMethod, Object[].class);
            method.invoke(bean, methodParam);
        }

        //5.打印结束日志
        StaticLog.info("[Quartz定时任务] 结束执行");
    }
}
