package com.example.reviewservice.Service;

import com.example.reviewservice.Model.User;
import com.example.reviewservice.Respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User hotel) {
        userRepository.save(hotel);
        return hotel;
    }


    public void delete(User hotel) {
        userRepository.delete(hotel);
    }

    public User findById(Long id) {
        return userRepository.findByUserId(id);
    }


    public User getOne(Long id) {
        return userRepository.getOne(id);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
