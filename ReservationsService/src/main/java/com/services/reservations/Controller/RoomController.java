package com.services.reservations.Controller;

import com.services.reservations.Exceptions.RoomAlreadyExistsException;
import com.services.reservations.Exceptions.RoomDoesntExistException;
import com.services.reservations.Exceptions.RoomNotFoundException;
import com.services.reservations.Model.Room;
import com.services.reservations.Services.RoomService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@RestController
@ComponentScan
public class RoomController {

    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @RequestMapping(value = "/addRoom", method = RequestMethod.POST, produces = "application/json")
    public JSONObject addRoom() {
        JSONObject json = new JSONObject();
        try {
            Room r = new Room();
            if (roomService.findById(r.getRoomId()) != null) throw new RoomAlreadyExistsException(r.getRoomId());
            else {
                roomService.save(r);
                System.out.println("**** Room successfully added! ****");
                json.put("status", HttpStatus.OK);
                json.put("room", r);
            }
        }
        catch (Exception e) {
            json.put("status", HttpStatus.BAD_REQUEST);
            json.put("message", e.getMessage());
        }
        return json;
    }
    @RequestMapping(value = "/findRoom", method = RequestMethod.POST, produces = "application/json")
    public JSONObject findRoom(@RequestParam(value="id") long id) {
        JSONObject json = new JSONObject();
        Room r = roomService.findById(id);
        if (r == null) throw new RoomDoesntExistException(id);
        else {
            System.out.println("**** Room successfully found! ****");
            json.put("status", HttpStatus.OK);
            json.put("room", r);
            return json;
        }
    }
    @RequestMapping(value = "/deleteRoom", method = RequestMethod.POST, produces = "application/json")
    public JSONObject deleteRoom(@RequestParam(value="id") long id) {
        JSONObject json = new JSONObject();
        Room r = roomService.findById(id);
        if (r == null) throw new RoomNotFoundException(id);
        else {
            roomService.delete(r);
            System.out.println("**** Room successfully deleted! ****");
            json.put("status", HttpStatus.OK);
            json.put("room", r);
            return json;
        }
    }
    @RequestMapping(value = "/rooms", method = RequestMethod.GET, produces = "application/json")
    public JSONObject getRooms() {
        JSONObject json = new JSONObject();
        Iterable<Room> rooms = roomService.findAll();
        System.out.println("**** Rooms successfully fetched! ****");
        json.put("status", HttpStatus.OK);
        json.put("rooms", rooms);
        return json;
    }
}
