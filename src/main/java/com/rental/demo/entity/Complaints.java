package com.rental.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "complaints")
@AllArgsConstructor
@ToString
public class Complaints {
    @Id
    private Long id;
    private String message;
    private Date createdTime;
    private String TenantUsername;
    private String MaintenancemanUsername;
}
