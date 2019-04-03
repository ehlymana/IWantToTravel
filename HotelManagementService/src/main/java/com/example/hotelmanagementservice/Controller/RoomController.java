package com.example.hotelmanagementservice.Controller;

import com.example.hotelmanagementservice.Model.Room;
import com.example.hotelmanagementservice.Service.HotelService;
import com.example.hotelmanagementservice.Service.RoomService;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RoomController {
    private HotelService hotelService;
    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService, HotelService hotelService) {
        this.hotelService = hotelService;
        this.roomService = roomService;
    }


//    @RequestMapping(value = "/addRoom/{hotelId}", method = RequestMethod.GET)
//    public String addRoom(@PathVariable("hotelId") Long id) {
//        System.out.println("HERE");
//        try {
//            Hotel h = hotelService.findById(id);
//            Room r = new Room(1, h, 4, "has wifi");
//            roomService.save(r);
//        } catch (Exception e) {
//            return "There has been an error";
//        }
//        return "Successfully added a room!";
//    }

    @GetMapping(path = "/rooms")
    @ResponseBody
    public Iterable<Room> getAllRooms(){
        try {
            Iterable<Room> all = roomService.findAll();
            return all;
        } catch(Exception e) {
            throw new RoomException("Rooms not found!");
        }
    }

    @GetMapping(path = "/rooms/{roomID}")
    @ResponseBody
    public Room getRoom(@PathVariable("roomID") Long id){
        try{
            Room h = roomService.findById(id);
            return h;
        } catch (Exception e) {
            throw new RoomException("Could not get room!");
        }
    }

    @PostMapping("/rooms")
    @ResponseBody
    Room newRoom(@RequestBody Object newRoom) {
        try {
            return roomService.save(new Room(hotelService.findById((long) 4), 3, "opis"));
        } catch(Exception e) {
            throw new RoomException("Something went wrong, room was not created!");
        }
    }

    @DeleteMapping(path = "/rooms/{roomID}")
    @ResponseBody
    public String deleteRoom(@PathVariable("roomID") Long id) {
        try{
            roomService.deleteById(id);
        } catch (Exception e) {
            throw new RoomException("Room not found!");
        }
        return "Successfully deleted";
    }

    @PutMapping("/rooms/{roomID}")
    Room replaceRoom(@RequestBody Room newRoom, @PathVariable("roomID") Long id) {
        try {
            Room old = roomService.findById(id);

            //old.setHotel(newRoom.getHotel());
            old.setRoomBeds(newRoom.getRoomBeds());
            old.setRoomDescription(newRoom.getRoomDescription());

            return roomService.save(old);
        } catch(Exception e) {
            throw new RoomException("Something went wrong while updating the room! "+e.getMessage());
        }
    }

    @GetMapping("/rooms/{hotelID}")
    Iterable<Room> sameHotel(@PathVariable("hotelID") Long id) {
        try {
            Iterable<Room> filter = roomService.findAll();
            for (Room r : filter) {
                if (r.getHotel().getHotelId() != id) {
                    filter.iterator().remove();
                }
            }
            return filter;
        } catch (Exception e) {
            throw new RoomException("Something went wrong...");
        }

    }



}
