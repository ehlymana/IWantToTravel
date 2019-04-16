package com.services.reservations.Repository;

import com.services.reservations.Model.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {

    Hotel findByHotelId(Long id);

    Hotel getOne(Long id);
}
