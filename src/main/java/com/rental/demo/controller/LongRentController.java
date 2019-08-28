package com.rental.demo.controller;

import com.alibaba.fastjson.JSON;
import com.rental.demo.entity.LongRentOrder;
import com.rental.demo.service.TenantService.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
public class LongRentController {
    @Autowired
    private RentService rentService;
    @CrossOrigin
    @RequestMapping(value = "/longRentEnroll",method= RequestMethod.POST)
    @ResponseBody
    public int longRentEnroll(@RequestBody Map<String,Object> map) {
        String jsonString = JSON.toJSONString(map);
        LongRentOrder longRentOrder = JSON.parseObject(jsonString, LongRentOrder.class);
        return rentService.longRental(longRentOrder);
    }
}
