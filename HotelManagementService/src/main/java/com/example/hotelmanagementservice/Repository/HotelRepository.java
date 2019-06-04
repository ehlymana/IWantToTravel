package com.example.hotelmanagementservice.Repository;

import com.example.hotelmanagementservice.Model.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {
    Hotel findByHotelId(Long id);

    Hotel findByHotelName(String name);

    Hotel getOne(Long id);

    Iterable<Hotel> findAll();
}
