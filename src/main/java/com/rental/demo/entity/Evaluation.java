package com.rental.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "evaluation")
@AllArgsConstructor
@ToString
public class Evaluation {
    @Id
    private String id;
    private String TenantUsername;
    private String message;
    private String MaintenancemanUsername;
    private Date LastChangeTime;
    private Date CreatedTime;
}
