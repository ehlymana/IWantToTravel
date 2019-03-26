package com.example.reviewservice.Controller;

import com.example.reviewservice.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReviewController {

    //    private HotelService hotelService;
//    private UserService userService;
    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService/*, UserService userService, HotelService hotelService*/) {
//        this.hotelService = hotelService;
//        this.userService = userService;
        this.reviewService = reviewService;
    }


    @RequestMapping(value = "/addReview", method = RequestMethod.GET)
    public String addReview() {

        return "";
    }

}
