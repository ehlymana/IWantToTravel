package com.example.hotelmanagementservice.Repository;

import com.example.hotelmanagementservice.Model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
    Room findByRoomId(Long id);

    Room getOne(Long id);
}
