package com.Container.Consumer.Model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContainerInfo {
    private Integer containerId;
    private String containerNumber;
    private String containerType;
    private String containerStatus;
    private LocalDateTime statusDateTime;
    private String currentLocationCode;
    private String vesselName;
    private String voyageNumber;
    private String portOfLoading;
    private String portOfDischarge;
    private LocalDateTime createdAt;
}
