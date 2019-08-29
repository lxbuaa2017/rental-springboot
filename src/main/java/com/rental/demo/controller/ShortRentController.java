package com.rental.demo.controller;

import com.alibaba.fastjson.JSON;
import com.rental.demo.entity.LongRentOrder;
import com.rental.demo.entity.ShortRentOrder;
import com.rental.demo.service.TenantService.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/")
public class ShortRentController {
    @Autowired
    private RentService rentService;
    @CrossOrigin
    @RequestMapping(value = "/shortRentEnroll",method= RequestMethod.POST)
    @ResponseBody
    public int shortRentEnroll(@RequestBody Map<String,Object> map){
       String jsonString= JSON.toJSONString(map);
       ShortRentOrder shortRentOrder=JSON.parseObject(jsonString,ShortRentOrder.class);
        return rentService.shortRentalEnroll(shortRentOrder);
    }
}
