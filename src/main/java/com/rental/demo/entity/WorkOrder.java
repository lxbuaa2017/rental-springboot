package com.rental.demo.entity;

import com.rental.demo.util.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "workorder")
@ToString
public class WorkOrder {
    @Id
    private String id;
    private String message;
    private String tenantName;
    private int stats;
    private String maintenanceName;
    private List<String> urls;
    private int score;
    private LocalDateTime createdTime;
    private  String address;
}
