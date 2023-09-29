package com.taco.pass.passbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

@EnableBatchProcessing
@SpringBootApplication
public class PassBatchApplication {


    @Bean
    public Step passStep(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("passStep", jobRepository)
                .chunk(1000, transactionManager)
                .build();
    }

    @Bean
    public Job passJob(JobRepository jobRepository, Step step){
        return new JobBuilder("passJob", jobRepository)
                .start(step)
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(PassBatchApplication.class, args);
    }

}
