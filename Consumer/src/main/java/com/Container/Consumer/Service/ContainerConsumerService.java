package com.Container.Consumer.Service;

import com.Container.Consumer.Dao.ContainerInfoConsumerDAO;
import com.Container.Consumer.Model.ContainerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ContainerConsumerService {
    @Autowired
    private ContainerInfoConsumerDAO dao;

    @KafkaListener(topics = "containerInfo", groupId =  "containerConsumerGroup", containerFactory = "kafkaListenerContainerFactory")
    public void consume(ContainerInfo containerInfo){
        System.out.println("Received Container: " + containerInfo.getContainerNumber());
        dao.insertInfo(containerInfo);
        System.out.println("Inserted into Table: " + containerInfo.getContainerNumber());
    }
}
