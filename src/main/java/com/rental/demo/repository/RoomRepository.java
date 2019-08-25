package com.rental.demo.repository;

import com.rental.demo.entity.LandLord;
import com.rental.demo.entity.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends MongoRepository<Room,String> {
    public List<Room> findByLandLord(LandLord landLord);
    public Room findByAddress(String address);
    public List<Room> findByRentType(int rentType);
}
