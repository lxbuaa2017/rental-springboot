package com.rental.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "complaints")
@AllArgsConstructor
@ToString
public class Complaints {
    @Id
    private String id;
    private String message;
    private LocalDateTime createdTime;
    private String tenantUsername;
    private String taintenancemanUsername;
    private String reply;
    private int state;
    private List<String> urls;
}
