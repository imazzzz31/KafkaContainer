package com.Container.Consumer.Dao;

import com.Container.Consumer.Model.ContainerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ContainerInfoConsumerDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertInfo(ContainerInfo container){
        jdbcTemplate.update("CALL sp_insert_container_consumer(?,?,?,?,?,?,?,?,?)",
                container.getContainerNumber(),
                container.getContainerType(),
                container.getContainerStatus(),
                container.getStatusDateTime(),
                container.getCurrentLocationCode(),
                container.getVesselName(),
                container.getVoyageNumber(),
                container.getPortOfLoading(),
                container.getPortOfDischarge()
        );
    }
}
