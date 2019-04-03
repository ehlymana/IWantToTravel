package com.example.reviewservice.Controller;

import com.example.reviewservice.Model.Review;
import com.example.reviewservice.Respository.ReviewRepository;
import com.example.reviewservice.Service.HotelService;
import com.example.reviewservice.Service.ReviewService;
import com.example.reviewservice.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

//@RestController
@Controller
public class ReviewController {

        private HotelService hotelService;
    private UserService userService;
    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService, UserService userService, HotelService hotelService) {
        this.hotelService = hotelService;
        this.userService = userService;
        this.reviewService = reviewService;
    }


//    private final ReviewRepository repository;
//
//    ReviewController(ReviewRepository repository) {
//        this.repository = repository;
//    }
//
//    @GetMapping("/reviews")
//    List<Review> all() {
//        return (List<Review>) repository.findAll();
//    }
//
//    @PostMapping("/reviews")
//    Review newReview(@RequestBody Review newReview) {
//        return repository.save(newReview);
//    }
//
//    @GetMapping("/reviews/{id}")
//    Optional<Review> one(@PathVariable Long id) {
//        return repository.findById(id);
//    }
//
//    @DeleteMapping("/reviews/{id}")
//    void deleteReview(@PathVariable Long id) {
//        repository.deleteById(id);
//    }
//
//    @PutMapping("reviews/{id}")
//    Review replaceReview(@RequestBody Review newReview, @PathVariable Long id) {
//        return repository.findById(id)
//                .map(review -> {
//                    review.setReviewComment(newReview.getReviewComment());
//                    review.setReviewDate(newReview.getReviewDate());
//                    return repository.save(newReview);
//                }).orElseGet(()-> {
//                    newReview.setReviewId(id);
//                    return repository.save(newReview);
//                });
//    }


    @RequestMapping(value = "/addNewReview", method = RequestMethod.GET)
    public String addReview() {
        Review review = new Review( "simply the best", new Date());
        reviewService.save(review);
        System.out.println("HERE");
        return "";
    }

}
