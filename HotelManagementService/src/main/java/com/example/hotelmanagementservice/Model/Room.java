package com.example.hotelmanagementservice.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ROOM")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Room {

    @Id
    @Column(name = "ROOM_ID", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roomId;

    @NotNull(message = "Hotel ID cannot be null!")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "HOTEL_ID", unique=true, referencedColumnName = "HOTEL_ID")
    private Hotel hotel;

    @NotNull(message = "Number of beds cannot be null!")
    @Min(value = 1, message = "Number of beds must be greater than zero!")
    @Column(name = "ROOM_BEDS", nullable = false)
    private int roomBeds;

    @Column(name = "ROOM_DESCRIPTION")
    private String roomDescription;
	
	@Column(name = "OCCUPIED")
	private boolean occupied;

    public Room(Hotel hotel, int roomBeds, String roomDescription, boolean occupied) {
        //this.roomId = id;
        this.hotel = hotel;
        this.roomBeds = roomBeds;
        this.roomDescription = roomDescription;
		this.occupied = occupied;
    }

    public Room(){}

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setRoomBeds(int roomBeds) {
        this.roomBeds = roomBeds;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }
	
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

    public long getRoomId() {
        return roomId;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public int getRoomBeds() {
        return roomBeds;
    }

    public String getRoomDescription() {
        return roomDescription;
    }
	
	public boolean getOccupied() {
		return occupied;
	}
}
