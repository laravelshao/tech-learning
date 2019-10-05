package com.laravelshao.learning.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.time.LocalTime;

/**
 * 自定义 quartz 任务
 *
 * @author qinghua.shao
 * @date 2019/10/5
 * @since 1.0.0
 */
@Slf4j
public class MyJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.info("任务执行 {}", LocalTime.now().toString());
    }
}
