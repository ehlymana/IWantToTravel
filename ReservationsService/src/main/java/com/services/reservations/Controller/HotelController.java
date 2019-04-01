package com.services.reservations.Controller;

import com.services.reservations.Model.Hotel;
import com.services.reservations.Services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HotelController {

    private HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @RequestMapping(value = "/addHotel", method = RequestMethod.POST)
    public String addHotel() {
        return "";
    }

    @PostMapping("/hotels")
    Hotel newHotel(@RequestBody Hotel newHotel) {
        return hotelService.save(newHotel);
    }
}
