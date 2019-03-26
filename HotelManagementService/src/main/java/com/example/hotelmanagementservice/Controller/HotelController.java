package com.example.hotelmanagementservice.Controller;

import com.example.hotelmanagementservice.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HotelController {

    private HotelService hotelService;
    //private UserService supervizorService;

    @Autowired
    public HotelController(HotelService hotelService/*, SupervizorService supervizorService*/) {
        this.hotelService = hotelService;
        //this.supervizorService = supervizorService;
    }


    @RequestMapping(value = "/addHotel", method = RequestMethod.GET)
    public String addHotel() {

        return "";
    }
}

