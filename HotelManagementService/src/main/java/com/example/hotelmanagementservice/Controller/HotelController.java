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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HotelController {

    private HotelService hotelService;
    private UserService supervizorService;

    @Autowired
    public HotelController(HotelService hotelService, UserService supervizorService) {
        this.hotelService = hotelService;
        this.supervizorService = supervizorService;
    }
	@RequestMapping(value = "/addHotel", method = RequestMethod.POST)
    public String addHotelNew(@RequestParam(value="hotelName") String hotelName, @RequestParam(value="hotelDescription") String hotelDescription, @RequestParam(value="hotelLocation") String hotelLocation, @RequestParam(value="hotelAddress") String hotelAddress, @RequestParam(value="hotelLongitude") long hotelLongitude, @RequestParam(value="hotelLatitude") long hotelLatitude) throws Exception {
		System.out.println("Hotel is being added...");
        try {
            Hotel h = new Hotel(null, hotelName, hotelDescription, hotelLocation, hotelAddress, hotelLongitude, hotelLatitude);
			hotelService.save(h);
		}
		catch (Exception e) {
            throw new Exception("Something went wrong while adding hotel...");
        }
		return "Hotel successfully added!";
	}
	
	@RequestMapping(value = "/populateHotel", method = RequestMethod.POST)
	public String populate() throws Exception {
		System.out.println("Hotel database population has started...");
        try {
            User u1 = supervizorService.findById(new Long(8));
            User u2 = supervizorService.findById(new Long(9));
            Hotel h1 = new Hotel(u1, "Europa", "Opis hotela", "Sarajevo", "Carsija", 0, 0);
			Hotel h2 = new Hotel(u2, "Holiday Inn", "Opis hotela", "Sarajevo", "Marijin Dvor", 10, 10);
			Hotel h3 = new Hotel(u1, "Hollywood", "Opis hotela", "Sarajevo", "Ilidza", 20, 20);
			Hotel h4 = new Hotel(u2, "Marriott", "Opis hotela", "Sarajevo", "Skenderija", 30, 30);
			hotelService.save(h1);
			hotelService.save(h2);
			hotelService.save(h3);
			hotelService.save(h4);
		}
		catch (Exception e) {
            throw new Exception(e.getMessage());
        }
		return "Hotels successfully added!";
	}
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
			System.out.println("Service is running!");
			return "Hello from Hotel Management Service";
	}

    @RequestMapping(value = "/addHotel", method = RequestMethod.GET)
    public String addHotel() throws Exception {
        System.out.println("HOTELLLL");
        try {
            Hotel hotel = new Hotel(new User(0, 0), "Hollywood", "neki desc", "Sarajevo", "Adresa", 45, 47);
            hotelService.save(hotel);
        } catch (Exception e) {
            throw new Exception("Something went wrong while adding hotel...");
        }
        System.out.println("gucci");
        return "All good";
    }

    @GetMapping(path = "/hotels")
    @ResponseBody
    public JSONObject getAllHotels(){
        try {
            JSONObject json = new JSONObject();
            Iterable<Hotel> all = hotelService.findAll();
            json.put("hotels", all);
            return json;
        } catch(Exception e) {
            throw new HotelException(e.getMessage());
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
            User u = new User(uLongitude.asDouble(), uLatitude.asDouble());
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
	@RequestMapping(value = "/filterHotels", method = RequestMethod.GET, produces = "application/json")
    public JSONObject filterHotels(@RequestParam(value="hotelLongitude") long longitude, @RequestParam(value="hotelLatitude") long latitude) {
        JSONObject json = new JSONObject();
        Iterable<Hotel> hotels = hotelService.findAll();
        List<Hotel> listOfHotels = new ArrayList<Hotel>();
        for (Hotel h : hotels) {
            if (h.getHotelLongitude() == longitude && h.getHotelLatitude() == latitude) listOfHotels.add(h);
        }
        System.out.println("**** Hotels successfully fetched! ****");
        json.put("status", HttpStatus.OK);
        json.put("hotels", listOfHotels);
        return json;
    }
	
	@RequestMapping(value = "/hotelByName", method = RequestMethod.GET, produces = "application/json")
    public Hotel filterHotels(@RequestParam(value="hotelName") String hotelName) {
        JSONObject json = new JSONObject();
        Iterable<Hotel> hotels = hotelService.findAll();
        for (Hotel h : hotels) {
            if (h.getHotelName().equals(hotelName)) return h;
        }
        System.out.println("**** Hotels successfully fetched! ****");
        return null;
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
