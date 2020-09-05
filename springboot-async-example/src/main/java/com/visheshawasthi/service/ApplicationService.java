package com.visheshawasthi.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ApplicationService {

  public final static String TASK_EXECUTOR = "taskExecutor";

  @Async(TASK_EXECUTOR)
  public void doSomethingAsynchronously(){
    log.info("Entering doSomethingAsynchronously");
    try {
      Thread.sleep(5000L); //delay of 5 sec
      System.out.println("Something happened here");
    } catch (Exception e) {
      log.error("Exception occurred in doSomethingAsynchronously", e);
    }
  }

}
