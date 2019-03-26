package Model;

import javax.persistence.*;

@Entity
@Table(name = "RESERVATIONS")
public class Reservation {
	@Id
	@Column(name="RESERVATION_ID", nullable = false, updatable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	    private Long reservationID;
	    @Column(name="USER_LONGITUDE", nullable = false)
	    double userLongitude;
	    @Column(name="USER_LATITUDE", nullable = false)
	    double userLatitude;
	    @Column(name="HOTEL_LONGITUDE", nullable = false)
	    double hotelLongitude;
	    @Column(name="HOTEL_LATITUDE", nullable = false)
	    double hotelLatitude;
	    /*@ManyToOne
	    private Hotel hotel;
	    @ManyToOne
	    private User user;*/
	    public Reservation(Long id, double uLongitude, double uLatitude, double hLongitude, double hLatitude/*, Hotel hotel, User user*/) {
	        this.reservationID = id;
	        this.userLongitude = uLongitude;
	        this.userLatitude = uLatitude;
	        this.hotelLongitude = hLongitude;
	        this.hotelLatitude = hLatitude;
	        /*this.hotel = hotel;
	        this.user = user;*/
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
	    /*public Hotel getHotel() {
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
	    }*/
}
