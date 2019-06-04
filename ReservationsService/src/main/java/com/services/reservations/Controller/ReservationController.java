package com.services.reservations.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.EurekaClient;
import com.services.reservations.Exceptions.*;
import com.services.reservations.Services.HotelService;
import com.services.reservations.Services.ReservationService;
import com.services.reservations.Services.RoomService;
import com.services.reservations.Services.UserService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.services.reservations.Model.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@ComponentScan
public class ReservationController {

    private ReservationService reservationService;
    private HotelService hotelService;
    private RoomService roomService;
    private UserService userService;
    @Autowired
    private EurekaClient eurekaClient;

    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @LoadBalanced
    private RestTemplate loadBalanced;

    @Autowired
    public ReservationController(ReservationService reservationService, HotelService hotelService, RoomService roomService, UserService userService,  DiscoveryClient discoveryClient) {
        this.reservationService = reservationService;
        this.hotelService = hotelService;
        this.roomService = roomService;
        this.userService = userService;
        this.discoveryClient = discoveryClient;
    }

    // nejra
    @RequestMapping (value="/reservation/add", method = RequestMethod.POST)
    public String add() {
        Hotel hotel = new Hotel(1, 0, 0);
        hotelService.save(hotel);
        Room room = new Room(1);
        roomService.save(room);
        Reservation reservation = new Reservation(0, 0, 0, 0, hotel, room);
        reservationService.save(reservation);
        return "";
    }
	
