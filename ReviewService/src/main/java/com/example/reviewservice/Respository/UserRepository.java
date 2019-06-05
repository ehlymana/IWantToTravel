package com.example.reviewservice.Respository;

import com.example.reviewservice.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserID(Long id);

    User getOne(Long id);
}
