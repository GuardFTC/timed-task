package com.ftc.quartz.quartz.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.quartz.*;

import java.util.Date;
import java.util.Map;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2023-02-03 20:30:16
 * @describe: Quartz定时任务实体类
 */
@Data
public class QuartzJobEntity {

    @ApiModelProperty("调度器名称")
    private String scheduleName;

    @ApiModelProperty("触发器名称")
    private String triggerName;

    @ApiModelProperty("触发器分组")
    private String triggerGroup;

    @ApiModelProperty("触发器状态文本")
    private String triggerStateText;

    @ApiModelProperty("触发器类型")
    private String triggerType;

    @ApiModelProperty("触发器状态")
    private String triggerState;

    @ApiModelProperty("任务名称")
    private String jobName;

    @ApiModelProperty("任务组")
    private String jobGroup;

    @ApiModelProperty("下次执行时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date nextFireTime;

    @ApiModelProperty("上次执行时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date preFireTime;

    @ApiModelProperty("开始时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @ApiModelProperty("cron表达式")
    private String cronExpression;

    @ApiModelProperty("目标类")
    private String targetObject;

    @ApiModelProperty("目标方法")
    private String targetMethod;

    @ApiModelProperty("任务描述")
    private String description;

    @ApiModelProperty("定时任务执行的方法")
    private Map<String, Object> extra;

    @ApiModelProperty("任务附加属性")
    private JobDataMap jobDataMap;

    /**
     * 初始化定时任务实体类
     *
     * @param scheduler 定时任务对应调度器
     * @param trigger   定时任务对应触发器
     * @param jobKey    定时任务Key
     * @return 定时任务实体类
     * @throws SchedulerException
     */
    public static QuartzJobEntity initQuartzJob(Scheduler scheduler, Trigger trigger, JobKey jobKey) throws SchedulerException {

        //1.创建定时任务实体类
        QuartzJobEntity job = new QuartzJobEntity();

        //2.设置调度器信息
        job.setScheduleName(scheduler.getSchedulerName());

        //3.设置触发器信息
        job.setTriggerName(trigger.getKey().getName());
        job.setTriggerGroup(trigger.getKey().getGroup());
        job.setTriggerState(scheduler.getTriggerState(trigger.getKey()).name());

        //4.设置Job信息
        job.setJobName(jobKey.getName());
        job.setJobGroup(jobKey.getGroup());

        //5.设置JobDetail信息
        job.setTargetObject(trigger.getJobDataMap().getString("targetObject"));
        job.setTargetMethod(trigger.getJobDataMap().getString("targetMethod"));
        job.setDescription(trigger.getDescription());
        job.setStartTime(trigger.getStartTime());
        job.setNextFireTime(trigger.getNextFireTime());
        job.setPreFireTime(trigger.getPreviousFireTime());
        if (trigger instanceof CronTrigger) {
            CronTrigger cronTrigger = (CronTrigger) trigger;
            job.setCronExpression(cronTrigger.getCronExpression());
        }

        //6.返回
        return job;
    }
}
