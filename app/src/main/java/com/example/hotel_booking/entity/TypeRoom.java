package com.example.hotel_booking.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TypeRoom {
    @PrimaryKey
    private Long idTypeRoom;

    private String nameType;

    public TypeRoom(Long idTypeRoom, String nameType) {
        this.idTypeRoom = idTypeRoom;
        this.nameType = nameType;
    }

    public Long getIdTypeRoom() {
        return idTypeRoom;
    }

    public void setIdTypeRoom(Long idTypeRoom) {
        this.idTypeRoom = idTypeRoom;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }
}
