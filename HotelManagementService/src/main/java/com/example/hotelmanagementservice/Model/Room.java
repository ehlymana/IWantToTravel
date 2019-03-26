package com.example.hotelmanagementservice.Model;

import javax.persistence.*;

@Entity
@Table(name = "ROOM")
public class Room {

    @Id
    @Column(name = "ROOM_ID", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roomId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "HOTEL_ID", nullable = false)
    private Hotel hotel;

    @Column(name = "ROOM_BEDS", nullable = false)
    private long roomBeds;

    @Column(name = "ROOM_DESCRIPTION")
    private String roomDescription;

    public Room(long id, Hotel hotel, long roomBeds, String roomDescription) {
        this.roomId = id;
        this.hotel = hotel;
        this.roomBeds = roomBeds;
        this.roomDescription = roomDescription;
    }

    public Room() {
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setRoomBeds(long roomBeds) {
        this.roomBeds = roomBeds;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public long getRoomId() {
        return roomId;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public long getRoomBeds() {
        return roomBeds;
    }

    public String getRoomDescription() {
        return roomDescription;
    }
}
