package com.services.reservations.Services;

import com.services.reservations.Model.User;
import com.services.reservations.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
