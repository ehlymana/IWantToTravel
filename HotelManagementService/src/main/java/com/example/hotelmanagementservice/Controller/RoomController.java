package com.example.hotelmanagementservice.Controller;

import com.example.hotelmanagementservice.Model.Hotel;
import com.example.hotelmanagementservice.Model.Room;
import com.example.hotelmanagementservice.Service.HotelService;
import com.example.hotelmanagementservice.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RoomController {
    private HotelService hotelService;
    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService, HotelService hotelService) {
        this.hotelService = hotelService;
        this.roomService = roomService;
    }


    @RequestMapping(value = "/addRoom", method = RequestMethod.GET)
    public String addRoom() {
        Hotel h = hotelService.findById(new Long(1));
        Room r = new Room(1, h, 4, "has wifi");
        roomService.save(r);
        return "";
    }
}
