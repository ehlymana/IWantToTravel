package com.example.reviewservice.Controller;

import com.example.reviewservice.Model.User;
import com.example.reviewservice.Respository.UserRepository;
import com.example.reviewservice.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/addNewUser", method = RequestMethod.GET)
    public String addUser() {
        User user = new User( "newUser123");
        userService.save(user);
        System.out.println("HERE");
        return "";
    }

//    private final UserRepository repository;
//
//    UserController(UserRepository repository) {
//        this.repository = repository;
//    }
//
//    @GetMapping("/users")
//    List<User> all() {
//        return (List<User>) repository.findAll();
//    }
//
//    @PostMapping("/users")
//    User newReview(@RequestBody User newReview) {
//        return repository.save(newReview);
//    }
//
//    @GetMapping("/users/{id}")
//    Optional<User> one(@PathVariable Long id) {
//        return repository.findById(id);
//    }
//
//    @DeleteMapping("/users/{id}")
//    void deleteReview(@PathVariable Long id) {
//        repository.deleteById(id);
//    }
//
//    @PutMapping("users/{id}")
//    User replaceReview(@RequestBody User newReview, @PathVariable Long id) {
//        return repository.findById(id)
//                .map(review -> {
//                    review.setUsername(newReview.getUsername());
//                    return repository.save(newReview);
//                }).orElseGet(()-> {
//                    newReview.setUserID(id);
//                    return repository.save(newReview);
//                });
//    }
}
