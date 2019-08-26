package com.rental.demo.entity.File;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Document(collection = "img_file")
@ToString
public class ImgFile {
    @Id
    private String id;
    private String name;
    private LocalDateTime createdTime;
    private Binary content;
    private String contentType;
    private long size;

    public Binary getContent() {
        return content;
    }
}
