package com.services.reservations.Controller;

import com.services.reservations.Exceptions.UserAlreadyExistsException;
import com.services.reservations.Exceptions.UserDoesntExistException;
import com.services.reservations.Exceptions.UserNotFoundException;
import com.services.reservations.Model.User;
import com.services.reservations.Services.UserService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
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

    @RequestMapping(value = "/addUser", method = RequestMethod.POST, produces = "application/json")
    public JSONObject addUser(@RequestParam(value="userID") long userID, @RequestParam(value="longitude") double longitude, @RequestParam(value="latitude") double latitude){
        JSONObject json = new JSONObject();
        try {
            User u = new User(userID, longitude, latitude);
            if (userService.findById(u.getUserID()) != null) throw new UserAlreadyExistsException(u.getUserID());
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<User>> violations = validator.validate(u);
            if (violations.size() > 0) {
                for (ConstraintViolation<User> violation : violations) {
                    System.out.println("**** ERROR: " + violation.getMessage() + " ****");
                    json.put("status", HttpStatus.BAD_REQUEST);
                    json.put("message", violation.getMessage());
                }
            } else {
                userService.save(u);
                System.out.println("**** User successfully added! ****");
                json.put("status", HttpStatus.OK);
                json.put("user", u);
            }
        }
        catch (Exception e) {
            json.put("status", HttpStatus.BAD_REQUEST);
            json.put("message", e.getMessage());
        }
        return json;
    }
    @RequestMapping(value = "/findUser", method = RequestMethod.POST, produces = "application/json")
    public JSONObject findUser(@RequestParam(value="id") long id) {
        JSONObject json = new JSONObject();
        User u = userService.findById(id);
        if (u == null) throw new UserDoesntExistException(id);
        else {
            System.out.println("**** User successfully found! ****");
            json.put("status", HttpStatus.OK);
            json.put("user", u);
            return json;
        }
    }
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST, produces = "application/json")
    public JSONObject deleteUser(@RequestParam(value="id") long id) {
        JSONObject json = new JSONObject();
        User u = userService.findById(id);
        if (u == null) throw new UserNotFoundException(id);
        else {
            userService.delete(u);
            System.out.println("**** User successfully deleted! ****");
            json.put("status", HttpStatus.OK);
            json.put("user", u);
            return json;
        }
    }
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
    public JSONObject getUsers() {
        JSONObject json = new JSONObject();
        Iterable<User> users = userService.findAll();
        System.out.println("**** Users successfully fetched! ****");
        json.put("status", HttpStatus.OK);
        json.put("users", users);
        return json;
    }
    @RequestMapping(value = "/populateUser", method = RequestMethod.POST)
    public String populate() throws Exception {
        System.out.println("User database population has started...");
        try {
            User u = new User(1, 10, 10);
            userService.save(u);
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return "Users successfully added!";
    }
}
