package com.example.hotelmanagementservice.Controller;

import com.example.hotelmanagementservice.Model.Hotel;
import com.example.hotelmanagementservice.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class HotelController {

    private HotelService hotelService;
    //private UserService supervizorService;

    @Autowired
    public HotelController(HotelService hotelService/*, SupervizorService supervizorService*/) {
        this.hotelService = hotelService;
        //this.supervizorService = supervizorService;
    }


    @RequestMapping(value = "/addHotel", method = RequestMethod.POST)
    public String addHotel() {
        Hotel hotel = new Hotel(1, "Hollywood", "neki desc", "Sarajevo", "Adresa", 45, 47);
        hotelService.save(hotel);
        return "";
    }

    @PostMapping("/hotels")
    Hotel newHotel(@RequestBody Hotel newHotel) {
        return hotelService.save(newHotel);
    }
}

