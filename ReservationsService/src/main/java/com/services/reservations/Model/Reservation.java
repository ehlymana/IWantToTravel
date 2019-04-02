package com.services.reservations.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "RESERVATIONS")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reservation {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long reservationID;

	@NotNull(message = "User longitude must be specified!")
	@Min(value = -180, message = "User longitude value out of range! Should be between -180 and 180!")
	@Max(value = 180, message = "User longitude value out of range! Should be between -180 and 180!")
	@Column(name="USER_LONGITUDE", nullable = false)
	double userLongitude;

	@NotNull(message = "User latitude must be specified!")
	@Min(value = -90, message = "User latitude value out of range! Should be between -180 and 180!")
	@Max(value = 90, message = "User latitude value out of range! Should be between -180 and 180!")
	@Column(name="USER_LATITUDE", nullable = false)
	double userLatitude;

	@NotNull(message = "Hotel longitude must be specified!")
	@Min(value = -180, message = "Hotel longitude value out of range! Should be between -180 and 180!")
	@Max(value = 180, message = "Hotel longitude value out of range! Should be between -180 and 180!")
	@Column(name="HOTEL_LONGITUDE", nullable = false)
	double hotelLongitude;

	@NotNull(message = "Hotel latitude must be specified!")
	@Min(value = -90, message = "Hotel latitude value out of range! Should be between -180 and 180!")
	@Max(value = 90, message = "Hotel latitude value out of range! Should be between -180 and 180!")
	@Column(name="HOTEL_LATITUDE", nullable = false)
	double hotelLatitude;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "HOTEL_ID", nullable = false)
	private Hotel hotel;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "USER_ID", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ROOM_ID", nullable = false)
	private Room room;

	public Reservation(double uLongitude, double uLatitude, double hLongitude, double hLatitude, Hotel hotel, User user, Room room) {
		this.userLongitude = uLongitude;
		this.userLatitude = uLatitude;
		this.hotelLongitude = hLongitude;
		this.hotelLatitude = hLatitude;
		this.hotel = hotel;
		this.user = user;
		this.room = room;
	}

	public Reservation(Hotel hotel, User user, Room room) {
		this.userLongitude = user.getLongitude();
		this.userLatitude = user.getLatitude();
		this.hotelLongitude = hotel.getHotelLongitude();
		this.hotelLatitude = hotel.getHotelLatitude();
		this.hotel = hotel;
		this.user = user;
		this.room = room;
	}

	public Reservation(Reservation r) {
		this.userLongitude = r.userLongitude;
		this.userLatitude = r.userLatitude;
		this.hotelLongitude = r.hotelLongitude;
		this.hotelLatitude = r.hotelLatitude;
		this.hotel = r.hotel;
		this.user = r.user;
		this.room = r.room;
	}

	public Reservation() {

	}

	public Long getReservationID() {
		return reservationID;
	}

	public void setReservationID(Long id) {
		this.reservationID = id;
	}

	public double getUserLongitude() {
		return userLongitude;
	}

	public void setUserLongitude(double l) {
		this.userLongitude = l;
	}

	public double getUserLatiitude() {
		return userLatitude;
	}

	public void setUserLatitude(double l) {
		this.userLatitude = l;
	}

	public double getHotelLongitude() {
		return hotelLongitude;
	}

	public void setHotelLongitude(double l) {
		this.hotelLongitude = l;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel h) {
		this.hotel = h;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User u) {
		this.user = u;
	}
}