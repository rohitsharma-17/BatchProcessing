package com.hashedin.tasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Slf4j
@EnableScheduling
@Configuration
public class JobScheduler {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private BatchJob1 batchJob1;
    @Autowired
    private BatchJob2 batchJob2;

    @Scheduled(cron = "* */2 * * * *")
    public void job1() throws Exception {
        log.info("Job 1 start time {}", LocalDateTime.now());
        JobParameters parameters = new JobParametersBuilder()
                .addString("input", "rohit")
                .addString("batch_1_JobId", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        JobExecution execution = jobLauncher.run(batchJob1.processJob(), parameters);
        log.info("Job 1 with status {} and time {}",execution.getExitStatus(),LocalDateTime.now());
    }

    @Scheduled(cron = "* */10 * * * *")
    public void job2() throws Exception {
        log.info("Job 2 start time {}",LocalDateTime.now());
        JobParameters parameters = new JobParametersBuilder()
                .addString("batch_2_JobId", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        JobExecution execution = jobLauncher.run(batchJob2.processJob2(), parameters);
        log.info("Job 2 with status {} and time {}",execution.getExitStatus(),LocalDateTime.now());
    }
}
