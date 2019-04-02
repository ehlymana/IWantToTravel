package com.services.reservations.Controller;

import com.services.reservations.Exceptions.HotelAlreadyExistsException;
import com.services.reservations.Exceptions.HotelDoesntExistException;
import com.services.reservations.Exceptions.HotelNotFoundException;
import com.services.reservations.Model.Hotel;
import com.services.reservations.Services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@RestController
@ComponentScan
public class HotelController {

    private HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @RequestMapping(value = "/addHotel", method = RequestMethod.POST)
    public String addHotel(@RequestParam(value="longitude") long longitude, @RequestParam(value="latitude") long latitude) {
        try {
            Hotel h = new Hotel(longitude, latitude);
            if (hotelService.findById(h.getHotelId()) != null) throw new HotelAlreadyExistsException(h.getHotelId());
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Hotel>> violations = validator.validate(h);
            if (violations.size() > 0) {
                for (ConstraintViolation<Hotel> violation : violations) {
                    System.out.println("**** ERROR: " + violation.getMessage() + " ****");
                    return violation.getMessage();
                }
                return "";
            } else {
                hotelService.save(h);
                System.out.println("**** Hotel successfully added! ****");
                return "Hotel with ID: " + h.getHotelId() +
                        "\n Longitude: " + h.getHotelLongitude() +
                        "\n Latitude: " + h.getHotelLatitude() +
                        "\n Successfully added!";
            }
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    @RequestMapping(value = "/findHotel", method = RequestMethod.POST)
    public Hotel findHotel(@RequestParam(value="id") long id) {
        Hotel h = hotelService.findById(id);
        if (h == null) throw new HotelDoesntExistException(id);
        else {
            System.out.println("**** Hotel successfully found! ****");
            return h;
        }
    }
    @RequestMapping(value = "/deleteHotel", method = RequestMethod.POST)
    public String deleteHotel(@RequestParam(value="id") long id) {
        Hotel h = hotelService.findById(id);
        if (h == null) throw new HotelNotFoundException(id);
        else {
            hotelService.delete(h);
            System.out.println("**** Hotel successfully deleted! ****");
            return "Hotel with ID: " + h.getHotelId() + " successfully deleted!";
        }
    }
    @RequestMapping(value = "/hotels", method = RequestMethod.GET)
    public Iterable<Hotel> getHotels() {
        Iterable<Hotel> hotels = hotelService.findAll();
        System.out.println("**** Hotels successfully fetched! ****");
        return hotels;
    }
}
