package com.services.reservations.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "ROOMS_RESERVATION")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Room {

    @Id
    @Column(name = "ROOM_ID", nullable = false, updatable = false)
    private long roomId;

    public Room(long roomID) {
        this.roomId = roomID;
    }

    public Room() {

    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getRoomId() {
        return roomId;
    }
}
