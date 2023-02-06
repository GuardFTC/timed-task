package com.ftc.quartz.quartz;

import cn.hutool.log.StaticLog;
import com.ftc.quartz.quartz.entity.QuartzJobEntity;
import com.ftc.quartz.result.RestfulResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2023-02-03 21:19:32
 * @describe: 定时任务Controller
 */
@Api("定时任务接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/vi/rest/quartz-jobs")
public class QuartzController {

    private final QuartzService quartzService;

    @PostMapping
    @ApiOperation("添加或更新定时任务")
    public RestfulResult<QuartzJobEntity> addOrUpdateJob(@RequestBody QuartzJobEntity job) throws ParseException, SchedulerException {
        StaticLog.info("[Quartz定时任务] 添加任务 入参:[{}]", job);

        //1.添加
        job = quartzService.addOrUpdateJob(job);
        StaticLog.info("[Quartz定时任务] 添加任务 出参:[{}]", job);

        //2.返回
        return RestfulResult.Success.addData(job);
    }

    @GetMapping
    @ApiOperation("获得所有定时任务")
    public RestfulResult<List<QuartzJobEntity>> getJobList() throws SchedulerException {

        //1.获取
        List<QuartzJobEntity> jobList = quartzService.getJobList();
        StaticLog.info("[Quartz定时任务] 获取任务 出参:[{}]", jobList);

        //2.返回
        return RestfulResult.Success.getOrUpdateData(jobList);
    }

    @PostMapping("remove-logs")
    @ApiOperation("移除定时任务")
    public RestfulResult<QuartzJobEntity> removeJob(@RequestBody QuartzJobEntity job) throws SchedulerException {
        StaticLog.info("[Quartz定时任务] 移除任务 入参:[{}]", job);

        //1.移除
        job = quartzService.removeJob(job);
        StaticLog.info("[Quartz定时任务] 移除任务 出参:[{}]", job);

        //2.返回
        return RestfulResult.Success.addData(job);
    }

    @PostMapping("resume-logs")
    @ApiOperation("重启定时任务")
    public RestfulResult<QuartzJobEntity> resumeJob(@RequestBody QuartzJobEntity job) throws SchedulerException {
        StaticLog.info("[Quartz定时任务] 重启任务 入参:[{}]", job);

        //1.重启
        job = quartzService.resumeJob(job);
        StaticLog.info("[Quartz定时任务] 重启任务 出参:[{}]", job);

        //2.返回
        return RestfulResult.Success.addData(job);
    }

    @PostMapping("pause-logs")
    @ApiOperation("暂停定时任务")
    public RestfulResult<QuartzJobEntity> pauseJob(@RequestBody QuartzJobEntity job) throws SchedulerException {
        StaticLog.info("[Quartz定时任务] 暂停任务 入参:[{}]", job);

        //1.暂停
        job = quartzService.pauseJob(job);
        StaticLog.info("[Quartz定时任务] 暂停任务 出参:[{}]", job);

        //2.返回
        return RestfulResult.Success.addData(job);
    }

    @PostMapping("trigger-logs")
    @ApiOperation("执行定时任务")
    public RestfulResult<QuartzJobEntity> triggerJob(@RequestBody QuartzJobEntity job) throws SchedulerException {
        StaticLog.info("[Quartz定时任务] 执行任务 入参:[{}]", job);

        //1.执行
        job = quartzService.triggerJob(job);
        StaticLog.info("[Quartz定时任务] 执行任务 出参:[{}]", job);

        //2.返回
        return RestfulResult.Success.addData(job);
    }
}
