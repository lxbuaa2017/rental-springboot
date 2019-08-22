package com.rental.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
@AllArgsConstructor
@ToString
public class User {
    @Id
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private boolean isMale;
    private int age;
}
