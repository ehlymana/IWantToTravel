package com.services.reservations.Repository;

import com.services.reservations.Model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRespository extends CrudRepository<Reservation, Long> {

    Reservation findByReservationID(Long id);

    Reservation getOne(Long id);
}