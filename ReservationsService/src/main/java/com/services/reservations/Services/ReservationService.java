package com.services.reservations.Services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.services.reservations.Model.Reservation;
import com.services.reservations.Model.User;
import com.services.reservations.Repository.ReservationRespository;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class ReservationService {

    private final RabbitTemplate rabbitTemplate;
    private final Exchange exchange;

    public ReservationService(RabbitTemplate rabbitTemplate, Exchange exchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }

    @Autowired
    private ReservationRespository reservationRepository;

    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
        String routingKey = "reservation.created";
        String message = "reservation created";
        rabbitTemplate.convertAndSend(exchange.getName(), routingKey, message);
    }

    public Reservation delete(Reservation reservation) {
        reservationRepository.delete(reservation);
        String routingKey = "reservation.deleted";
        String message = "reservation deleted";
        rabbitTemplate.convertAndSend(exchange.getName(), routingKey, message);
        return reservation;
    }

    public Reservation findById(Long id) {
        Reservation r = reservationRepository.findByReservationID(id);
        String routingKey = "reservation.found";
        String message = "reservation found";
        rabbitTemplate.convertAndSend(exchange.getName(), routingKey, message);
        return r;
    }

  //  public Optional<Reservation> findOne(Long id) { return reservationRespository.findById(id); }

    public Iterable<Reservation> findAll() {
        Iterable<Reservation> reservations = reservationRepository.findAll();
        String routingKey = "reservation.foundAll";
        String message = "reservation found all";
        rabbitTemplate.convertAndSend(exchange.getName(), routingKey, message);
        return reservations;
    }

    public Reservation getOne(Long id) {
        Reservation r = reservationRepository.getOne(id);
        String routingKey = "reservation.found";
        String message = "reservation found";
        rabbitTemplate.convertAndSend(exchange.getName(), routingKey, message);
        return r;
    }

    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
        String routingKey = "reservation.deleted";
        String message = "reservation deleted";
        rabbitTemplate.convertAndSend(exchange.getName(), routingKey, message);
    }
    // deletes the reservation after a message with a user is received
    // umjesto reservation queue ide ime queue-a od user service (zasad to ne radi)
    @RabbitListener(queues = "#{reservationQueue.name}")
    public void getUserMessage(String userMessage) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        TypeReference<User> mapType = new TypeReference<User>() {};

        User u = null;

        try {
            u = objectMapper.readValue(userMessage, mapType);
        }
        catch (IOException e) {
            System.out.println(String.valueOf(e));
        }
        Iterable<Reservation> allReservations = reservationRepository.findAll();
        for (Reservation r : allReservations) {
            if (r.getUser().getUserID() == u.getUserID()) {
                reservationRepository.delete(r);
            }
        }
    }
}
