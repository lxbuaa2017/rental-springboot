package com.rental.demo.service;

import com.rental.demo.entity.LandLord;
import com.rental.demo.entity.Room;

import java.util.List;

//商家对房间的管理
public interface RoomManagerService {
  public void addRoom(Room room);
  public void removeRoom(Room room);
  public void updateRoom(Room room);
  public List<Room> serchRoom(LandLord landLord);
}
