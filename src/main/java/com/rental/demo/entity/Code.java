package com.rental.demo.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "code")
@ToString
public class Code {
    public String phone;
    public String code;
    public LocalDateTime createdTime;
}
