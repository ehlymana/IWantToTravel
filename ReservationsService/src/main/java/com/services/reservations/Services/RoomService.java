package com.services.reservations.Services;

import com.services.reservations.Model.Room;
import com.services.reservations.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Iterable<Room> findAll() {
        return roomRepository.findAll();
    }

    public void save(Room room) {
        roomRepository.save(room);
    }

    public void delete(Room room) {
        roomRepository.delete(room);
    }

    public Room findById(Long id) {
        return roomRepository.findByRoomId(id);
    }

    public Room getOne(Long id) {
        return roomRepository.getOne(id);
    }

    public void deleteById(Long id) {
        roomRepository.deleteById(id);
    }
}