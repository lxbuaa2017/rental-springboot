package com.rental.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rental.demo.entity.LongRentOrder;
import com.rental.demo.entity.ShortRentOrder;
import com.rental.demo.repository.LongRentOrderRepository;
import com.rental.demo.service.TenantService.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static com.rental.demo.util.Constant.*;

@RestController
@RequestMapping("/api")
public class LongRentController {
    @Autowired
    private RentService rentService;
    @Autowired
    private LongRentOrderRepository longRentOrderRepository;

    //@CrossOrigin
    @RequestMapping(value = "/longRentEnroll", method = RequestMethod.POST)
    @ResponseBody
    public int longRentEnroll(@RequestBody Map<String, Object> map) {
//        String jsonString= JSON.toJSONString(map); Map<String,Object> map
//        ShortRentOrder shortRentOrder=JSON.parseObject(jsonString,ShortRentOrder.class);
        int months = Integer.valueOf(map.get("months").toString());
        //LongRentOrder longRentOrder=(LongRentOrder) map.get("longRentOrder");
        String jsonString = JSON.toJSONString(map.get("longRentOrder"));
        LongRentOrder longRentOrder = JSON.parseObject(jsonString, LongRentOrder.class);
        System.out.println(longRentOrder);
        LocalDate checkDate = LocalDate.parse(longRentOrder.getCheckInDay());
        LocalDate leaveDate = checkDate.plusMonths(months);
        longRentOrder.setLeaveDay(leaveDate.toString());
        return rentService.longRentalEnroll(longRentOrder);
    }

    //对 待审核订单 进行审核
    //通过，则修改为待付款订单（即待签合同）
    @PostMapping("/longRentPass")
    @ResponseBody
    public void longRentPass(@RequestBody LongRentOrder longRentOrder) {
        longRentOrder.setState(WAIT_PAY);
        longRentOrderRepository.save(longRentOrder);
    }

    //对 待审核订单 进行审核
    //不通过，则直接删除订单
    @PostMapping("/longRentNotPass")
    @ResponseBody
    public void longRentNotPass(@RequestBody LongRentOrder longRentOrder) {
        longRentOrderRepository.delete(longRentOrder);
    }

    //对 款项待确认订单（即合同待签） 进行审核
    //通过，则修改为在住
    @PostMapping("/longRentPayPass")
    @ResponseBody
    public void longRentPayPass(@RequestBody LongRentOrder longRentOrder) {
        longRentOrder.setState(INN);
        longRentOrderRepository.save(longRentOrder);
    }

    //对 款项待确认订单（即合同待签） 进行审核
    //不通过，则修改为待付款
    @PostMapping("/longRentPayNotPass")
    @ResponseBody
    public void longRentPayNotPass(@RequestBody LongRentOrder longRentOrder) {
        longRentOrder.setState(WAIT_PAY);
        longRentOrderRepository.save(longRentOrder);
    }

    //对 续租待确认订单 进行审核
    //通过，则修改为在住，延长相应租期
    @PostMapping("/longRentReletPass")
    @ResponseBody
    public void longRentReletPass(@RequestBody JSONObject jsonObject) {
        LongRentOrder longRentOrder = (LongRentOrder) jsonObject.get("longRentOrder");
        int months = Integer.valueOf((String) jsonObject.get("months"));
        longRentOrder.setState(INN);
        LocalDateTime dueTime = LocalDateTime.parse(longRentOrder.getLeaveDay(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        longRentOrder.setLeaveDay(dueTime.plusMonths(months).toString());
        longRentOrderRepository.save(longRentOrder);
    }

    //对 续租待确认订单 进行审核
    //不通过，则修改为待付款
    @PostMapping("/longRentReletNotPass")
    @ResponseBody
    public void longRentReletNotPass(@RequestBody LongRentOrder longRentOrder) {
        longRentOrder.setState(WAIT_PAY);
        longRentOrderRepository.save(longRentOrder);
    }

    //通用接口，前端自己调
    @PostMapping("/setLongRentState")
    @ResponseBody
    public void setLongRentState(@RequestBody JSONObject jsonObject) {
        Map<String, Object> map = (Map<String, Object>) jsonObject.get("longRentOrder");
        String jsonString = JSON.toJSONString(map);
        LongRentOrder longRentOrder = JSON.parseObject(jsonString, LongRentOrder.class);
        int state = (int) jsonObject.get("state");
        longRentOrder.setState(state);
        longRentOrderRepository.save(longRentOrder);
    }

    @GetMapping("/getLongRentOrderById")
    @ResponseBody
    public LongRentOrder getLongRentOrderById(@RequestParam String id) {
        return longRentOrderRepository.findById(id).get();
    }

    //续租
    @PostMapping("/setLongRentRelet")
    @ResponseBody
    public void setLongRentRelet(@RequestBody JSONObject jsonObject) {
        Map<String, Object> map = (Map<String, Object>) jsonObject.get("longRentOrder");
        String jsonString = JSON.toJSONString(map);
        LongRentOrder longRentOrder = JSON.parseObject(jsonString, LongRentOrder.class);
        int state = (int) jsonObject.get("state");
        int months = Integer.parseInt((String) jsonObject.get("months"));
        LocalDate dueTime = LocalDate.parse(longRentOrder.getLeaveDay(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        longRentOrder.setLeaveDay(dueTime.plusMonths(months).toString());
        longRentOrder.setState(state);
        longRentOrderRepository.save(longRentOrder);
    }

}
