package com.rental.demo.entity;

import com.rental.demo.entity.Room;
import com.rental.demo.entity.Tenant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.swing.*;
import java.util.Date;

@Data
@Document(collection = "workorder")
@AllArgsConstructor
@ToString
public class WorkOrder {            //工单
    @Id
    private Long id;                 //工单号
    private Room room;               //需要处理的房间
    private ImageIcon image;         //图片
    private String description;      //问题描述
    private Date orderTime;          //工单建立日期
    private Tenant tenant;           //报修的租户
    //todo 添加处理工单的师傅id 未指定时为null
    private boolean isFinished;      //表示工单是否完成
    private Date finishTime;         //工单完成日期
}
