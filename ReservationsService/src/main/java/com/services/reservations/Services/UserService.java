package com.services.reservations.Services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.services.reservations.Model.Reservation;
import com.services.reservations.Model.User;
import com.services.reservations.Repository.UserRepository;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void delete(User user ) {
        userRepository.delete(user);
    }

    public User findById(Long id) {
        return userRepository.findByUserID(id);
    }

    public User getOne(Long id) {
        return userRepository.getOne(id);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    // when a user from User Management Service is deleted, it is deleted in this service too
    // in order for this to work, multiple projects must be started at the same time - idk how that will work
    /*@RabbitListener(queues="userQueue")
    public void receive(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        TypeReference<User> mapType = new TypeReference<User>() {};

        User u = null;

        try {
            u = objectMapper.readValue(message, mapType);
        }
        catch (IOException e) {
            System.out.println(String.valueOf(e));
        }
        userRepository.delete(u);
    }*/
}
