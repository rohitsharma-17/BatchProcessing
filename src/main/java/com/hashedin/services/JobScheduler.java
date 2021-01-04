package com.hashedin.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@EnableScheduling
public class JobScheduler {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private BatchJob1 batchJob1;

    @Scheduled(cron = "*/1 * * * * *")
    public void job1() throws Exception {
        log.info("Job 1");
        JobParameters parameters = new JobParametersBuilder().addString("input", "rohit").toJobParameters();
        JobExecution execution = jobLauncher.run(batchJob1.processJob(), parameters);
        log.info("job1 ", execution.getExitStatus());
    }
}
