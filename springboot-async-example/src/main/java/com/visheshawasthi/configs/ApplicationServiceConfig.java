package com.visheshawasthi.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class ApplicationServiceConfig {

  public final static String TASK_EXECUTOR = "taskExecutor";

  @Bean(name = TASK_EXECUTOR)
  public Executor getThreadPoolTaskExecutor(){
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(5);
    executor.setMaxPoolSize(5);
    executor.setQueueCapacity(10000);
    executor.setThreadNamePrefix(TASK_EXECUTOR + "-ASYNC-");
    executor.setWaitForTasksToCompleteOnShutdown(true);
    executor.initialize();
    return executor;
  }
}
