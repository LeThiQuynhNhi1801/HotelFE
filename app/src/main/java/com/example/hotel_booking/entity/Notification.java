package com.example.hotel_booking.entity;

import org.threeten.bp.LocalDateTime;

import java.io.Serializable;

public class Notification implements Serializable {
    private int imgId;
    private String content;

    private int type;

    private LocalDateTime date;

    public Notification(int imgId, String content, int type, LocalDateTime date) {
        this.imgId = imgId;
        this.content = content;
        this.type = type;
        this.date = date;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDate() {
        return String.format(String.valueOf(date));
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
