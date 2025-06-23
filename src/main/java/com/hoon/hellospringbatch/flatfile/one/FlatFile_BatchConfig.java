package com.hoon.hellospringbatch.flatfile.one;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class FlatFile_BatchConfig {

    @Bean
    public FlatFileItemReader<FileData> fileReader(
            @Value("${input.file}") String inputFile) {

        return new FlatFileItemReaderBuilder<FileData>()
                .name("fileReader")
                .resource(new FileSystemResource(inputFile))
                .delimited().delimiter(",")
                .names("id", "name", "value")
                .targetType(FileData.class)
                .build();
    }

    @Bean
    public ItemProcessor<FileData, DataEntity> dataProcessor() {
        return fileData -> new DataEntity(
                fileData.id(),
                fileData.name(),
                fileData.value()
        );
    }

    @Bean
    public JpaItemWriter<DataEntity> dbWriter(EntityManagerFactory entityManagerFactory) {
        JpaItemWriter<DataEntity> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

    @Bean
    public Step fileToDbStep(JobRepository jobRepository,
                             PlatformTransactionManager transactionManager,
                             ItemReader<FileData> fileReader,
                             ItemProcessor<FileData, DataEntity> dataProcessor,
                             ItemWriter<DataEntity> dbWriter) {

        return new StepBuilder("fileToDbStep", jobRepository)
                .<FileData, DataEntity>chunk(100, transactionManager)
                .reader(fileReader)
                .processor(dataProcessor)
                .writer(dbWriter)
                .build();
    }

    @Bean
    public Job fileToDbJob(JobRepository jobRepository, Step fileToDbStep) {
        return new JobBuilder("fileToDbJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(fileToDbStep)
                .build();
    }
}