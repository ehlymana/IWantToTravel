package com.example.hotelmanagementservice.Repository;


import com.example.hotelmanagementservice.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserID(Long id);

    //User findByUserName(String name);

    User getOne(Long id);
}
