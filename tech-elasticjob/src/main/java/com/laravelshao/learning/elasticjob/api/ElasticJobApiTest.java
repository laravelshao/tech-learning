package com.laravelshao.learning.elasticjob.api;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.script.ScriptJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.laravelshao.learning.elasticjob.job.MyDataflowJob;
import com.laravelshao.learning.elasticjob.job.MySimpleJob;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermissions;

/**
 * Elastic Job Java API 测试
 *
 * @author qinghua.shao
 * @date 2019/10/3
 * @since 1.0.0
 */
public class ElasticJobApiTest {

    public static void main(String[] args) throws IOException {

        //new JobScheduler(zkRegistryCenter(), simpleJobConfig()).init(); // 启动Simple类型Job
        //new JobScheduler(zkRegistryCenter(), dataflowJobConfig()).init(); // 启动Dataflow类型Job
        new JobScheduler(zkRegistryCenter(), scriptJobConfig()).init(); // 启动Script类型Job
    }


    /**
     * ZooKeeper注册中心配置
     *
     * @return
     */
    public static ZookeeperRegistryCenter zkRegistryCenter() {
        ZookeeperConfiguration zkConfig = new ZookeeperConfiguration("localhost:2181", "tech-elasticjob");
        ZookeeperRegistryCenter zkRegistryCenter = new ZookeeperRegistryCenter(zkConfig);
        // 注册中心初始化
        zkRegistryCenter.init();
        return zkRegistryCenter;
    }

    /**
     * Simple 类型任务配置
     *
     * @return
     */
    public static LiteJobConfiguration simpleJobConfig() {

        // JOB核心配置
        JobCoreConfiguration jobCoreConfig = JobCoreConfiguration.newBuilder("mySimpleJob", "0/5 * * * * ?", 2).build();
        // JOB类型配置
        JobTypeConfiguration simpleJobConfig = new SimpleJobConfiguration(jobCoreConfig, MySimpleJob.class.getCanonicalName());
        // JOB根配置
        LiteJobConfiguration liteJobConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).overwrite(true).build();

        return liteJobConfig;
    }

    /**
     * Dataflow 类型任务配置
     *
     * @return
     */
    public static LiteJobConfiguration dataflowJobConfig() {

        // JOB核心配置
        JobCoreConfiguration jobCoreConfig = JobCoreConfiguration.newBuilder("myDataflowJob", "0/5 * * * * ?", 2).build();
        // JOB类型配置
        JobTypeConfiguration dataflowJobConfig = new DataflowJobConfiguration(jobCoreConfig, MyDataflowJob.class.getCanonicalName(), true);
        // JOB根配置
        LiteJobConfiguration liteJobConfig = LiteJobConfiguration.newBuilder(dataflowJobConfig).overwrite(true).build();

        return liteJobConfig;
    }

    /**
     * Script 类型任务配置
     *
     * @return
     * @throws IOException
     */
    private static LiteJobConfiguration scriptJobConfig() throws IOException {

        // JOB核心配置
        JobCoreConfiguration coreConfig = JobCoreConfiguration.newBuilder("scriptElasticJob", "0/5 * * * * ?", 2).build();
        // JOB类型配置
        ScriptJobConfiguration scriptJobConfig = new ScriptJobConfiguration(coreConfig, buildScriptCommandLine());
        // JOB根配置
        LiteJobConfiguration liteJobConfig = LiteJobConfiguration.newBuilder(scriptJobConfig).overwrite(true).build();

        return liteJobConfig;
    }

    private static String buildScriptCommandLine() throws IOException {
        if (System.getProperties().getProperty("os.name").contains("Windows")) {
            return Paths.get(ElasticJobApiTest.class.getResource("/script/demo.bat").getPath().substring(1)).toString();
        }
        Path result = Paths.get(ElasticJobApiTest.class.getResource("/script/demo.sh").getPath());
        Files.setPosixFilePermissions(result, PosixFilePermissions.fromString("rwxr-xr-x"));
        return result.toString();
    }

}