	@RequestMapping(value = "/populateReservation", method = RequestMethod.POST)
    public String populate() throws Exception {
        System.out.println("Room database population has started...");
        try {
            Reservation r1 = new Reservation(0, 0, 0, 0, 1, 1, 1);
			Reservation r2 = new Reservation(10, 10, 10, 10, 2, 2, 2);
			reservationService.save(r1);
			reservationService.save(r2);
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return "Rooms successfully added!";
    }

    @RequestMapping(value = "/addReservation", method = RequestMethod.POST, produces = "application/json")
    public JSONObject addReservation(@RequestParam(value="hotelID") long hotelID, @RequestParam(value="userID") long userID,
                                     @RequestParam(value="roomID") long roomID) throws IOException {
        JSONObject json = new JSONObject();
        try {
            // sinhrona komunikacija 1 - treba nam hotel s porta 8089
            ServiceInstance serviceInstanceHotelsAndRooms = discoveryClient.getInstances("hotel-management-service").get(0);
            String url = "http://" + serviceInstanceHotelsAndRooms.getServiceId() + "/hotels/" + hotelID;
            ResponseEntity<String> response = loadBalanced.getForEntity(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode hID = root.path("hotelId");
            JsonNode hLongitude = root.path("hotelLongitude");
            JsonNode hLatitude = root.path("hotelLatitude");
            System.out.println(hID + " " + hLongitude + " " + hLatitude);
            if (hID.asLong() != hotelID) throw new HotelDoesntExistException(hotelID);
            Hotel h = hotelService.findById(hotelID);
            if (h == null) {
                h = new Hotel(hID.asLong(), hLongitude.asLong(), hLatitude.asLong());
                hotelService.save(h);
            }
            // sinhrona komunikacija 2 - treba nam user s porta 8088
            ServiceInstance serviceInstanceUsers = discoveryClient.getInstances("user-management-service").get(0);
            url = "http://" + serviceInstanceUsers.getServiceId() + "/user/" + userID;
            response = loadBalanced.getForEntity(url, String.class);
            mapper = new ObjectMapper();
            root = mapper.readTree(response.getBody());
            JsonNode uID = root.path("userID");
            JsonNode uLongitude = root.path("longitude");
            JsonNode uLatitude = root.path("latitude");
            System.out.println(uID + " " + uLongitude + " " + uLatitude);
            if (uID.asLong() != userID) throw new UserDoesntExistException(userID);
            User u = userService.findById(userID);
            if (u == null) {
                u = new User(uID.asLong(), uLongitude.asDouble(), uLatitude.asDouble());
                userService.save(u);
            }
            // sinhrona komunikacija 3 - treba nam room s porta 8089
            url = "http://" + serviceInstanceHotelsAndRooms.getServiceId() + "/rooms/" + roomID;
            response = loadBalanced.getForEntity(url, String.class);
            root = mapper.readTree(response.getBody());
            JsonNode rID = root.path("roomId");
            System.out.println(rID);
            if (rID.asLong() != roomID) throw new RoomDoesntExistException(roomID);
            Room r = roomService.findById(roomID);
            if (r == null) {
                r = new Room(rID.asLong());
                roomService.save(r);
            }
            Reservation reservation = new Reservation(u.getLongitude(), u.getLatitude(), h.getHotelLongitude(), h.getHotelLatitude(), h, u, r);
            if (reservationService.findById(reservation.getReservationID()) != null) throw new ReservationAlreadyExistsException(reservation.getReservationID());
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Reservation>> violations = validator.validate(reservation);
            if (violations.size() > 0) {
                for (ConstraintViolation<Reservation> violation : violations) {
                    System.out.println("**** ERROR: " + violation.getMessage() + " ****");
                    json.put("status", HttpStatus.BAD_REQUEST);
                    json.put("message", violation.getMessage());
                }
            } else {
                reservationService.save(reservation);
                System.out.println("**** Reservation successfully added! ****");
                json.put("status", HttpStatus.OK);
                json.put("reservation", reservation);
            }
        }
        catch (Exception e) {
            json.put("status", HttpStatus.BAD_REQUEST);
            json.put("message", e.getMessage());
        }
        return json;
    }
    @RequestMapping(value = "/findReservation", method = RequestMethod.POST, produces = "application/json")
    public JSONObject findReservation(@RequestParam(value="id") long id) {
        JSONObject json = new JSONObject();
        Reservation r = reservationService.findById(id);
        if (r == null) throw new ReservationDoesntExistException(id);
        else {
            System.out.println("**** Reservation successfully found! ****");
            json.put("status", HttpStatus.OK);
            json.put("reservation", r);
            return json;
        }
    }

    // nejra
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hi() {
        return "Hello from Reservations Service";
    }

    @RequestMapping(value = "/deleteReservation", method = RequestMethod.POST, produces = "application/json")
    public JSONObject deleteReservation(@RequestParam(value="id") long id) {
        JSONObject json = new JSONObject();
        Reservation r = reservationService.findById(id);
        if (r == null) throw new ReservationNotFoundException(id);
        else {
            reservationService.delete(r);
            System.out.println("**** Reservation successfully deleted! ****");
            json.put("status", HttpStatus.OK);
            json.put("reservation", r);
            return json;
        }
    }
    @RequestMapping(value = "/reservations", method = RequestMethod.GET, produces = "application/json")
    public JSONObject getReservations() {
        JSONObject json = new JSONObject();
        Iterable<Reservation> reservations = reservationService.findAll();
        System.out.println("**** Reservations successfully fetched! ****");
        json.put("status", HttpStatus.OK);
        json.put("reservations", reservations);
        return json;
    }
    @RequestMapping(value = "/reservationsByID", method = RequestMethod.GET, produces = "application/json")
    public JSONObject getReservationsByID(@RequestParam(value="hotelID") long hotelID, @RequestParam(value="userID") long userID,
                                             @RequestParam(value="roomID") long roomID) {
        JSONObject json = new JSONObject();
        Iterable<Reservation> reservations = reservationService.findAll();
        List<Reservation> listOfReservations = new ArrayList<Reservation>();
        for (Reservation r : reservations) {
            if (r.getHotel().getHotelId() == hotelID && r.getUser().getUserID() == userID
            && r.getRoom().getRoomId() == roomID) listOfReservations.add(r);
        }
        System.out.println("**** Reservations successfully fetched! ****");
        json.put("status", HttpStatus.OK);
        json.put("reservations", listOfReservations);
        return json;
    }
    @RequestMapping(value = "/allReservationsFromUser", method = RequestMethod.GET, produces = "application/json")
    public JSONObject getReservationsByUserID(@RequestParam(value="userID") long userID) {
        JSONObject json = new JSONObject();
        Iterable<Reservation> reservations = reservationService.findAll();
        List<Reservation> listOfReservations = new ArrayList<Reservation>();
        for (Reservation r : reservations) {
            if (r.getUser().getUserID() == userID) listOfReservations.add(r);
        }
        System.out.println("**** Reservations successfully fetched! ****");
        json.put("status", HttpStatus.OK);
        json.put("reservations", listOfReservations);
        return json;
    }
    @RequestMapping(value = "/filterReservations", method = RequestMethod.GET, produces = "application/json")
    public JSONObject getReservationsByHotelID(@RequestParam(value="hotelID") long hotelID) {
        JSONObject json = new JSONObject();
        Iterable<Reservation> reservations = reservationService.findAll();
        List<Reservation> listOfReservations = new ArrayList<Reservation>();
        for (Reservation r : reservations) {
            if (r.getHotel().getHotelId() == hotelID) listOfReservations.add(r);
        }
        System.out.println("**** Reservations successfully fetched! ****");
        json.put("status", HttpStatus.OK);
        json.put("reservations", listOfReservations);
        return json;
    }

    // nejra
    @RequestMapping(value = "/reservations/user/{userID}", method = RequestMethod.GET, produces = "application/json")
    public Iterable<Reservation> findReservationsByUserID(@PathVariable long userID) {

        try {
            Iterable<Reservation> reservations = reservationService.findAll();
            return reservations;
        } catch(Exception e) {
            throw new UserDoesntExistException(userID);
        }

    }
}