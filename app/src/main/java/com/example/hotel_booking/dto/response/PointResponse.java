package com.example.hotel_booking.dto.response;

import com.example.hotel_booking.entity.User;

public class PointResponse {
    private Integer idPoint;

    private Integer statusPoint;

    private Integer point;

    public PointResponse(Integer idPoint, Integer statusPoint, Integer point, Integer total) {
        this.idPoint = idPoint;
        this.statusPoint = statusPoint;
        this.point = point;
        this.total = total;
    }

    private Integer total;

    public Integer getIdPoint() {
        return idPoint;
    }

    public void setIdPoint(Integer idPoint) {
        this.idPoint = idPoint;
    }

    public Integer getStatusPoint() {
        return statusPoint;
    }

    public void setStatusPoint(Integer statusPoint) {
        this.statusPoint = statusPoint;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
