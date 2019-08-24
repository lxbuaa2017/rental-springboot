package com.rental.demo.entity.File;

import lombok.*;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "img_file")
@ToString
public class PdfFile {
    @Id
    private String id;
    private String name;
    private String landLordName;
    private String tenantName;
    private LocalDateTime createdTime;
    private Binary content;
    private String contentType;
    private long size;
}