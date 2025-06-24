package com.hoon.hellospringbatch.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;


// , InitializingBean
@Slf4j
public class GreetingTask implements Tasklet{
    @Override
    public RepeatStatus execute(StepContribution contribution,
                                ChunkContext chunkContext) throws Exception {
        log.info("------------------ Task Execute -----------------");
        log.info("00------------------ Task Execute -----------------");
        log.info("GreetingTask: {}, {}", contribution, chunkContext);

        return RepeatStatus.FINISHED;
    }

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        log.info("00----------------- After Properites Sets() --------------");
//    }
}