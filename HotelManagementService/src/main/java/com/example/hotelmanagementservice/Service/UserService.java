package com.example.hotelmanagementservice.Service;

import com.example.hotelmanagementservice.Model.User;
import com.example.hotelmanagementservice.Repository.UserRepository;
import org.bouncycastle.util.Iterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> findAll() {
        return (Iterable<User>) userRepository.findAll();
    }

    public User save(User user) {
        userRepository.save(user);
        return user;
    }


    public void delete(User user) {
        userRepository.delete(user);
    }

    public User findById(Long id) {
        return userRepository.findByUserID(id);
    }

//    public User findByName(String name) {
//        return userRepository.findByUserName(name);
//    }


    public User getOne(Long id) {
        return userRepository.getOne(id);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
