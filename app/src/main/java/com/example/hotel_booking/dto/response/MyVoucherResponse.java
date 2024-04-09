package com.example.hotel_booking.dto.response;

public class MyVoucherResponse {
    private String nameVoucher;

    private Integer id;

    public MyVoucherResponse(String nameVoucher, Integer id, Integer point, Integer price, Integer status) {
        this.nameVoucher = nameVoucher;
        this.id = id;
        this.point = point;
        this.price = price;
        this.status = status;
    }

    private Integer point;

    public String getNameVoucher() {
        return nameVoucher;
    }

    public void setNameVoucher(String nameVoucher) {
        this.nameVoucher = nameVoucher;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private Integer price;

    private Integer status;
}
