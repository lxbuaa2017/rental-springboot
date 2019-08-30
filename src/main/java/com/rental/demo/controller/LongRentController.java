package com.rental.demo.controller;

import com.alibaba.fastjson.JSON;
import com.rental.demo.entity.LongRentOrder;
import com.rental.demo.entity.ShortRentOrder;
import com.rental.demo.service.TenantService.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LongRentController {
    @Autowired
    private RentService rentService;
    //@CrossOrigin
    @RequestMapping(value = "/longRentEnroll",method= RequestMethod.POST)
    @ResponseBody
    public int longRentEnroll(@RequestBody Map<String,Object> map){
//        String jsonString= JSON.toJSONString(map); Map<String,Object> map
//        ShortRentOrder shortRentOrder=JSON.parseObject(jsonString,ShortRentOrder.class);
        int months=Integer.valueOf(map.get("months").toString());
        //LongRentOrder longRentOrder=(LongRentOrder) map.get("longRentOrder");
        String jsonString= JSON.toJSONString(map.get("longRentOrder"));
        LongRentOrder longRentOrder=JSON.parseObject(jsonString,LongRentOrder.class);
        System.out.println(longRentOrder);
        LocalDate checkDate=LocalDate.parse(longRentOrder.getCheckInDay());
        LocalDate leaveDate=checkDate.plusMonths(months);
        longRentOrder.setLeaveDay(leaveDate.toString());
        return rentService.longRentalEnroll(longRentOrder);
    }
}
