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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLandLordName() {
        return landLordName;
    }

    public void setLandLordName(String landLordName) {
        this.landLordName = landLordName;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public LocalDateTime getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalDateTime startDay) {
        this.startDay = startDay;
    }

    public LocalDateTime getDueDay() {
        return dueDay;
    }

    public void setDueDay(LocalDateTime dueDay) {
        this.dueDay = dueDay;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public Binary getContent() {
        return content;
    }

    public void setContent(Binary content) {
        this.content = content;
    }
}