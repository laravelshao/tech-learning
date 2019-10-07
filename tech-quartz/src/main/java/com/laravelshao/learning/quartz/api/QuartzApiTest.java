package com.laravelshao.learning.quartz.api;

import com.laravelshao.learning.quartz.job.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;

/**
 * Quartz Java API 测试
 *
 * @author qinghua.shao
 * @date 2019/10/5
 * @since 1.0.0
 */
public class QuartzApiTest {

    public static void main(String[] args) throws SchedulerException, IOException {

        // 获取调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // 配置JobDetail
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("jobDetail0", "group0").build();

        // 配置Trigger(SimpleScheduleBuilder/CronScheduleBuilder)
        Trigger trigger = TriggerBuilder.newTrigger().startNow()
                .withSchedule(
                        SimpleScheduleBuilder
                                .simpleSchedule()
                                .withIntervalInSeconds(5)
                                .repeatForever())
                .build();

        // 注册任务和定时器
        scheduler.scheduleJob(jobDetail, trigger);

        // 启动调度器
        scheduler.start();

        // 保持服务不结束
        System.in.read();
    }
}
