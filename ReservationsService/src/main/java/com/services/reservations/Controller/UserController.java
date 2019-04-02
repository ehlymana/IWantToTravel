package com.services.reservations.Controller;

import com.services.reservations.Model.User;
import com.services.reservations.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.GET)
    public String addUser(@RequestParam(value="longitude") long longitude, @RequestParam(value="latitude") long latitude){
        try {
            User u = new User(longitude, latitude);
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<User>> violations = validator.validate(u);
            if (violations.size() > 0) {
                for (ConstraintViolation<User> violation : violations) {
                    System.out.println("**** ERROR: " + violation.getMessage() + " ****");
                    return violation.getMessage();
                }
                return "";
            } else {
                userService.save(u);
                System.out.println("**** User successfully added! ****");
                return u.toString();
            }
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}
