package com.services.reservations.Controller;

import com.services.reservations.Exceptions.UserAlreadyExistsException;
import com.services.reservations.Exceptions.UserDoesntExistException;
import com.services.reservations.Exceptions.UserNotFoundException;
import com.services.reservations.Model.User;
import com.services.reservations.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
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
@ComponentScan
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@RequestParam(value="longitude") long longitude, @RequestParam(value="latitude") long latitude){
        try {
            User u = new User(longitude, latitude);
            if (userService.findById(u.getUserID()) != null) throw new UserAlreadyExistsException(u.getUserID());
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
                return "User with ID: " + u.getUserID() +
                        "\n Longitude: " + u.getLongitude() +
                        "\n Latitude: " + u.getLatitude() +
                        "\n Successfully added!";
            }
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    @RequestMapping(value = "/findUser", method = RequestMethod.POST)
    public User findUser(@RequestParam(value="id") long id) {
        User u = userService.findById(id);
        if (u == null) throw new UserDoesntExistException(id);
        else {
            System.out.println("**** User successfully found! ****");
            return u;
        }
    }
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public String deleteUser(@RequestParam(value="id") long id) {
        User u = userService.findById(id);
        if (u == null) throw new UserNotFoundException(id);
        else {
            userService.delete(u);
            System.out.println("**** User successfully deleted! ****");
            return "User with ID: " + u.getUserID() + " successfully deleted!";
        }
    }
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Iterable<User> getUsers() {
        Iterable<User> users = userService.findAll();
        System.out.println("**** Users successfully fetched! ****");
        return users;
    }
}
