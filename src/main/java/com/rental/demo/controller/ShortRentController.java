package com.rental.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rental.demo.entity.LongRentOrder;
import com.rental.demo.entity.ShortRentOrder;
import com.rental.demo.repository.ShortRentOrderRepository;
import com.rental.demo.service.TenantService.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static com.rental.demo.util.Constant.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ShortRentController {
    @Autowired
    private RentService rentService;
    @Autowired
    private ShortRentOrderRepository shortRentOrderRepository;
    //@CrossOrigin
    @RequestMapping(value = "/shortRentEnroll",method= RequestMethod.POST)
    @ResponseBody
    public int shortRentEnroll(@RequestBody Map<String,Object> map){
       String jsonString= JSON.toJSONString(map);
       ShortRentOrder shortRentOrder=JSON.parseObject(jsonString,ShortRentOrder.class);
        return rentService.shortRentalEnroll(shortRentOrder);
    }
    //通用接口，前端自己调
    @PostMapping("/setShortRentState")
    @ResponseBody
    public void setShortRentState(@RequestBody JSONObject jsonObject){
        ShortRentOrder shortRentOrder=(ShortRentOrder) jsonObject.get("shortRentOrder");
        int state=Integer.valueOf((String)jsonObject.get("state"));
        shortRentOrder.setState(state);
        shortRentOrderRepository.save(shortRentOrder);
    }
    //续租
    @PostMapping("/setShortRentRelet")
    @ResponseBody
    public void setShortRentRelet(@RequestBody JSONObject jsonObject){
        ShortRentOrder shortRentOrder=(ShortRentOrder) jsonObject.get("shortRentOrder");
        int state=Integer.valueOf((String)jsonObject.get("state"));
        int days=Integer.valueOf((String)jsonObject.get("days"));
        shortRentOrder.setState(state);
        if(state==INN){
            LocalDateTime dueTime=LocalDateTime.parse(shortRentOrder.getLeaveDay(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            shortRentOrder.setLeaveDay(dueTime.plusDays(days).toString());
        }
        shortRentOrderRepository.save(shortRentOrder);
    }
}
