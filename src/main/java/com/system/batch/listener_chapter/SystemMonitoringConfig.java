package com.system.batch.listener_chapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SystemMonitoringConfig {

    @Bean
    public Job systemMonitoringJob(JobRepository jobRepository, Step monitoringStep) {
        return new JobBuilder("systemMonitoringJob", jobRepository)
                .listener(new BigBrotherJobExecutionListener())
                .start(monitoringStep)
                .build();
    }

    @Bean
    public Step monitoringStep(JobRepository jobRepository) {
        return new StepBuilder("monitoringStep", jobRepository)
                .listener(new BigBrotherStepExecutionListener())
                .tasklet(((contribution, chunkContext) -> RepeatStatus.FINISHED), new ResourcelessTransactionManager())
                .build();
    }
}
