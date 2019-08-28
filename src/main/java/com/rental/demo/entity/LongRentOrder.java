package com.rental.demo.entity;

import com.rental.demo.entity.File.PdfFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@ToString
@Document(collection = "long_rent_order")
public class LongRentOrder {
    @Id
    private String Id;
    private String tenantName;
    private String tenantId;
    private String checkInDay;
    private String leaveDay;
    private int state;
    private Room room;
    private PdfFile pdffile;
    private LocalDateTime createdTime;
}
