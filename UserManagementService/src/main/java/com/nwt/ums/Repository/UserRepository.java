package com.nwt.ums.Repository;

import com.nwt.ums.Model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUserID(Long id);

    User findByUsername(String username);

    User findByEmail(String email);

    User findByConfirmToken(String token);

    User findByPasswordToken(String token);

    User getOne(Long id);


}
