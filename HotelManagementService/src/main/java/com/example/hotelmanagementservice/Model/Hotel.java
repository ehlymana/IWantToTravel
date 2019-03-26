package com.example.hotelmanagementservice.Model;

import javax.persistence.*;


@Entity
@Table(name = "HOTEL")
public class Hotel {

    @Id
    @Column(name = "HOTEL_ID", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long hotelId;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "SUPERVIZOR_ID", nullable = false)
//    User supervizor;

    @Column(name = "HOTEL_NAME", nullable = false)
    String hotelName;

    @Column(name = "HOTEL_DESCRIPTION")
    String hotelDescription;

    @Column(name = "HOTEL_LOCATION", nullable = false)
    String hotelLocation;

    @Column(name = "HOTEL_ADDRESS", nullable = false)
    String hotelAddress;

    @Column(name = "HOTE_LONGITUDE")
    long hotelLongitude;

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

    public Hotel() {
    }

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
