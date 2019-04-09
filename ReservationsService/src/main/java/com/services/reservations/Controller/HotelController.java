package com.services.reservations.Controller;

import com.services.reservations.Exceptions.HotelAlreadyExistsException;
import com.services.reservations.Exceptions.HotelDoesntExistException;
import com.services.reservations.Exceptions.HotelNotFoundException;
import com.services.reservations.Model.Hotel;
import com.services.reservations.Services.HotelService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.ws.Response;
import java.util.Set;

@RestController
@ComponentScan
public class HotelController {

    private HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @RequestMapping(value = "/addHotel", method = RequestMethod.POST, produces = "application/json")
    public JSONObject addHotel(@RequestParam(value="longitude") long longitude, @RequestParam(value="latitude") long latitude) {
        JSONObject json = new JSONObject();
        try {
            Hotel h = new Hotel(longitude, latitude);
            if (hotelService.findById(h.getHotelId()) != null) throw new HotelAlreadyExistsException(h.getHotelId());
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Hotel>> violations = validator.validate(h);
            if (violations.size() > 0) {
                for (ConstraintViolation<Hotel> violation : violations) {
                    System.out.println("**** ERROR: " + violation.getMessage() + " ****");
                    json.put("status", HttpStatus.BAD_REQUEST);
                    json.put("message", violation.getMessage());
                }
            } else {
                hotelService.save(h);
                System.out.println("**** Hotel successfully added! ****");
                json.put("status", HttpStatus.OK);
                json.put("hotel", h);
            }
        }
        catch (Exception e) {
            json.put("status", HttpStatus.BAD_REQUEST);
            json.put("message", e.getMessage());
        }
        return json;
    }
    @RequestMapping(value = "/findHotel", method = RequestMethod.POST, produces = "application/json")
    public JSONObject findHotel(@RequestParam(value="id") long id) {
        JSONObject json = new JSONObject();
        Hotel h = hotelService.findById(id);
        if (h == null) throw new HotelDoesntExistException(id);
        else {
            System.out.println("**** Hotel successfully found! ****");
            json.put("status", HttpStatus.OK);
            json.put("hotel", h);
            return json;
        }
    }
    @RequestMapping(value = "/deleteHotel", method = RequestMethod.POST, produces = "application/json")
    public JSONObject deleteHotel(@RequestParam(value="id") long id) {
        JSONObject json = new JSONObject();
        Hotel h = hotelService.findById(id);
        if (h == null) throw new HotelNotFoundException(id);
        else {
            hotelService.delete(h);
            System.out.println("**** Hotel successfully deleted! ****");
            json.put("status", HttpStatus.OK);
            json.put("hotel", h);
            return json;
        }
    }
    @RequestMapping(value = "/hotels", method = RequestMethod.GET, produces = "application/json")
    public JSONObject getHotels() {
        JSONObject json = new JSONObject();
        Iterable<Hotel> hotels = hotelService.findAll();
        System.out.println("**** Hotels successfully fetched! ****");
        json.put("status", HttpStatus.OK);
        json.put("hotels", hotels);
        return json;
    }
}
