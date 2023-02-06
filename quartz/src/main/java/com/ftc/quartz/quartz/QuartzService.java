package com.ftc.quartz.quartz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ftc.quartz.quartz.entity.QuartzJobEntity;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2023-02-03 20:52:27
 * @describe: Quartz业务类
 */
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class QuartzService {

    private final Scheduler scheduler;

    private final JobDetail jobDetail;

    /**
     * 新增或更新任务
     *
     * @param job 定时任务
     * @return 定时任务
     * @throws ParseException
     * @throws SchedulerException
     */
    public QuartzJobEntity addOrUpdateJob(QuartzJobEntity job) throws ParseException, SchedulerException {

        //1.获取触发器Key
        TriggerKey triggerKey = new TriggerKey(job.getTriggerName(), job.getTriggerGroup());

        //2.设置定时任务信息
        JobDataMap dataMap = ObjectUtil.isNull(job.getJobDataMap()) ? new JobDataMap() : job.getJobDataMap();
        dataMap.put("targetObject", job.getTargetObject());
        dataMap.put("targetMethod", job.getTargetMethod());
        dataMap.put("description", job.getDescription());
        dataMap.put("extra", job.getExtra());

        //3.获取cron触发器
        CronTriggerImpl cronTrigger = new CronTriggerImpl();

        //4.设置cron触发器信息
        cronTrigger.setKey(triggerKey);
        cronTrigger.setJobName(jobDetail.getKey().getName());
        cronTrigger.setJobGroup(jobDetail.getKey().getGroup());
        cronTrigger.setDescription(job.getDescription());
        cronTrigger.setCronExpression(job.getCronExpression());
        cronTrigger.setJobDataMap(dataMap);

        //5.如果存在该触发器ID,更新定时任务,否则添加定时任务
        if (scheduler.checkExists(triggerKey)) {
            scheduler.rescheduleJob(triggerKey, cronTrigger);
        } else {
            scheduler.addJob(jobDetail, true);
            scheduler.scheduleJob(cronTrigger);
        }

        //6.返回
        return job;
    }

    /**
     * 获取任务列表
     *
     * @return 任务列表
     * @throws SchedulerException
     */
    public List<QuartzJobEntity> getJobList() throws SchedulerException {

        //1.创建结果集
        List<QuartzJobEntity> jobList = CollUtil.newArrayList();

        //2.获取定时任务Key集合并开始遍历
        Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.anyJobGroup());
        for (JobKey jobKey : jobKeys) {

            //3.获取定时任务触发器集合
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {

                //4.初始化定时任务实体类
                QuartzJobEntity job = QuartzJobEntity.initQuartzJob(scheduler, trigger, jobKey);

                //5.存入结果集
                jobList.add(job);
            }
        }

        //6.返回
        return jobList;
    }

    /**
     * 移除任务
     *
     * @param job 定时任务
     * @return 定时任务
     * @throws SchedulerException
     */
    public QuartzJobEntity removeJob(QuartzJobEntity job) throws SchedulerException {

        //1.获取触发器Key
        TriggerKey triggerKey = new TriggerKey(job.getTriggerName(), job.getTriggerGroup());

        //2.暂停触发器
        scheduler.pauseTrigger(triggerKey);

        //3.暂停任务并返回
        scheduler.unscheduleJob(triggerKey);

        //4.返回
        return job;
    }

    /**
     * 重启任务
     *
     * @param job 定时任务
     * @return 定时任务
     * @throws SchedulerException
     */
    public QuartzJobEntity resumeJob(QuartzJobEntity job) throws SchedulerException {

        //1.获取触发器Key
        TriggerKey triggerKey = new TriggerKey(job.getTriggerName(), job.getTriggerGroup());

        //2.重启任务
        scheduler.resumeTrigger(triggerKey);

        //3.返回
        return job;
    }

    /**
     * 暂停任务
     *
     * @param job 定时任务
     * @return 定时任务
     * @throws SchedulerException
     */
    public QuartzJobEntity pauseJob(QuartzJobEntity job) throws SchedulerException {

        //1.获取触发器Key
        TriggerKey triggerKey = new TriggerKey(job.getTriggerName(), job.getTriggerGroup());

        //2.暂停任务
        scheduler.pauseTrigger(triggerKey);

        //3.返回
        return job;
    }

    /**
     * 触发任务
     *
     * @param job 定时任务
     * @return 定时任务
     * @throws SchedulerException
     */
    public QuartzJobEntity triggerJob(QuartzJobEntity job) throws SchedulerException {

        //1.获取触发器Key
        TriggerKey triggerKey = new TriggerKey(job.getTriggerName(), job.getTriggerGroup());

        //2.获取触发器
        Trigger trigger = scheduler.getTrigger(triggerKey);

        //3.获取任务Key
        JobKey jobKey = trigger.getJobKey();

        //4.执行任务
        scheduler.triggerJob(jobKey, trigger.getJobDataMap());

        //5.返回
        return job;
    }
}