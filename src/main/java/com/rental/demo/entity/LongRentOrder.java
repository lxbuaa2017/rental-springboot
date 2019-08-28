package com.rental.demo.entity;

import com.rental.demo.entity.File.PdfFile;
import com.rental.demo.entity.Room;
import com.rental.demo.entity.Tenant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Document(collection = "long_rent_order")
public class LongRentOrder {
    @Id
    private String Id;
    private Tenant tenant;
    private String tenantId;
    private String checkInDay;
    private String leaveDay;
    private int state;
    private Room room;
    private PdfFile pdffile;
}
