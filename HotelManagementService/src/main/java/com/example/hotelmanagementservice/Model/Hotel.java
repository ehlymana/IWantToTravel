package com.example.hotelmanagementservice.Model;

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

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "SUPERVIZOR_ID", nullable = false)
//    User supervizor;

    @NotNull
    @Size(min=1, message="Hotel name should be at least one character long!")
    @Column(name = "HOTEL_NAME", nullable = false)
    String hotelName;

    @NotNull(message = "Hotel description should not be null!")
    @Column(name = "HOTEL_DESCRIPTION")
    String hotelDescription;

    @NotNull
    @Size(min=1, message = "Hotel location should be at least one character long!")
    @Column(name = "HOTEL_LOCATION", nullable = false)
    String hotelLocation;

    @NotNull
    @Size(min=6, message = "Hotel address should have at least six characters!")
    @Column(name = "HOTEL_ADDRESS", nullable = false)
    String hotelAddress;

    @NotNull(message = "Hotel longitude not provided!")
    //@Size(min = -180, max = 180, message = "Longitude value out of range! Should be between -180 and 180!")
    @Column(name = "HOTE_LONGITUDE")
    long hotelLongitude;

    @NotNull(message = "Hotel latitude not provided!")
    //@Size(min = -90, max = 90, message = "Longitude value out of range! Should be between -90 and 90!")
    @Column(name = "HOTE_LATITUDE")
    long hotelLatitude;

    public Hotel(long id, /*Supervizor supervizor,*/ String hotelName, String hotelDescription, String hotelLocation, String hotelAddress, long hotelLongitude, long hotelLatitude) {
        this.hotelId = id;
        //this.supervizor = supervizor;
        this.hotelName = hotelName;
        this.hotelDescription = hotelDescription;
        this.hotelLocation = hotelLocation;
        this.hotelAddress = hotelAddress;
        this.hotelLongitude = hotelLongitude;
        this.hotelLatitude = hotelLatitude;
    }

    public Hotel(){}

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

//    public Supervizor getSupervizor() {
//        return supervizor;
//    }
//
//    public void setSupervizor(Supervizor supervizor) {
//        this.supervizor = supervizor;
//    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelDescription() {
        return hotelDescription;
    }

    public void setHotelDescription(String hotelDescription) {
        this.hotelDescription = hotelDescription;
    }

    public String getHotelLocation() {
        return hotelLocation;
    }

    public void setHotelLocation(String hotelLocation) {
        this.hotelLocation = hotelLocation;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
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
