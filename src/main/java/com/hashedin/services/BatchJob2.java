//package com.hashedin.services;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.file.FlatFileItemWriter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//
//
//public class BatchJob2 {
//
//    @Autowired
//    public JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    public StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    @StepScope
//    public ItemReader<String> jobReader(){
//
//    }
//
//    FlatFileItemWriter<String> jobWriter(){
//
//    }
//
//
//    @Bean
//    public Job processJob() {
//        Step  step = stepBuilderFactory.get("orderStep1")
//                .<String, String> chunk(1)
//                .reader(jobReader())
//                //.processor(new Processor())
//                .writer(jobWriter())
//                .build();
//        return jobBuilderFactory.get("processJob")
//                .incrementer(new RunIdIncrementer())
//                .flow(step)
//                .end()
//                .build();
//    }
//
//}
