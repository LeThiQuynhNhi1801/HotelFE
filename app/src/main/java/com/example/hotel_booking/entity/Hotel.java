package com.example.hotel_booking.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Hotel {
    @PrimaryKey
    private Long idHotel;
    private String hotelName;

    public Long getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Long idHotel) {
        this.idHotel = idHotel;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
}
