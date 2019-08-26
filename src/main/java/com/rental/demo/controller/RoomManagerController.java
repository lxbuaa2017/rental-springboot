package com.rental.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.rental.demo.entity.Room;
import com.rental.demo.repository.RoomRepository;
import com.rental.demo.service.RoomManagerService;
import com.rental.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.rental.demo.util.Constant.*;

@RestController
@RequestMapping("/room")
public class RoomManagerController {
    @Autowired
    private RoomManagerService roomManagerService;
    @Autowired
    private RoomRepository roomRepository;
    @CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String addRoom(@RequestBody Room room){
        String address=room.getAddress();
        if(roomRepository.findByAddress(address)!=null)
            return JsonResult.build(ROOM_AREADY_EXIST,"该地址的房源已录入过",null);
        else {
            roomManagerService.addRoom(room);
            return JsonResult.build(SUCCESS,"房源成功录入",null);
        }
    }
    @CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    @ResponseBody
    public List<Room> getRoom(HttpSession httpSession){
        return roomRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
    @RequestMapping(value = "/findByRentType",method = RequestMethod.POST)
    @ResponseBody
    public List<Room> getRoomByRentType(@RequestBody JSONObject jsonObject, HttpSession httpSession){
        int rentType=jsonObject.getIntValue("rentType");
        List<Room> rooms=new ArrayList<>();
        List<Room> rooms1= roomRepository.findByRentTypeAndState(rentType,FREE);
        List<Room> rooms2= roomRepository.findByRentTypeAndState(2006,FREE);
        rooms.addAll(rooms1);
        rooms.addAll(rooms2);
        return rooms;
    }

    @CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
    @RequestMapping(value = "/findByAddress",method = RequestMethod.GET)
    @ResponseBody
    public Room getRoomByAddress(@RequestParam String address){
        return roomRepository.findByAddress(address);
    }

    @CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
    @RequestMapping(value = "/findById",method = RequestMethod.GET)
    @ResponseBody
    public Room getRoomById(@RequestParam String id){
        return roomRepository.findById(id).get();
    }
}
