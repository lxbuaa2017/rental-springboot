package com.rental.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@ToString
@AllArgsConstructor
@Document(collection = "long_rent_order")
public class LongRentOrder {
    @Id
    private String id;
    private Date startTime;     //开始租的时间
    private Tenant tenant;
    private int state;
    private Room room;
    private boolean reminded;     //标记本月是否提醒过提交租金
}
