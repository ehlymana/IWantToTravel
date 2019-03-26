package com.example.hotelmanagementservice.Controller;

import com.example.hotelmanagementservice.Model.Hotel;
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
//        User user = new User("jdoe", "novipass", "Jane7", "Doe7", 10.0, 10.0, "ROLE_USER");
//        userService.save(user);
//String s = "INSERT INTO Hotel VALUES(1, 'Hollywood', 'neki desc', 'Sarajevo', 'Adresa', 45, 47)";
        Hotel hotel = new Hotel(1, "Hollywood", "neki desc", "Sarajevo", "Adresa", 45, 47);
        hotelService.save(hotel);
        return "";
    }
}

