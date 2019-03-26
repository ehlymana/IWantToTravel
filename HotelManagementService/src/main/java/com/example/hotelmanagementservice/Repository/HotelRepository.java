package com.example.hotelmanagementservice.Repository;

import com.example.hotelmanagementservice.Model.Hotel;
import org.springframework.data.repository.CrudRepository;

public interface HotelRepository extends CrudRepository<Hotel, Long> {
    Hotel findByHotelId(Long id);

    Hotel findByHotelName(String name);

    Hotel getOne(Long id);
}
