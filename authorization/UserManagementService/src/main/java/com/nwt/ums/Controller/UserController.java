package com.nwt.ums.Controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.nwt.ums.Exceptions.UserException;
import com.nwt.ums.Model.Hotel;
import com.nwt.ums.Model.Reservation;
import com.nwt.ums.Model.Role;
import com.nwt.ums.Model.User;
import com.nwt.ums.Services.RoleService;
import com.nwt.ums.Services.UserService;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import org.springframework.security.core.Authentication;

import javax.validation.*;
import java.io.IOException;
import java.util.List;
import java.util.Set;

// @RefreshScope
@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {

    private UserService userService;
    private RoleService roleService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EurekaClient eurekaClient;

    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @LoadBalanced
    private RestTemplate loadBalanced;


//    @Value("${vcap.application.instance_id:${spring.application.name}:${spring.application.instance_id:${server.port}}}")
//    private String serviceInfo;



    @Autowired
    public UserController(UserService userService, RoleService roleService, DiscoveryClient discoveryClient) {

        this.userService = userService;
        this.roleService = roleService;
        this.discoveryClient = discoveryClient;
    }

    //@CrossOrigin(origins = "http://localhost:8080")
//    @RequestMapping("/login")
//    public ResponseEntity<Object> showLoginView(@RequestParam(value = "username", defaultValue = "please") String username,
//                                                @RequestParam(value = "password", defaultValue = "123456789") String password) {
//        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(username, password);
//
//
//
//        //Authentication auth = new AuthenticationManager()
////                authManager.authenticate(authReq);
////        SecurityContext sc = SecurityContextHolder.getContext();
////        securityContext.setAuthentication(auth);
//        System.out.println("ovdje");
//        return new ResponseEntity<>(username + " " + password, HttpStatus.OK);
//    }
//    // login authentification
//    @RequestMapping(value = "/default", method = RequestMethod.GET)
//    public ResponseEntity<Object> redirectToPanel() {
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//////        String name = authentication.getName();
//////        //System.out.println(name);
//////        if (authentication.getAuthorities().toString().contains("ROLE_ADMIN")) {
//////            return new ResponseEntity<>("Admin login successful", HttpStatus.OK);
//////            //return "redirect:/admin";
//////        }
//////        else if (authentication.getAuthorities().toString().contains("ROLE_USER")) {
//////            // authentication.getPrincipal().toString();
//////
//////            User user = userService.findByUsername(name);
//////
//////            if(user == null) {
//////                // can't log in
//////                return new ResponseEntity<>("Can't login!", HttpStatus.BAD_REQUEST);
//////            }
//////            return new ResponseEntity<>("User login successful", HttpStatus.OK);
//////        }
//////        else if (authentication.getAuthorities().toString().contains("ROLE_SUPERVISOR")) {
//////            //   System.out.println("super");
//////            return new ResponseEntity<>("Supervisor login successful", HttpStatus.OK);
//////        }
//////        return new ResponseEntity<>("Can't login!", HttpStatus.BAD_REQUEST);
//    }

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
            throw new UserException("Binding error! Add user failed!");
            //return new ResponseEntity<>("Binding error! Add user failed!", HttpStatus.BAD_REQUEST);
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

    @RequestMapping (value = "/reservations/{userID}", method = RequestMethod.GET)
    public ResponseEntity<?> getReservationsByUserId (@PathVariable Long userID) {
        try {
            ServiceInstance serviceInstanceReservations = discoveryClient.getInstances("reservations-service").get(0);
            String url = "http://" + serviceInstanceReservations.getServiceId() + "/reservations/user/" + userID;
            //String result = loadBalanced.getForObject(url, String.class);

            Reservation[] reservations = loadBalanced.getForObject(url, Reservation[].class);
            return new ResponseEntity<>(reservations, HttpStatus.FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping (value = "/show/hotels", method = RequestMethod.GET)
    public ResponseEntity<?> showHotelsList () throws ClientProtocolException, IOException {
        try {
            ServiceInstance serviceInstanceHotels = discoveryClient.getInstances("hotel-management-service").get(0);
            String url = "http://" + serviceInstanceHotels.getServiceId() + "/hotels";

            Hotel[] hotels = loadBalanced.getForObject(url, Hotel[].class);
            return new ResponseEntity<>(hotels, HttpStatus.FOUND);


        } catch (Exception exception) {
            return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
        }



    }


    @RequestMapping (value = "/search/hotels", method = RequestMethod.GET)
    public ResponseEntity<?> getHotelsList () throws ClientProtocolException, IOException {
        try {


            ServiceInstance serviceInstanceReservations = discoveryClient.getInstances("hotel-management-service").get(0);
            String url = "http://" + serviceInstanceReservations.getHost() +":" + serviceInstanceReservations.getPort() + "/hotels";
            System.out.println(url);


            Hotel[] hotels = loadBalanced.getForObject(url, Hotel[].class);
            return new ResponseEntity<>(hotels, HttpStatus.FOUND);

            //ResponseEntity<Hotel[]> response = loadBalanced.exchange(url, HttpMethod.GET, null, Hotel[].class );


        } catch (Exception exception) {
            return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
        }



    }

    @RequestMapping (value = "/search/hotels2", method = RequestMethod.GET)
    public ResponseEntity<?> getHotelsList2 () throws ClientProtocolException, IOException {
        try {

            Application application = eurekaClient.getApplication("hotel-management-service");
            InstanceInfo instanceInfo = application.getInstances().get(0);

            String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "hotels";
            System.out.println("URL" + url);
            Hotel[] hotels1 = loadBalanced.getForObject(url, Hotel[].class);
            System.out.println("RESPONSE " + hotels1);
            return new ResponseEntity<>(hotels1, HttpStatus.OK);

        } catch (Exception exception) {
            return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
        }
    }

    //  test
    @RequestMapping (value = "/reservations/hi", method = RequestMethod.GET)
    public ResponseEntity<?> sayHi () {
        ServiceInstance serviceInstanceReservations = discoveryClient.getInstances("reservations-service").get(0);


        System.out.println("port " + serviceInstanceReservations.getPort());
        System.out.println("port " + serviceInstanceReservations.getPort());
        System.out.println("id " + serviceInstanceReservations.getServiceId());
        System.out.println("mdata " + serviceInstanceReservations.getMetadata());
        System.out.println("URI " + serviceInstanceReservations.getUri());
        String url = "http://" + serviceInstanceReservations.getServiceId() + "/hi";
        String result = loadBalanced.getForObject(url, String.class);

        return new ResponseEntity<>(result, HttpStatus.FOUND);
    }
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
                System.out.println("Service is running!");
                return "Hello from User Management Service";
    }


}
