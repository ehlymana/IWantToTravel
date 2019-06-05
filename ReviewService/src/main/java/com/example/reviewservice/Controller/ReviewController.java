package com.example.reviewservice.Controller;

import com.example.reviewservice.Model.Hotel;
import com.example.reviewservice.Model.Review;
import com.example.reviewservice.Respository.ReviewRepository;
import com.example.reviewservice.Service.HotelService;
import com.example.reviewservice.Service.ReviewService;
import com.example.reviewservice.Service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@RestController
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
        Review review = new Review("simply the best", new Date(), null);
        reviewService.save(review);
        System.out.println("HERE");
        return "";
    }
    @RequestMapping(value = "/addReview", method = RequestMethod.POST, produces = "application/json")
    public JSONObject addReview(@RequestParam(value="hotelID") long hotelID, @RequestParam(value="reviewText") String reviewText) throws Exception {
        JSONObject json = new JSONObject();
        try {
            Hotel h = hotelService.findById(hotelID);
            if (h == null) {
                h = new Hotel("Europa");
                hotelService.save(h);
            }
			Review r = new Review(reviewText, new Date(), h);
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Review>> violations = validator.validate(r);
            if (violations.size() > 0) {
                for (ConstraintViolation<Review> violation : violations) {
                    System.out.println("**** ERROR: " + violation.getMessage() + " ****");
                    json.put("status", HttpStatus.BAD_REQUEST);
                    json.put("message", violation.getMessage());
                }
            } else {
                reviewService.save(r);
                System.out.println("**** Review successfully added! ****");
                json.put("status", HttpStatus.OK);
                json.put("review", r);
            }
        }
        catch (Exception e) {
            json.put("status", HttpStatus.BAD_REQUEST);
            json.put("message", e.getMessage());
        }
        return json;
    }
}
