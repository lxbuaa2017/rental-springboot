package com.rental.demo.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@ToString
@Document(collection = "short_rent_order")
public class ShortRentOrder implements Serializable {
    @Id
    private String id;
    private String checkInDay;//中午十二点后可以入住
    private String leaveDay;//早上十二点之前必须离开
    private Tenant tenant;
    private int state;
    private Room room;
}
