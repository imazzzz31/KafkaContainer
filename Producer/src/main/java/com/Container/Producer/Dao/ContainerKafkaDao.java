package com.Container.Producer.Dao;

import com.Container.Producer.Model.ContainerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ContainerKafkaDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class ContainerinfoMapper implements RowMapper<ContainerInfo> {
        @Override
        public ContainerInfo mapRow(ResultSet rs, int rowNum) throws SQLException{
            ContainerInfo container = new ContainerInfo();
            container.setContainerId(rs.getInt("container_id"));
            container.setContainerNumber(rs.getString("container_number"));
            container.setContainerType(rs.getString("container_type"));
            container.setContainerStatus(rs.getString("container_status"));
            container.setStatusDateTime(rs.getTimestamp("status_date_time").toLocalDateTime());
            container.setCurrentLocationCode(rs.getString("current_location_code"));
            container.setVesselName(rs.getString("vessel_name"));
            container.setVoyageNumber(rs.getString("voyage_number"));
            container.setPortOfLoading(rs.getString("port_of_loading"));
            container.setPortOfDischarge(rs.getString("port_of_discharge"));
            container.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            container.setPublishedToKafka(rs.getBoolean("published_to_kafka"));
            container.setPublishedAt(rs.getTimestamp("published_at").toLocalDateTime());
            return container;
        }
    }

    public List<ContainerInfo> getInfo(){
        String query = "CALL sp_get_unpublished_containers()";
        return jdbcTemplate.query(query, new ContainerinfoMapper());
    }

    public void markAsPublished(String containerNumber){
        String query = "CALL sp_container_action(2,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(query, containerNumber, null, null, null, null, null, null, null, null);
    }


}
