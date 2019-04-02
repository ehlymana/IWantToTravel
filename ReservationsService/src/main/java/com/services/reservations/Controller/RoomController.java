package com.services.reservations.Controller;

import com.services.reservations.Model.Room;
import com.services.reservations.Services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Controller
public class RoomController {

    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @RequestMapping(value = "/addRoom", method = RequestMethod.POST)
    public String addRoom() {
        try {
            Room r = new Room();
            roomService.save(r);
            System.out.println("**** Room successfully added! ****");
            return r.toString();
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}
