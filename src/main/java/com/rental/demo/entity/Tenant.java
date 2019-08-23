package com.rental.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "tenant")
@AllArgsConstructor
@ToString
public class Tenant {
    @Id
    private String username;
    private String password;
    private String phone;
    private String email;
    private boolean isMale;
    private int age;
}
