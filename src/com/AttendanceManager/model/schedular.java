package com.AttendanceManager.model;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class schedular {

    @Scheduled(cron = "0 0 0 * * ?")
    public void scheduleTaskWithCronExpression() {
    	System.out.println("this is schedular");
    }
}
