package com.rental.demo.entity;

import com.rental.demo.entity.File.ImgFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "room")
@ToString
public class Room {
    @Id
    private String roomId;
    private String type;
    private String address;
    private int priceForDay;
    private int priceForMonth;
    private String area;//单位㎡
    private LandLord landLord;
    private Tenant renter;
    private LocalDateTime rentTime;
    private LocalDateTime dueTime;
    private int state;
    private List<String> imageUrls;
}
