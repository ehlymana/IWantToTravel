package com.nwt.ums.Controller;

import com.nwt.ums.Model.Role;
import com.nwt.ums.Model.User;
import com.nwt.ums.Services.RoleService;
import com.nwt.ums.Services.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.validation.*;
import java.io.Console;
import java.util.HashMap;
import java.util.List;
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
    public UserController(UserService userService, RoleService roleService ) {

        this.userService = userService;
        this.roleService = roleService;
    }

    // this method adds user to db with specified role name
    @RequestMapping (value = "/adduser", method = RequestMethod.POST)
    public ResponseEntity<Object> addUser(@Valid User user, @RequestParam(value = "roleName", defaultValue = "ROLE_USER") String roleName, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws NotImplementedException {

        if(bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            String result = "";

            for (int i = 0; i <  fieldErrors.size() - 1; i++) {
                result += fieldErrors.get(i).getField();
                result += ", ";
            }
            result += fieldErrors.get(fieldErrors.size() - 1).getField();
            System.out.println(result);
            return new ResponseEntity<>("Binding error! Add user failed!", HttpStatus.BAD_REQUEST);
            // this is the code for showing errors on frontend
//            if(fieldErrors.size() > 1)
//                redirectAttributes.addFlashAttribute("failMessage", "Something went wrong! " + result + " fields are not valid. User not added.");
//            else
//                redirectAttributes.addFlashAttribute("failMessage", "Something went wrong! " + result + " field is not valid. User not added.");

        } else {
            Role role = roleService.findByRoleName(roleName);
            user.setRole(role);
            //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userService.save(user);
            // code for showig messages on frontend
            //redirectAttributes.addFlashAttribute("successMessage", "User added!");
            System.out.println("User with role " + roleName + " added!");

        }
        return new ResponseEntity<>("User with role " + roleName + " added!", HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findUserById (@PathVariable Long id) {
        User user = new User();
        user = userService.findById(id);
        if(user != null) {
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        }
        return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/delete/user/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        try {
            userService.delete(userService.findById(id));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("User deleted!", HttpStatus.OK);
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.GET)
    public String addUserget() throws NotImplementedException {

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

//    @RequestMapping(name = "/getuserTest", method = RequestMethod.GET)
//    public User getUserTest(@RequestParam(value = "username", defaultValue = "username") String username, @RequestParam(value = "password", defaultValue = "password") String password,
//                            @RequestParam(value = "firstName", defaultValue = "firstName") String firstName, @RequestParam (value = "lastName", defaultValue = "lastName") String lastName) {
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//        return user;
//    }




}
