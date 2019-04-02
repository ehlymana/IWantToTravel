package com.services.reservations.Controller;

import com.services.reservations.Exceptions.*;
import com.services.reservations.Services.HotelService;
import com.services.reservations.Services.ReservationService;
import com.services.reservations.Services.RoomService;
import com.services.reservations.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.services.reservations.Model.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@ComponentScan
public class ReservationController {

    private ReservationService reservationService;
    private UserService userService;
    private HotelService hotelService;
    private RoomService roomService;

    @Autowired
    public ReservationController(ReservationService reservationService, UserService userService, HotelService hotelService, RoomService roomService) {

        this.reservationService = reservationService;
        this.userService = userService;
        this.hotelService = hotelService;
        this.roomService = roomService;
    }

    @RequestMapping(value = "/addReservation", method = RequestMethod.POST)
    public String addReservation(@RequestParam(value="userLongitude") long userLongitude, @RequestParam(value="userLatitude") long userLatitude,
                                 @RequestParam(value="hotelLongitude") long hotelLongitude, @RequestParam(value="hotelLatitude") long hotelLatitude,
                                 @RequestParam(value="hotelID") long hotelID, @RequestParam(value="userID") long userID,
                                 @RequestParam(value="roomID") long roomID) {
        try {
            Hotel h = hotelService.findById(hotelID);
            if (h == null) throw new HotelDoesntExistException(hotelID);
            User u = userService.findById(userID);
            if (u == null) throw new UserDoesntExistException(userID);
            Room r = roomService.findById(roomID);
            if (r == null) throw new RoomDoesntExistException(roomID);
            Reservation reservation = new Reservation(userLongitude, userLatitude, hotelLongitude, hotelLatitude, h, u, r);
            if (reservationService.findById(reservation.getReservationID()) != null) throw new ReservationAlreadyExistsException(reservation.getReservationID());
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Reservation>> violations = validator.validate(reservation);
            if (violations.size() > 0) {
                for (ConstraintViolation<Reservation> violation : violations) {
                    System.out.println("**** ERROR: " + violation.getMessage() + " ****");
                    return violation.getMessage();
                }
                return "";
            } else {
                reservationService.save(reservation);
                System.out.println("**** Reservation successfully added! ****");
                return "Reservation with ID: " + reservation.getReservationID() +
                        "\n User Longitude: " + reservation.getUserLongitude() +
                        "\n User Latitude: " + reservation.getUserLatiitude() +
                        "\n Hotel Longitude: " + reservation.getHotelLongitude() +
                        "\n Hotel Latitude: " + reservation.getHotelLatiitude() +
                        "\n Hotel ID: " + reservation.getHotel().getHotelId() +
                        "\n User ID: " + reservation.getUser().getUserID() +
                        "\n Room ID: " + reservation.getRoom().getRoomId() +
                        "\n Successfully added!";
            }
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    @RequestMapping(value = "/findReservation", method = RequestMethod.POST)
    public Reservation findReservation(@RequestParam(value="id") long id) {
        Reservation r = reservationService.findById(id);
        if (r == null) throw new ReservationDoesntExistException(id);
        else {
            System.out.println("**** Reservation successfully found! ****");
            return r;
        }
    }
    @RequestMapping(value = "/deleteReservation", method = RequestMethod.POST)
    public String deleteReservation(@RequestParam(value="id") long id) {
        Reservation r = reservationService.findById(id);
        if (r == null) throw new ReservationNotFoundException(id);
        else {
            reservationService.delete(r);
            System.out.println("**** Reservation successfully deleted! ****");
            return "Reservation with ID: " + r.getReservationID() + " successfully deleted!";
        }
    }
    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public Iterable<Reservation> getReservations() {
        Iterable<Reservation> reservations = reservationService.findAll();
        System.out.println("**** Reservations successfully fetched! ****");
        return reservations;
    }
    @RequestMapping(value = "/reservationsByID", method = RequestMethod.GET)
    public List<Reservation> getReservationsByID(@RequestParam(value="hotelID") long hotelID, @RequestParam(value="userID") long userID,
                                             @RequestParam(value="roomID") long roomID) {
        Iterable<Reservation> reservations = reservationService.findAll();
        List<Reservation> listOfReservations = new ArrayList<Reservation>();
        for (Reservation r : reservations) {
            if (r.getHotel().getHotelId() == hotelID && r.getUser().getUserID() == userID
            && r.getRoom().getRoomId() == roomID) listOfReservations.add(r);
        }
        System.out.println("**** Reservations successfully fetched! ****");
        return listOfReservations;
    }
}