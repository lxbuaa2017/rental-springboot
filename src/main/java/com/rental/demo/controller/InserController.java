package com.rental.demo.controller;

import com.rental.demo.entity.File.PdfFile;
import com.rental.demo.entity.LandLord;
import com.rental.demo.entity.Room;
import com.rental.demo.entity.Tenant;
import com.rental.demo.repository.PdfFileRepository;
import com.rental.demo.repository.RoomRepository;
import com.rental.demo.service.PdfContractService;
import com.rental.demo.service.RoomManagerService;
import com.rental.demo.serviceImpl.RandomLocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/")
public class InsertController {
    @Autowired
    private RoomManagerService roomManagerService;
    @Autowired
    private RoomRepository roomRepository;
    RandomLocalDate randomLocalDate = new RandomLocalDate();
    @RequestMapping(value = "/insrtRoom",method = RequestMethod.GET)
    @ResponseBody
    public void insertRoom(){
        LandLord lan = new LandLord("firstman","123456","132123456789","13212345678@163.com",true,45);
        LandLord lee = new LandLord("secondman","123456","18612345678","18612345678@sina.com",false,26);
        LandLord zhou = new LandLord("thirdman","123456","18812345678","18812345678@126.com",true,35);
        Tenant jack = new Tenant("firstte","123456","16612345678","16612345678@qq.com",true,23);
        Tenant david = new Tenant("secondte","123456","18312345678","18312345678@163.com",false,30);
        Tenant rose = new Tenant("thirdte","123456","13512345678","13512345678@126.com",false,28);
        LandLord[] landLords = {lan,lee,zhou};
        Tenant[] tenants = {jack,david,rose};
        String[] type = {"单人间","双人间","四人间"};
        String[] city = {"北京","石家庄","济南","杭州","上海"};
        String[] Community = {"文庭雅苑","美景天城","田园牧歌","水木春城","中景濠庭"};
        String[] Orientation = {"东","西"};
        for(int i = 0;i<100;i++){
            Room room = new Room();
            room.setRoomId(String.valueOf(i));
            int random = (int)(Math.random()*3);
            room.setType(type[random]);
            int cityr = (int)(Math.random()*5);
            int communityr = (int)(Math.random()*5);
            room.setAddress(city[cityr]+"市"+Community[communityr]+"小区"
                    + (int)(Math.random() * 15 + 1) +"栋"
                    + (int)(Math.random() * 10 + 1) +"楼"
                    +Orientation[(int)(Math.random()*2)]+"户");
            room.setPriceForDay((int)(Math.random()*900+100));
            room.setPriceForMonth((int)(Math.random()*49000+1000));
            room.setArea(String.valueOf((int)(Math.random()*450+50)));
            room.setLandLord(landLords[(int)(Math.random()*3)]);
            room.setRenter(tenants[(int)(Math.random()*3)]);
            room.setRentTime(randomLocalDate.randomLocalDateTime(-100,0));
            room.setDueTime(randomLocalDate.randomLocalDateTime(0,100));
            room.setState(2002);
            room.setRentType(2006);
            if(roomRepository.findByAddress(room.getAddress())!=null);
            else
                roomManagerService.addRoom(room);
        }
    }

    @RequestMapping(value = "/insertTenant", method = RequestMethod.GET)
    @ResponseBody
    public void insertTenant() {
        for (int i = 0; i < 50; i++) {

        }
    }
}
