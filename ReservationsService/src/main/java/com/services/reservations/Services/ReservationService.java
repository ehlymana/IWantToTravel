package com.services.reservations.Services;

import com.services.reservations.Model.Reservation;
import com.services.reservations.Repository.ReservationRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRespository reservationRespository;

    public void save(Reservation reservation) {
        reservationRespository.save(reservation);
    }

    public void delete(Reservation reservation ) {
        reservationRespository.delete(reservation);
    }

    public Reservation findById(Long id) {
        return reservationRespository.findByReservationID(id);
    }

  //  public Optional<Reservation> findOne(Long id) { return reservationRespository.findById(id); }


    public Reservation getOne(Long id) {
        return reservationRespository.getOne(id);
    }

    public void deleteById(Long id) {
        reservationRespository.deleteById(id);
    }
}
