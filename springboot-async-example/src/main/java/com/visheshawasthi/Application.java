package com.visheshawasthi;

import com.visheshawasthi.service.ApplicationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@Log4j2
@EnableAsync
@SpringBootApplication
public class Application implements CommandLineRunner {

  @Autowired
  private ApplicationService applicationService;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    applicationService.doSomethingAsynchronously();
    log.info("Called Async option will take 5sec to complete");
  }
}
