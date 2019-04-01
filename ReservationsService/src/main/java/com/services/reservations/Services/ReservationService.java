package com.services.reservations.Services;

import com.services.reservations.Model.Reservation;
import com.services.reservations.Repository.ReservationRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRespository reservationRepository;

    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public void delete(Reservation reservation ) {
        reservationRepository.delete(reservation);
    }

    public Reservation findById(Long id) {
        return reservationRepository.findByReservationID(id);
    }

  //  public Optional<Reservation> findOne(Long id) { return reservationRespository.findById(id); }

    public Iterable<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Reservation getOne(Long id) {
        return reservationRepository.getOne(id);
    }

    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }
}
