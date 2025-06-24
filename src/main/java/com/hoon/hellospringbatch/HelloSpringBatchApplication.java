package com.hoon.hellospringbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;


@SpringBootApplication
//public class HelloSpringBatchApplication implements CommandLineRunner {
public class HelloSpringBatchApplication{
//	private final JobLauncher jobLauncher;
//	private final Job fileToDbJob;
//	private final Environment environment;
//
//	public HelloSpringBatchApplication(JobLauncher jobLauncher,
//							Job fileToDbJob,
//							Environment environment) {
//		this.jobLauncher = jobLauncher;
//		this.fileToDbJob = fileToDbJob;
//		this.environment = environment;
//	}

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringBatchApplication.class, args);
	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		JobParameters params = new JobParametersBuilder()
//				.addString("input.file", environment.getProperty("input.file"))
//				.addLong("time", System.currentTimeMillis())
//				.toJobParameters();
//
//		jobLauncher.run(fileToDbJob, params);
//	}

}
