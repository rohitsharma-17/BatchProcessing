package com.hashedin.services;

import com.hashedin.tasklets.Job1Tesklets;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchJob1 {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job processJob() {

        Step step = stepBuilderFactory.get("step_one")
                .tasklet(new Job1Tesklets())
                .build();

        return jobBuilderFactory.get("processJob")
                .incrementer(new RunIdIncrementer())
                .flow(step)
                .end()
                .build();
    }
}
