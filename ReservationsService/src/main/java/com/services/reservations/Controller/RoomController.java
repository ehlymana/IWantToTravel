package com.services.reservations.Controller;

import com.services.reservations.Exceptions.RoomAlreadyExistsException;
import com.services.reservations.Exceptions.RoomDoesntExistException;
import com.services.reservations.Exceptions.RoomNotFoundException;
import com.services.reservations.Model.Room;
import com.services.reservations.Services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
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

    @RequestMapping(value = "/addRoom", method = RequestMethod.POST)
    public String addRoom() {
        try {
            Room r = new Room();
            if (roomService.findById(r.getRoomId()) != null) throw new RoomAlreadyExistsException(r.getRoomId());
            else {
                roomService.save(r);
                System.out.println("**** Room successfully added! ****");
                return "Room with ID: " + r.getRoomId() + " successfully added!";
            }
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    @RequestMapping(value = "/findRoom", method = RequestMethod.POST)
    public Room findRoom(@RequestParam(value="id") long id) {
        Room r = roomService.findById(id);
        if (r == null) throw new RoomDoesntExistException(id);
        else {
            System.out.println("**** Room successfully found! ****");
            return r;
        }
    }
    @RequestMapping(value = "/deleteRoom", method = RequestMethod.POST)
    public String deleteRoom(@RequestParam(value="id") long id) {
        Room r = roomService.findById(id);
        if (r == null) throw new RoomNotFoundException(id);
        else {
            roomService.delete(r);
            System.out.println("**** Room successfully deleted! ****");
            return "Room with ID: " + r.getRoomId() + " successfully deleted!";
        }
    }
    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    public Iterable<Room> getRooms() {
        Iterable<Room> rooms = roomService.findAll();
        System.out.println("**** Rooms successfully fetched! ****");
        return rooms;
    }
}
