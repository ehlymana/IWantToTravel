package com.services.reservations.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.services.reservations.Exceptions.*;
import com.services.reservations.Services.HotelService;
import com.services.reservations.Services.ReservationService;
import com.services.reservations.Services.RoomService;
import com.services.reservations.Services.UserService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.services.reservations.Model.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
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
    public ReservationController(ReservationService reservationService, HotelService hotelService, RoomService roomService, UserService userService) {
        this.reservationService = reservationService;
        this.hotelService = hotelService;
        this.roomService = roomService;
        this.userService = userService;
    }

    @RequestMapping(value = "/addReservation", method = RequestMethod.POST, produces = "application/json")
    public JSONObject addReservation(@RequestParam(value="hotelID") long hotelID, @RequestParam(value="userID") long userID,
                                     @RequestParam(value="roomID") long roomID) {
        JSONObject json = new JSONObject();
        try {
            // sinhrona komunikacija 1 - treba nam hotel s porta 8089
            RestTemplate restTemplate = new RestTemplate();
            String hotelByIDURL = "http://localhost:8089/hotels/";
            ResponseEntity<String> response = restTemplate.getForEntity(hotelByIDURL + Long.toString(hotelID), String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode hID = root.path("hotelID");
            JsonNode hLongitude = root.path("hotelLongitude");
            JsonNode hLatitude = root.path("hotelLatitude");
            if (hID.asLong() != hotelID) throw new HotelDoesntExistException(hotelID);
            Hotel h = new Hotel(hID.asLong(), hLongitude.asLong(), hLatitude.asLong());
            Hotel hotel = hotelService.findById(h.getHotelId());
            if (hotel == null)hotelService.save(h);
            else h = hotel;
            // sinhrona komunikacija 2 - treba nam user s porta 8088
            String userByIDURL = "http://localhost:8088/user/";
            response = restTemplate.getForEntity(userByIDURL + Long.toString(userID), String.class);
            mapper = new ObjectMapper();
            root = mapper.readTree(response.getBody());
            JsonNode uID = root.path("userID");
            JsonNode uLongitude = root.path("longitude");
            JsonNode uLatitude = root.path("latitude");
            if (uID.asLong() != userID) throw new UserDoesntExistException(userID);
            User u = new User(uID.asLong(), uLongitude.asDouble(), uLatitude.asDouble());
            User user = userService.findById(u.getUserID());
            if (user == null)userService.save(u);
            else u = user;
            // sinhrona komunikacija 3 - treba nam room s porta 8089
            String roomByID = "http://localhost:8089/rooms/";
            response = restTemplate.getForEntity(roomByID + Long.toString(roomID), String.class);
            root = mapper.readTree(response.getBody());
            JsonNode rID = root.path("roomID");
            if (rID.asLong() != roomID) throw new RoomDoesntExistException(roomID);
            Room r = new Room(rID.asLong());
            Room room = roomService.findById(r.getRoomId());
            if (room == null)roomService.save(r);
            else r = room;
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
}