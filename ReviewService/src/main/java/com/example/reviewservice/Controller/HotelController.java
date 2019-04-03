package com.example.reviewservice.Controller;

import com.example.reviewservice.Model.Hotel;
import com.example.reviewservice.Respository.HotelRepository;
import com.example.reviewservice.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@RestController
@Controller
public class HotelController {

    private HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @RequestMapping(value = "/addNewHotel", method = RequestMethod.GET)
    public String addHotel() {
        Hotel hotel = new Hotel( "simply the best");
        hotelService.save(hotel);
        System.out.println("HERE");
        return "";
    }

//    private final HotelRepository repository;
//
//    HotelController(HotelRepository repository) {
//        this.repository = repository;
//    }
//
//    // Aggregate root
//
//    @GetMapping("/hotels")
//    List<Hotel> all() {
//        return repository.findAll();
//    }
//
//    @PostMapping("/hotels")
//    Hotel newHotel(@RequestBody Hotel newHotel) {
//        return repository.save(newHotel);
//    }
//
//    // Single item
//
//    @GetMapping("/hotels/{id}")
//    Optional<Hotel> one(@PathVariable Long id) {
//
//        return repository.findById(id);
//    }
//
//    @PutMapping("/hotels/{id}")
//    Hotel replaceHotel(@RequestBody Hotel newHotel, @PathVariable Long id) {
//
//        return repository.findById(id)
//                .map(employee -> {
//                    employee.setHotelName(newHotel.getHotelName());
//                    return repository.save(employee);
//                })
//                .orElseGet(() -> {
//                    newHotel.setHotelId(id);
//                    return repository.save(newHotel);
//                });
//    }
//
//    @DeleteMapping("/hotels/{id}")
//    void deleteHotel(@PathVariable Long id) {
//        repository.deleteById(id);
//    }

}
