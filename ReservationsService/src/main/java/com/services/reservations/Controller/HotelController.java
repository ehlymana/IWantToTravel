package com.services.reservations.Controller;

import com.services.reservations.Model.Hotel;
import com.services.reservations.Services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@RestController
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
                return h.toString();
            }
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("/hotels")
    Hotel newHotel(@RequestBody Hotel newHotel) {
        return hotelService.save(newHotel);
    }
}
