package com.nwt.ums.Controller;

import com.nwt.ums.Model.Role;
import com.nwt.ums.Model.User;
import com.nwt.ums.Services.RoleService;
import com.nwt.ums.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class UserController {
    //private static final Map<String, String> testMap = new HashMap<>();
    private static final String username = "" + "nkulovic1";
    private static final String password = "nejrapassword";
    private static final String firstName = "Nejra";
    private static final String lastName = "Kulovic";

    private UserService userService;
    private RoleService roleService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {

        this.userService = userService;
        this.roleService = roleService;
//        testMap.put("username", "nkulovic");
//        testMap.put("password", "testPassword");
//        testMap.put("firstName", "Nejra");
//        testMap.put("lastName", "Kulovic");
//        testMap.put("longitude", "0.0");
//        testMap.put("latitude", "0.0");
//        testMap.put("email", "nkulovic1@etf.unsa.ba");
//        testMap.put("confirmToken", "");
//        testMap.put("passwordToken", "");
//        testMap.put("reactivateToken", "");
//        testMap.put("enabled", "true");
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.GET)
    public String addUser(){

        Role role = new Role("ROLE_USERNAME", Long.parseLong("1"));
        // trebalo bi dodati hashiranje password-a
        User user = new User("nkulovic1", "npassword", "Nejra", "Kulovic", role, "nkulovic1@etf.unsa.ba", 0.0, 0.0, "", "", "", true);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if(violations.size() > 0) {
            for (ConstraintViolation<User> violation : violations) {
                System.out.println("ERROR: " + violation.getMessage());
            }
            return "";
        } else {
            //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            roleService.save(role);
            userService.save(user);
            //role.setUserId(user.getUserID());

        }
        return "";
    }

//    @RequestMapping(name = "/getuser", method = RequestMethod.GET)
//    public User getUser(@RequestParam(value = "testMap", defaultValue = "testMap") Map<String, String> user) {
//        //User user = new User()
//        return new User(user.get("username"), user.get("password"), user.get("firstName"), user.get("lastName"),
//                        user.get("email"), Double.parseDouble(user.get("longitude")), Double.parseDouble(user.get("latitude")), user.get("confirmToken"),
//                        user.get("reactivateToken"), user.get("passwordToken"), Boolean.parseBoolean(user.get("enabled")));
//    }

    @RequestMapping(name = "/getuserTest", method = RequestMethod.GET)
    public User getUserTest(@RequestParam(value = "username", defaultValue = "username") String username, @RequestParam(value = "password", defaultValue = "password") String password,
                            @RequestParam(value = "firstName", defaultValue = "firstName") String firstName, @RequestParam (value = "lastName", defaultValue = "lastName") String lastName) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }
}
