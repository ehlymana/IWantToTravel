package com.baeldung.web.model.service;

import com.baeldung.web.model.User;
import com.baeldung.web.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }


    //public Users findOne(Long id) {
    //   return userRepository.findOne(id);
    // }


    public void save(User user) {
        userRepository.save(user);
    }


    public void delete(User user ) {
        userRepository.delete(user);
    }

    public User findById(Long id) {
        return userRepository.findByUserID(id);
    }

    public Optional<User> findOne(Long id) { return Optional.ofNullable(userRepository.findByUserID(id)); }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }



    public User getOne(Long id) {
        return userRepository.getOne(id);
    }

    public void deleteById(Long id) {
        userRepository.delete(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByPasswordToken(String token) {
        return userRepository.findByPasswordToken(token);
    }

    public User findByConfirmToken(String token) {
        return userRepository.findByConfirmToken(token);
    }

    public String getNewToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    //
//    public void updateUserTokens(String confirmToken, String passwordToken, String reactivateToken, Long id){
//        userRepository.updateUserTokens(confirmToken, passwordToken, reactivateToken, id);
//    }

/*
    public void userDeleted(Long id) {
        userRepository.userDeleted(id);
    }
*/



}