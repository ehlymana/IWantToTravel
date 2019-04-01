package com.services.reservations.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "HOTEL")
public class Hotel {

    @Id
    @Column(name = "HOTEL_ID", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long hotelId;

    @NotBlank(message = "Hotel longitude must be specified!")
    @Size(min = -180, max = 180, message = "Longitude value out of range! Should be between -180 and 180!")
    @Column(name = "HOTEL_LONGITUDE")
    private long hotelLongitude;

    @NotBlank(message = "Hotel latitude must be specified!")
    @Size(min = -90, max = 90, message = "Latitude value out of range! Should be between -90 and 90!")
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
