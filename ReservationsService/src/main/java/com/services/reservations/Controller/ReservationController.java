package com.services.reservations.Controller;

import com.services.reservations.Exceptions.*;
import com.services.reservations.Services.HotelService;
import com.services.reservations.Services.ReservationService;
import com.services.reservations.Services.RoomService;
import com.services.reservations.Services.UserService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
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

    @RequestMapping(value = "/addReservation", method = RequestMethod.POST, produces = "application/json")
    public JSONObject addReservation(@RequestParam(value="userLongitude") long userLongitude, @RequestParam(value="userLatitude") long userLatitude,
                                     @RequestParam(value="hotelLongitude") long hotelLongitude, @RequestParam(value="hotelLatitude") long hotelLatitude,
                                     @RequestParam(value="hotelID") long hotelID, @RequestParam(value="userID") long userID,
                                     @RequestParam(value="roomID") long roomID) {
        JSONObject json = new JSONObject();
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
                    json.put("status", HttpStatus.BAD_REQUEST);
                    json.put("message", violation.getMessage());
                }
            } else {
                reservationService.save(reservation);
                System.out.println("**** Reservation successfully added! ****");
                json.put("status", HttpStatus.OK);
                json.put("reservation", reservation);
            }
        }
        catch (Exception e) {
            json.put("status", HttpStatus.BAD_REQUEST);
            json.put("message", e.getMessage());
        }
        return json;
    }
    @RequestMapping(value = "/findReservation", method = RequestMethod.POST, produces = "application/json")
    public JSONObject findReservation(@RequestParam(value="id") long id) {
        JSONObject json = new JSONObject();
        Reservation r = reservationService.findById(id);
        if (r == null) throw new ReservationDoesntExistException(id);
        else {
            System.out.println("**** Reservation successfully found! ****");
            json.put("status", HttpStatus.OK);
            json.put("reservation", r);
            return json;
        }
    }
    @RequestMapping(value = "/deleteReservation", method = RequestMethod.POST, produces = "application/json")
    public JSONObject deleteReservation(@RequestParam(value="id") long id) {
        JSONObject json = new JSONObject();
        Reservation r = reservationService.findById(id);
        if (r == null) throw new ReservationNotFoundException(id);
        else {
            reservationService.delete(r);
            System.out.println("**** Reservation successfully deleted! ****");
            json.put("status", HttpStatus.OK);
            json.put("reservation", r);
            return json;
        }
    }
    @RequestMapping(value = "/reservations", method = RequestMethod.GET, produces = "application/json")
    public JSONObject getReservations() {
        JSONObject json = new JSONObject();
        Iterable<Reservation> reservations = reservationService.findAll();
        System.out.println("**** Reservations successfully fetched! ****");
        json.put("status", HttpStatus.OK);
        json.put("reservations", reservations);
        return json;
    }
    @RequestMapping(value = "/reservationsByID", method = RequestMethod.GET, produces = "application/json")
    public JSONObject getReservationsByID(@RequestParam(value="hotelID") long hotelID, @RequestParam(value="userID") long userID,
                                             @RequestParam(value="roomID") long roomID) {
        JSONObject json = new JSONObject();
        Iterable<Reservation> reservations = reservationService.findAll();
        List<Reservation> listOfReservations = new ArrayList<Reservation>();
        for (Reservation r : reservations) {
            if (r.getHotel().getHotelId() == hotelID && r.getUser().getUserID() == userID
            && r.getRoom().getRoomId() == roomID) listOfReservations.add(r);
        }
        System.out.println("**** Reservations successfully fetched! ****");
        json.put("status", HttpStatus.OK);
        json.put("reservations", listOfReservations);
        return json;
    }
}