package com.services.reservations.Controller;

import com.services.reservations.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReservationController {
    ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {

        this.reservationService = reservationService;
    }

    // temporary za zadatak 2
    @RequestMapping(value = "/addreservation", method = RequestMethod.GET)
    public String tempAddReservation() {
        // code for adding reservation
        return "";
    }
}
