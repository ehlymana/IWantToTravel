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

    @RequestMapping(value = "/addreservation", method = RequestMethod.GET)
    public String addReservation() {
        User u1 = new User(10.0, 10.0);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations1 = validator.validate(u1);
        if(violations1.size() > 0) {
            for (ConstraintViolation<User> violation : violations1) {
                System.out.println("**** ERROR: " + violation.getMessage() + " ****");
            }
            return "";
        } else {
            userService.save(u1);
            System.out.println("**** User u1 successfully added! ****");
        }
        User u2 = new User(-10.0, -10.0);
        violations1 = validator.validate(u2);
        if(violations1.size() > 0) {
            for (ConstraintViolation<User> violation : violations1) {
                System.out.println("**** ERROR: " + violation.getMessage() + " ****");
            }
            return "";
        } else {
            userService.save(u2);
            System.out.println("**** User u2 successfully added! ****");
        }
        Room r = new Room();
        roomService.save(r);
        System.out.println("**** Room r successfully added! ****");
        Hotel h = new Hotel(50, 50);
        Set<ConstraintViolation<Hotel>> violations2 = validator.validate(h);
        if(violations2.size() > 0) {
            for (ConstraintViolation<Hotel> violation : violations2) {
                System.out.println("**** ERROR: " + violation.getMessage() + " ****");
            }
            return "";
        } else {
            hotelService.save(h);
            System.out.println("**** Hotel h successfully added! ****");
        }
        Reservation r1 = new Reservation(h, u1, r);
        Set<ConstraintViolation<Reservation>> violations3 = validator.validate(r1);
        if(violations3.size() > 0) {
            for (ConstraintViolation<Reservation> violation : violations3) {
                System.out.println("**** ERROR: " + violation.getMessage() + " ****");
            }
            return "";
        } else {
            reservationService.save(r1);
            System.out.println("**** Reservation r1 successfully added! ****");
        }
        Reservation r2 = new Reservation(h, u2, r);
        violations3 = validator.validate(r2);
        if(violations3.size() > 0) {
            for (ConstraintViolation<Reservation> violation : violations3) {
                System.out.println("**** ERROR: " + violation.getMessage() + " ****");
            }
            return "";
        } else {
            reservationService.save(r2);
            System.out.println("**** Reservation r2 successfully added! ****");
        }
        return "";
    }
}