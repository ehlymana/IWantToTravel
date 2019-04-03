package com.example.reviewservice.Service;

import com.example.reviewservice.Model.Hotel;
import com.example.reviewservice.Respository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public Iterable<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    public Hotel save(Hotel hotel) {
        hotelRepository.save(hotel);
        return hotel;
    }


    public void delete(Hotel hotel) {
        hotelRepository.delete(hotel);
    }

    public Hotel findById(Long id) {
        return hotelRepository.findByHotelId(id);
    }


    public Hotel getOne(Long id) {
        return hotelRepository.getOne(id);
    }

    public void deleteById(Long id) {
        hotelRepository.deleteById(id);
    }
}
