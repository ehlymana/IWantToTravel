package com.baeldung.web.model.repository;

import com.baeldung.web.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUserID(Long id);

    User findByUsername(String username);

    User findByEmail(String email);

    User findByConfirmToken(String token);

    User findByPasswordToken(String token);

    User getOne(Long id);


//    @Transactional
//    @Modifying
//    @Query(value = "update User u set u.confirmToken =: confirmToken, u.passwordToken =: passwordToken, u.reactivateToken =: reactivateToken where u.userID =: id")
//    void updateUserTokens(@Param (value = "confirmToken") String confirmToken,
//                          @Param (value = "passwordToken") String passwordToken,
//                          @Param (value = "reactivateToken") String reactivateToken,
//                          @Param (value = "id") Long id);


}