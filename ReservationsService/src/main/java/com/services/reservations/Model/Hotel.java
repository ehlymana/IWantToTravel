package com.services.reservations.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "HOTEL")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Hotel {

    @Id
    @Column(name = "HOTEL_ID", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long hotelId;

    @NotNull(message = "Hotel longitude must be specified!")
    @Min(value = -180, message = "Hotel latitude value out of range! Should be between -180 and 180!")
    @Max(value = 180, message = "Hotel latitude value out of range! Should be between -180 and 180!")
    @Column(name = "HOTEL_LONGITUDE")
    private long hotelLongitude;

    @NotNull(message = "Hotel latitude must be specified!")
    @Min(value = -90, message = "Hotel latitude value out of range! Should be between -180 and 180!")
    @Max(value = 90, message = "Hotel latitude value out of range! Should be between -180 and 180!")
    @Column(name = "HOTEL_LATITUDE")
    private long hotelLatitude;

    public Hotel(long hotelLongitude, long hotelLatitude) {
        this.hotelLongitude = hotelLongitude;
        this.hotelLatitude = hotelLatitude;
    }

    public Hotel() {
    }

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    public long getHotelLongitude() {
        return hotelLongitude;
    }

    public void setHotelLongitude(long hotelLongitude) {
        this.hotelLongitude = hotelLongitude;
    }

    public long getHotelLatitude() {
        return hotelLatitude;
    }

    public void setHotelLatitude(long hotelLatitude) {
        this.hotelLatitude = hotelLatitude;
    }
}
