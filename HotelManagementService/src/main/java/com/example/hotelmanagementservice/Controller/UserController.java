package com.example.hotelmanagementservice.Controller;

import com.example.hotelmanagementservice.Model.User;
import com.example.hotelmanagementservice.Service.UserService;
import com.netflix.ribbon.proxy.annotation.Http;
import net.minidev.json.JSONObject;
import org.bouncycastle.util.Iterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/supervisors")
    @ResponseBody
    public Iterable<User> getAllHotels(){
        try {
            Iterable<User> all = (Iterable<User>) userService.findAll();
            return all;
        } catch(Exception e) {
            throw new HotelException("Supervisor not found!");
        }
    }

    @GetMapping(path = "/supervisors/{supervisorID}")
    @ResponseBody
    public User getSupervisor(@PathVariable("supervisorID") Long id){
        try{
            User u = userService.findById(id);
            return u;
        } catch (Exception e) {
            throw new HotelException("Could not get supervisor!");
        }
    }

    @DeleteMapping(path = "/supervisors/{supervisorID}")
    @ResponseBody
    public String deleteHotel(@PathVariable("supervisorID") Long id) {
        try{
            userService.deleteById(id);
        } catch (Exception e) {
            throw new HotelException("Supervisor not found!");
        }
        return "Successfully deleted supervisor!";
    }

    @PutMapping("/supervisors/{supervisorID}")
    User replaceHotel(@RequestBody User newUser, @PathVariable("supervisorID") Long id) {

        try {
            User old = userService.findById(id);

//            old.setHotelAddress(newHotel.getHotelAddress());
            old.setLatitude(newUser.getLatitude());
            old.setLongitude(newUser.getLongitude());

            return userService.save(old);
        } catch (Exception e) {
            throw new HotelException("Something went wrong while updating the supervisor! "+e.getMessage());
        }
    }


    @PostMapping("/supervisors")
    @ResponseBody
    User newHotel(@RequestBody User newUser) {
        JSONObject json = new JSONObject();
        try {
            return userService.save(newUser);
        } catch(Exception e) {
            json.put("status", HttpStatus.BAD_REQUEST);
            json.put("message", e.getMessage());
            throw new HotelException("Supervisor not added!");
        }
    }


}
