package com.rental.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "room")
@AllArgsConstructor
@ToString
public class Room {
    @Id
    private Long roomId;
    private String type;
    private LandLord landLord;
    private Tenant renter;
    private LocalDateTime rentTime;
    private LocalDateTime dueTime;
    private int state;
}
