package com.example.hotelmanagementservice.Controller;

import com.example.hotelmanagementservice.Model.Hotel;
import com.example.hotelmanagementservice.Service.HotelService;
import com.netflix.ribbon.proxy.annotation.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class HotelController {

    private HotelService hotelService;
    //private UserService supervizorService;

    @Autowired
    public HotelController(HotelService hotelService/*, SupervizorService supervizorService*/) {
        this.hotelService = hotelService;
        //this.supervizorService = supervizorService;
    }


//    @RequestMapping(value = "/addHotel", method = RequestMethod.GET)
//    public String addHotel() throws Exception {
//        System.out.println("HOTELLLL");
//        try {
//            Hotel hotel = new Hotel(1, "Hollywood", "neki desc", "Sarajevo", "Adresa", 45, 47);
//            hotelService.save(hotel);
//        } catch (Exception e) {
//            throw new Exception("Something went wrong while adding hotel...");
//        }
//        System.out.println("gucci");
//        return "All good";
//    }

    @GetMapping(path = "/hotels")
    @ResponseBody
    public Iterable<Hotel> getAllHotels(){
        try {
            Iterable<Hotel> all = hotelService.findAll();
            return all;
        } catch(Exception e) {
            throw new HotelException("Hotels not found!");
        }
    }

    @GetMapping(path = "/hotels/{hotelID}")
    @ResponseBody
    public Hotel getHotel(@PathVariable("hotelID") Long id){
        try{
            Hotel h = hotelService.findById(id);
            return h;
        } catch (Exception e) {
            throw new HotelException("Could not get hotel!");
        }
    }

    @PostMapping("/hotels")
    @ResponseBody
    Hotel newHotel(@RequestBody Hotel newHotel) {
        try {
            return hotelService.save(newHotel);
        } catch(Exception e) {
            throw new HotelException("Something went wrong, hotel was not created!");
        }
    }

    @DeleteMapping(path = "/hotels/{hotelID}")
    @ResponseBody
    public String deleteHotel(@PathVariable("hotelID") Long id) {
        try{
            hotelService.deleteById(id);
        } catch (Exception e) {
            throw new HotelException("Hotel not found!");
        }
        return "Successfully deleted";
    }

    @PutMapping("/hotels/{hotelID}")
    Hotel replaceHotel(@RequestBody Hotel newHotel, @PathVariable("hotelID") Long id) {

        try {
            Hotel old = hotelService.findById(id);

            old.setHotelAddress(newHotel.getHotelAddress());
            old.setHotelDescription(newHotel.getHotelDescription());
            old.setHotelLatitude(newHotel.getHotelLatitude());
            old.setHotelLongitude(newHotel.getHotelLongitude());
            old.setHotelLocation(newHotel.getHotelLocation());
            old.setHotelName(newHotel.getHotelName());

            return hotelService.save(old);
        } catch (Exception e) {
            throw new HotelException("Something went wrong while updating the hotel! "+e.getMessage());
        }
    }
}
