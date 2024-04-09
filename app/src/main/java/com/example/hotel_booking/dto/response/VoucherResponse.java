package com.example.hotel_booking.dto.response;

public class VoucherResponse {
    public Integer getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(Integer idVoucher) {
        this.idVoucher = idVoucher;
    }

    public String getNameVoucher() {
        return nameVoucher;
    }

    public void setNameVoucher(String nameVoucher) {
        this.nameVoucher = nameVoucher;
    }

    public Integer getPointVoucher() {
        return pointVoucher;
    }

    public void setPointVoucher(Integer pointVoucher) {
        this.pointVoucher = pointVoucher;
    }

    public Integer getPriceVoucher() {
        return priceVoucher;
    }

    public void setPriceVoucher(Integer priceVoucher) {
        this.priceVoucher = priceVoucher;
    }

    public VoucherResponse(Integer idVoucher, String nameVoucher, Integer pointVoucher, Integer priceVoucher) {
        this.idVoucher = idVoucher;
        this.nameVoucher = nameVoucher;
        this.pointVoucher = pointVoucher;
        this.priceVoucher = priceVoucher;
    }

    private Integer idVoucher;

    private String nameVoucher;

    private Integer pointVoucher;

    private Integer priceVoucher;
}
