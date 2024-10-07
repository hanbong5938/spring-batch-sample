package com.moow.demo.global.job;

import com.moow.demo.domain.ChangeDomain;
import com.moow.demo.domain.Domain;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class CreateJobConfig {
    @Value("${chunkSize:1000}")
    private int chunkSize;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final ItemReader<Domain> itemReader;
    private final ItemProcessor<Domain, ChangeDomain> itemProcessor;
    private final ItemWriter<ChangeDomain> itemWriter;

    @Bean
    public Job changeDomainJob() {
        return new JobBuilder("changeDomainJob", jobRepository)
                .start(changeDomainStep())
                .build();
    }

    @Bean
    public Step changeDomainStep() {
        return new StepBuilder("changeDomainStep", jobRepository)
                .<Domain, ChangeDomain>chunk(chunkSize, transactionManager)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }
}
