package com.example.hotelmanagementservice.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Table(name = "USER_HOTELM")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @Column(name = "USER_ID", nullable = false, updatable = false)
    private long userID;

    @NotNull(message = "Longitude must be specified!")
    @Min(value = -180, message = "Longitude value out of range! Should be between -180 and 180!")
    @Max(value = 180, message = "Longitude value out of range! Should be between -180 and 180!")
    @Column(name = "LONGITUDE")
    private double longitude;

    @NotNull(message = "Latitude must be specified!")
    @Min(value = -90, message = "Latitude value out of range! Should be between -90 and 90!")
    @Max(value = 90, message = "Latitude value out of range! Should be between -90 and 90!")
    @Column(name="LATITUDE", nullable = false)
    private double latitude;

    public User(){}

    public User(long userID, double longitude, double latitude) {
        this.userID = userID;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public User(User user) {
        this.longitude = user.longitude;
        this.latitude = user.latitude;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

}
