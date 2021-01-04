package com.hashedin.tasks;

import com.hashedin.beans.MoviePlottingDetails;
import com.hashedin.processor.MovieProcessor;
import com.hashedin.utils.Util;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.util.Map;

@Configuration
@EnableBatchProcessing
public class BatchJob2 {

    private final String filePath = "/home/hasher/Downloads/imdb_movies.csv";
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    @StepScope
    public IteratorItemReader<Map.Entry<String, MoviePlottingDetails>> jobReader() {
        Map<String, MoviePlottingDetails> items = new Util().readAllDataAtOnce(filePath);
        IteratorItemReader<Map.Entry<String, MoviePlottingDetails>> iteratorItemReader = new IteratorItemReader<>(items.entrySet().iterator());
        return iteratorItemReader;
    }

    @Bean
    @StepScope
    FlatFileItemWriter<MoviePlottingDetails> jobWriter() {
        return new FlatFileItemWriterBuilder<MoviePlottingDetails>()
                .name("writer")
                .resource(new FileSystemResource("src/main/resources/batch_two_output.csv"))
                .headerCallback(writer -> {
                    writer.write("year \t title \t genre \t country \t director \t writer \t productionCompany \t budget \t titleId");
                })
                 .delimited().delimiter("\t")
                .names(new String[] {"year","title","genre","country","director","writer","productionCompany","budget","titleId"})
                .shouldDeleteIfExists(true).build();
    }


    @Bean
    public Job processJob2() {
        Step step = stepBuilderFactory.get("movie_plotting_details_step")
                .<Map.Entry<String, MoviePlottingDetails>, MoviePlottingDetails>chunk(100)
                .reader(jobReader())
                .processor(new MovieProcessor())
                .writer(jobWriter())
                .build();
        return jobBuilderFactory.get("movie_plotting_details_job")
                .incrementer(new RunIdIncrementer())
                .flow(step)
                .end()
                .build();
    }

}
