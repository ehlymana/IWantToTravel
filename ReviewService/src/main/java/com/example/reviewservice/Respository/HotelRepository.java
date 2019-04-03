package com.example.reviewservice.Respository;

import com.example.reviewservice.Model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {
    Hotel findByHotelId(Long id);

    Hotel getOne(Long id);
}
