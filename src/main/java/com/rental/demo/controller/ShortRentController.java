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
    @RequestMapping(value = "/shortRentEnroll", method = RequestMethod.POST)
    @ResponseBody
    public int shortRentEnroll(@RequestBody Map<String, Object> map) {
        String jsonString = JSON.toJSONString(map);
        ShortRentOrder shortRentOrder = JSON.parseObject(jsonString, ShortRentOrder.class);
        return rentService.shortRentalEnroll(shortRentOrder);
    }

    //通用接口，前端自己调
    @PostMapping("/setShortRentState")
    @ResponseBody
    public void setShortRentState(@RequestBody JSONObject jsonObject) {
        Map<String, Object> map = (Map<String, Object>) jsonObject.get("shortRentOrder");
        String jsonString = JSON.toJSONString(map);
        ShortRentOrder shortRentOrder = JSON.parseObject(jsonString, ShortRentOrder.class);
        int state = (int) jsonObject.get("state");
        shortRentOrder.setState(state);
        shortRentOrderRepository.save(shortRentOrder);
    }

    //续租（客服）
    @PostMapping("/setShortRentRelet")
    @ResponseBody
    public void setShortRentRelet(@RequestBody JSONObject jsonObject) {
        Map<String, Object> map = (Map<String, Object>) jsonObject.get("shortRentOrder");
        String jsonString = JSON.toJSONString(map);
        ShortRentOrder shortRentOrder = JSON.parseObject(jsonString, ShortRentOrder.class);
        int state = (int) jsonObject.get("state");
        int days = Integer.parseInt((String) jsonObject.get("days"));
        LocalDate dueTime = LocalDate.parse(shortRentOrder.getLeaveDay(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        shortRentOrder.setLeaveDay(dueTime.plusDays(days).toString());
        shortRentOrder.setState(state);
        shortRentOrderRepository.save(shortRentOrder);
    }
    //续租（客户申请）
    @PostMapping("/applyShortRentRelet")
    @ResponseBody
    public void applyShortRentRelet(@RequestBody ShortRentOrder shortRentOrder) {
        shortRentOrder.setState(1827);
        shortRentOrderRepository.save(shortRentOrder);
    }
}
