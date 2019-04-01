package com.services.reservations.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userID;

    @NotBlank(message = "Longitude must be specified!")
    @Size(min = -180, max = 180, message = "Longitude value out of range! Should be between -180 and 180!")
    @Column(name = "LONGITUDE")
    private double longitude;

    @NotBlank(message = "Latitude must be specified!")
    @Size(min = -90, max = 90, message = "Latitude value out of range! Should be between -90 and 90!")
    @Column(name="LATITUDE", nullable = false)
    private double latitude;

    public User(){}

    public User(double longitude, double latitude) {
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
