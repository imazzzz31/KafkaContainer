package com.Container.Producer.Service;

import com.Container.Producer.Dao.ContainerKafkaDao;
import com.Container.Producer.Model.ContainerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;

@Service
public class ContainerInfoService {
    @Autowired
    private ContainerKafkaDao dao;

    @Autowired
    private KafkaTemplate<String, ContainerInfo> kafkaTemplate;
    private static final String TOPIC = "containerInfo";

    public void publishInfo(){
        List<ContainerInfo> containerInfo = dao.getInfo();

        for(ContainerInfo container : containerInfo){
            kafkaTemplate.send(TOPIC, container);

            dao.markAsPublished(container.getContainerNumber());
        }
    }
}
