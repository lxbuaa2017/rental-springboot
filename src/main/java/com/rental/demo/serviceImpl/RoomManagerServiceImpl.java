package com.rental.demo.serviceImpl;

import com.rental.demo.entity.LandLord;
import com.rental.demo.entity.Room;
import com.rental.demo.repository.RoomRepository;
import com.rental.demo.service.RoomManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomManagerServiceImpl implements RoomManagerService {
    @Autowired
    private RoomRepository roomRepository;
    @Override
    public void addRoom(Room room) {
        roomRepository.save(room);
    }

    @Override
    public void removeRoom(Room room) {
        roomRepository.delete(room);
    }

    @Override
    public void updateRoom(Room room) {
        Room room1=roomRepository.findById(room.getRoomId()).get();
        room1=room;
    }

    @Override
    public List<Room> serchRoom(LandLord landLord) {
        return roomRepository.findByLandLord(landLord);
    }
}
