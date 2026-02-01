package com.Container.Producer.Scheduler;

import com.Container.Producer.Service.ContainerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ContainerInfoScheduler {

    @Autowired
    private ContainerInfoService service;

    @Scheduled (fixedDelay = 10000)
    public void runScheduler(){
        System.out.println("Scheduler Running");
        service.publishInfo();
    }
}
