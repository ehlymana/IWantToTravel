package com.services.reservations.Controller;

import com.services.reservations.Services.HotelService;
import com.services.reservations.Services.ReservationService;
import com.services.reservations.Services.RoomService;
import com.services.reservations.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.services.reservations.Model.*;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Controller
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

    @RequestMapping(value = "/addReservation", method = RequestMethod.GET)
    public String addReservation(@RequestParam(value="userLongitude") long userLongitude, @RequestParam(value="userLatitude") long userLatitude,
                                 @RequestParam(value="hotelLongitude") long hotelLongitude, @RequestParam(value="hotelLatitude") long hotelLatitude,
                                 @RequestParam(value="hotelID") long hotelID, @RequestParam(value="userID") long userID,
                                 @RequestParam(value="roomID") long roomID) {
        try {
            Hotel h = hotelService.findById(hotelID);
            User u = userService.findById(userID);
            Room r = roomService.findById(roomID);
            Reservation reservation = new Reservation(userLongitude, userLatitude, hotelLongitude, hotelLatitude, h, u, r);
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
                return reservation.toString();
            }
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}