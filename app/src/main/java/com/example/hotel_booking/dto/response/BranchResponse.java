package com.example.hotel_booking.dto.response;

import androidx.room.Entity;

@Entity
public class BranchResponse {
    private Integer idBranch;

    public Integer getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(Integer idBranch) {
        this.idBranch = idBranch;
    }

    public String getNameHotel() {
        return nameHotel;
    }

    public void setNameHotel(String nameHotel) {
        this.nameHotel = nameHotel;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public BranchResponse(Integer idBranch, String nameHotel, String city, String province, String district,Integer rate,Float price) {
        this.idBranch = idBranch;
        this.nameHotel = nameHotel;
        this.city = city;
        this.province = province;
        this.district = district;
        this.rate = rate;
        this.price = price;
    }
    private Integer rate;

    private String nameHotel;


    private String district;

    public BranchResponse(String district) {
        this.district = district;
    }

    private String city;

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    private String province;

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    private Float price;
}
