package com.example.reviewservice.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "HOTEL")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Hotel {

    @Id
    @Column(name = "HOTEL_ID", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long hotelId;

    @NotNull
    @Size(min=1, message="Hotel name should be at least one character long!")
    @Column(name = "HOTEL_NAME", nullable = false)
    String hotelName;


    public Hotel(@NotNull @Size(min = 1, message = "Hotel name should be at least one character long!") String hotelName) {
        this.hotelName = hotelName;
    }

    public Hotel() {}

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
}
