package com.rental.demo.entity.File;

import com.rental.demo.entity.Room;
import lombok.*;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Document(collection = "img_file")
@ToString
public class PdfFile {
    @Id
    private String id;
    private String name;
    private String landLordName;
    private String tenantName;
    private Room room;
    private int months;
    private LocalDateTime startDay;
    private LocalDateTime dueDay;
    private LocalDateTime createdTime;
    private Binary content;
    //private String contentType;
    //private long size;
}