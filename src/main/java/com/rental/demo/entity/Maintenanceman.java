package com.rental.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "maintenanceman")
@AllArgsConstructor
@ToString
public class Maintenanceman {
    @Id
    private String id;
    private String username;
    private String password;
    private String phone;
    private String realname;
    private int age;
    private boolean isMale;
    private int status;
    private double Ascore;
    private double times;
}
