package com.example.hotelmanagementservice.Controller;

import com.example.hotelmanagementservice.Model.Hotel;
import com.example.hotelmanagementservice.Model.Room;
import com.example.hotelmanagementservice.Service.HotelService;
import com.example.hotelmanagementservice.Service.RoomService;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;

@RestController
public class RoomController {
    private HotelService hotelService;
    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService, HotelService hotelService) {
        this.hotelService = hotelService;
        this.roomService = roomService;
    }
    @RequestMapping(value = "/populateRoom", method = RequestMethod.POST)
    public String populate() throws Exception {
        System.out.println("Room database population has started...");
        try {
            Hotel h1 = hotelService.findById(new Long(33));
            Hotel h2 = hotelService.findById(new Long(34));
            Room r1 = new Room(h1, 1, "Description 1", false);
            Room r2 = new Room(h2, 2, "Description 1", false);
            roomService.save(r1);
            roomService.save(r2);
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return "Rooms successfully added!";
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
	
	@GetMapping(path = "/roomsByHotel/{hotelName}")
    @ResponseBody
    public List<Room> getRoomsByHotel(@PathVariable("hotelName") String name){
        try{
            Iterable<Room> r = roomService.findAll();
			Iterable<Hotel> h = hotelService.findAll();
			long id = -1;
			for (Hotel hotel : h) {
				if (hotel.getHotelName().equals(name)) id = hotel.getHotelId();
			}
			if (id == -1) throw new RoomException("Could not find room!");
			List<Room> listOfRooms = new ArrayList<Room>();
			for (Room room : r) {
				if (room.getHotel().getHotelId() == id && !room.getOccupied()) listOfRooms.add(room);
			}
            return listOfRooms;
        } catch (Exception e) {
            throw new RoomException("Could not find room!");
        }
    }

    @PostMapping("/rooms")
    @ResponseBody
    Room newRoom(@RequestBody JSONObject newRoom) {
        try {
            System.out.println(newRoom);

            System.out.println( newRoom.get("roomDescription").toString());
            System.out.println(Integer.valueOf( newRoom.get("roomBeds").toString()));
            System.out.println(Long.valueOf(newRoom.get("hotel").toString()));
            return roomService.save(new Room(hotelService.findById(Long.valueOf(newRoom.get("hotel").toString())), Integer.valueOf( newRoom.get("roomBeds").toString()), newRoom.get("roomDescription").toString(), (Boolean)newRoom.get("occupied")));
        } catch(Exception e) {
            throw new RoomException("Something went wrong, room was not created!");
        }
    }
	
@RequestMapping(value = "/occupy", method = RequestMethod.GET, produces = "application/json")
    public JSONObject filterHotels(@RequestParam(value="roomID") long id) {
        JSONObject json = new JSONObject();
        Room room = roomService.findById(id);
		if (room == null) throw new RoomException("Could not find hotel!");
        room.setOccupied(!room.getOccupied());
		roomService.save(room);
        System.out.println("**** Room occupation successfully changed! ****");
        json.put("status", HttpStatus.OK);
        json.put("room", room);
		return json;
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
//ovaj dio pravi clash sa get-om za roomId, pa bi se trebala promijeniti ruta na roomsByHotel/{hotelID} ili nešto drugo po želji
    /*@GetMapping("/rooms/{hotelID}")
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

    }*/



}
