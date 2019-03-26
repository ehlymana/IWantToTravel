package com.example.hotelmanagementservice.Service;

import com.example.hotelmanagementservice.Model.Room;
import com.example.hotelmanagementservice.Repository.RoomRepository;
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
