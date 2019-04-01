package com.services.reservations.Model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "RESERVATIONS")
public class Reservation {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long reservationID;

	@NotBlank(message = "User longitude must be specified!")
	@Size(min = -180, max = 180, message = "Longitude value out of range! Should be between -180 and 180!")
	@Column(name="USER_LONGITUDE", nullable = false)
	double userLongitude;

	@NotBlank(message = "User latitude must be specified!")
	@Size(min = -90, max = 90, message = "Latitude value out of range! Should be between -90 and 90!")
	@Column(name="USER_LATITUDE", nullable = false)
	double userLatitude;

	@NotBlank(message = "Hotel longitude must be specified!")
	@Size(min = -180, max = 180, message = "Longitude value out of range! Should be between -180 and 180!")
	@Column(name="HOTEL_LONGITUDE", nullable = false)
	double hotelLongitude;

	@NotBlank(message = "Hotel latitude must be specified!")
	@Size(min = -90, max = 90, message = "Latitude value out of range! Should be between -90 and 90!")
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