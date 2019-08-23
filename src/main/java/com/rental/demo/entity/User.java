package com.rental.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
//@AllArgsConstructor
@ToString
public class User {
    @Id
    private String username;
    private String password;
    private String phone;
    private String email;
    private boolean isMale;
    private int age;

    public User(String username, String password, String phone, String email, boolean isMale, int age) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.isMale = isMale;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public boolean isMale() {
        return isMale;
    }

    public int getAge() {
        return age;
    }
}
