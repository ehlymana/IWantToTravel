package com.nwt.ums.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "HOTELS_RESERVATION")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Hotel {

    @Id
    @Column(name = "HOTEL_ID", nullable = false, updatable = false)
    private Long hotelId;

    @NotNull(message = "Hotel longitude must be specified!")
    @Min(value = -180, message = "Hotel longitude value out of range! Should be between -180 and 180!")
    @Max(value = 180, message = "Hotel longitude value out of range! Should be between -180 and 180!")
    @Column(name = "HOTEL_LONGITUDE")
    private double hotelLongitude;

    @NotNull(message = "Hotel latitude must be specified!")
    @Min(value = -90, message = "Hotel latitude value out of range! Should be between -180 and 180!")
    @Max(value = 90, message = "Hotel latitude value out of range! Should be between -180 and 180!")
    @Column(name = "HOTEL_LATITUDE")
    private double hotelLatitude;

    public Hotel(Long hotelID, double hotelLongitude, double hotelLatitude) {
        this.hotelId = hotelID;
        this.hotelLongitude = hotelLongitude;
        this.hotelLatitude = hotelLatitude;
    }

    public Hotel() {
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public double getHotelLongitude() {
        return hotelLongitude;
    }

    public void setHotelLongitude(double hotelLongitude) {
        this.hotelLongitude = hotelLongitude;
    }

    public double getHotelLatitude() {
        return hotelLatitude;
    }

    public void setHotelLatitude(double hotelLatitude) {
        this.hotelLatitude = hotelLatitude;
    }
}
