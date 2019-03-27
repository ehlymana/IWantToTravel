package com.services.reservations.Controller;

import com.services.reservations.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.services.reservations.Model.*;

@Controller
public class ReservationController {
    ReservationService reservationService;
    @Autowired
    public ReservationController(ReservationService reservationService) {

        this.reservationService = reservationService;
    }
    @RequestMapping(value = "/addreservation", method = RequestMethod.GET)
    public String addReservation() {
        Reservation r1 = new Reservation(50.0, 25.5, 10.5, 5.5);
        Reservation r2 = new Reservation(10.7, 10.7, 50.11, 67.3);
        reservationService.save(r1);
        reservationService.save(r2);
        return "";
    }
}