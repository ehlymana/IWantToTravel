package com.services.reservations.Repository;

import com.services.reservations.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUserID(Long id);

    User getOne(Long id);
}
