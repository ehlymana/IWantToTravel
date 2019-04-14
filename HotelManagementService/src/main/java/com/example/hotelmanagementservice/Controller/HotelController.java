package com.example.hotelmanagementservice.Controller;

import com.example.hotelmanagementservice.Model.Hotel;
//import com.example.hotelmanagementservice.Model.User;
import com.example.hotelmanagementservice.Model.User;
import com.example.hotelmanagementservice.Service.HotelService;
//import com.example.hotelmanagementservice.Service.UserService;
import com.example.hotelmanagementservice.Service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.bouncycastle.util.Iterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class HotelController {

    private HotelService hotelService;
    private UserService supervizorService;

    @Autowired
    public HotelController(HotelService hotelService, UserService supervizorService) {
        this.hotelService = hotelService;
        this.supervizorService = supervizorService;
    }


//    @RequestMapping(value = "/addHotel", method = RequestMethod.GET)
//    public String addHotel() throws Exception {
//        System.out.println("HOTELLLL");
//        try {
//            Hotel hotel = new Hotel(1, "Hollywood", "neki desc", "Sarajevo", "Adresa", 45, 47);
//            hotelService.save(hotel);
//        } catch (Exception e) {
//            throw new Exception("Something went wrong while adding hotel...");
//        }
//        System.out.println("gucci");
//        return "All good";
//    }

    @GetMapping(path = "/hotels")
    @ResponseBody
    public Iterable<Hotel> getAllHotels(){
        try {
            Iterable<Hotel> all = (Iterable<Hotel>) hotelService.findAll();
            return all;
        } catch(Exception e) {
            throw new HotelException("Hotels not found!");
        }
    }

    @GetMapping(path = "/hotels/{hotelID}")
    @ResponseBody
    public Hotel getHotel(@PathVariable("hotelID") Long id){
        try{
            Hotel h = hotelService.findById(id);
            return h;
        } catch (Exception e) {
            throw new HotelException("Could not get hotel!");
        }
    }

    @PostMapping("/hotels")
    @ResponseBody
    Hotel newHotel(@RequestBody JSONObject newHotel) {
        JSONObject json = new JSONObject();
        try {
            RestTemplate restTemplate = new RestTemplate();
            String userByIDURL = "http://localhost:8088/user/";
            System.out.println(newHotel.get("user").toString());
            ResponseEntity<String> response = restTemplate.getForEntity(userByIDURL + newHotel.get("user").toString(), String.class);
            System.out.println(response.toString());
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            System.out.println(root.toString());
            JsonNode uID = root.path("userID");
            JsonNode uLongitude = root.path("longitude");
            JsonNode uLatitude = root.path("latitude");
            //if (uID.asLong() != userID) throw new UserDoesntExistException(userID);
            User u = new User(uID.asLong(), uLongitude.asDouble(), uLatitude.asDouble());
            User user = supervizorService.findById(u.getUserID());
            if (user == null)supervizorService.save(u);
            else u = user;

            return hotelService.save(jsonToHotel(newHotel, u));
        } catch(Exception e) {
            json.put("status", HttpStatus.BAD_REQUEST);
            json.put("message", e.getMessage());
        }
        // json;
        return new Hotel();
    }

    private Hotel jsonToHotel(JSONObject json, User u) {
        String hName = json.get("hotelName").toString();
        String hDesc = json.get("hotelDescription").toString();
        String hLocation = json.get("hotelLocation").toString();
        String hAddress = json.get("hotelAddress").toString();
        Long hLong = Long.valueOf(json.get("hotelLongitude").toString());
        Long hLat = Long.valueOf(json.get("hotelLatitude").toString());
        Hotel h = new Hotel(u, hName, hDesc, hLocation, hAddress, hLong, hLat);
        return h;
    }


    @DeleteMapping(path = "/hotels/{hotelID}")
    @ResponseBody
    public String deleteHotel(@PathVariable("hotelID") Long id) {
        try{
            hotelService.deleteById(id);
        } catch (Exception e) {
            throw new HotelException("Hotel not found!");
        }
        return "Successfully deleted hotel!";
    }

    @PutMapping("/hotels/{hotelID}")
    Hotel replaceHotel(@RequestBody Hotel newHotel, @PathVariable("hotelID") Long id) {

        try {
            Hotel old = hotelService.findById(id);

            old.setHotelAddress(newHotel.getHotelAddress());
            old.setHotelDescription(newHotel.getHotelDescription());
            old.setHotelLatitude(newHotel.getHotelLatitude());
            old.setHotelLongitude(newHotel.getHotelLongitude());
            old.setHotelLocation(newHotel.getHotelLocation());
            old.setHotelName(newHotel.getHotelName());

            return hotelService.save(old);
        } catch (Exception e) {
            throw new HotelException("Something went wrong while updating the hotel! "+e.getMessage());
        }
    }
}
