package com.baeldung.web.model.controller;

import com.baeldung.web.model.Role;
import com.baeldung.web.model.User;
import com.baeldung.web.model.service.EmailService;
import com.baeldung.web.model.service.RoleService;
import com.baeldung.web.model.service.UserService;

import com.netflix.discovery.EurekaClient;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class UserController {

    private UserService userService;
    private RoleService roleService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private EmailService emailService;

    @Autowired
    private EurekaClient eurekaClient;

    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @LoadBalanced
    private RestTemplate loadBalanced;

    @Autowired
    public UserController(UserService userService, RoleService roleService,
                          DiscoveryClient discoveryClient, BCryptPasswordEncoder bCryptPasswordEncoder,EmailService emailService) {

        this.userService = userService;
        this.roleService = roleService;
        this.discoveryClient = discoveryClient;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailService = emailService;
    }



    @PreAuthorize("#oauth2.hasScope('fooScope') and hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "adduser", method = RequestMethod.POST)
    @ResponseBody
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
            //throw new UserException("Binding error! Add user failed!");
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

    @RequestMapping(value = "/edit/user", method = RequestMethod.POST)
    public ResponseEntity<Object> editUser(@RequestParam(value = "userID") String userID, @RequestParam Map params) throws NotImplementedException {

           User user = userService.findOne(Long.parseLong(userID)).get();
           if(user != null) {
               user.setFirstName(params.get("firstName").toString());
               user.setLastName(params.get("lastName").toString());
               user.setEmail(params.get("email").toString());
               user.setUsername(params.get("username").toString());
               user.setPassword(bCryptPasswordEncoder.encode(params.get("password").toString()));
               user.setLongitude(Double.parseDouble(params.get("longitude").toString()));
               user.setLatitude(Double.parseDouble(params.get("latitude").toString()));
               Role role = roleService.findByRoleName(params.get("roleName").toString());
               user.setRole(roleService.findByRoleName(params.get("roleName").toString()));
               userService.save(user);
               return  new ResponseEntity<>("User updated!", HttpStatus.OK);
           } else {
               return new ResponseEntity<>("Error updating user!", HttpStatus.BAD_REQUEST);
           }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "addrole", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> addRole(@Valid Role role, BindingResult bindingResult)  {

        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>("Binding error! Add role failed!", HttpStatus.BAD_REQUEST);
        } else {
            roleService.save(role);
        }

        return new ResponseEntity<>("Role " + role.getRoleName() + " added!", HttpStatus.OK);
    }

    @RequestMapping(value = "/dashboard",method = RequestMethod.GET)
    public ResponseEntity<Object> redirectToAdminPanel(HttpServletResponse response, Principal principal) throws
            IOException {
        if(principal != null) {
            // response.sendRedirect("some-url");

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("http://localhost:8080/admin/dashboard"));
            return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);

        } else {
            return new ResponseEntity<>("Login required!", HttpStatus.BAD_REQUEST);
        }
        //If your method always returns a redirect, use ResponseEntity<Void>, otherwise whatever is returned normally as generic type.
    }

    @RequestMapping(value = "/getusers", method = RequestMethod.GET)
    public JSONObject getUsers() {
        JSONObject jsonObject = new JSONObject();
        Iterable<User> users = userService.findAll();
        if (users != null) {
            jsonObject.put("status", HttpStatus.OK);
            jsonObject.put("users", users);

        } else {
            jsonObject.put("status", HttpStatus.OK);
            jsonObject.put("users", null);
        }
        return jsonObject;
    }

    @RequestMapping(value = "/getuser/id", method = RequestMethod.GET)
    public JSONObject getUser(@RequestParam(value = "userID") String userID) {
        JSONObject jsonObject = new JSONObject();
        User user = userService.findById(Long.parseLong(userID));
        System.out.println(user.getUserID() + " " + user.getUsername());
        if (user != null) {
            jsonObject.put("status", HttpStatus.OK);
            jsonObject.put("user", user);
        } else {
            jsonObject.put("status", HttpStatus.BAD_REQUEST);
        }
        return jsonObject;
    }
    @RequestMapping(value = "/delete/user", method = RequestMethod.GET)
    public ResponseEntity<Object> deleteUser(@RequestParam(value = "userID") String userID) {
       User user = userService.findById(Long.parseLong(userID));
       if(user != null) {
           userService.delete(user);
           System.out.println("deleted");
           return new ResponseEntity<>("User deleted!", HttpStatus.OK);
       } else {
           return new ResponseEntity<>("Error deleting user!", HttpStatus.BAD_REQUEST);
       }
    }
}
