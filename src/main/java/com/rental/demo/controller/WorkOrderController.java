package com.rental.demo.controller;

import com.alibaba.fastjson.JSON;
import com.rental.demo.entity.Complaints;
import com.rental.demo.entity.WorkOrder;
import com.rental.demo.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
public class WorkOrderController {
    @Autowired
    private WorkOrderService workOrderService;
    @CrossOrigin(origins = "http://114.115.160.38:8080", allowCredentials = "true")
    @RequestMapping(value = "/workorder/add",method = RequestMethod.POST)
    public int publish(@RequestBody Map<String,Object> map){
        String jsonString = JSON.toJSONString(map);
        WorkOrder workOrder = JSON.parseObject(jsonString, WorkOrder.class);
        return workOrderService.addWorkOrder(workOrder);
    }

    @RequestMapping(value = "/workorder/distribution",method = RequestMethod.POST)
    public int distribution(@RequestParam(value = "id") String id,@RequestParam(value = "name") String name){
        return workOrderService.distribution(id,name);
    }

    @RequestMapping(value = "/workorder/finish",method = RequestMethod.POST)
    public int finish(@RequestParam(value = "id") String id){
        return workOrderService.finish(id);
    }

    @RequestMapping(value = "/workorder/evaluate",method = RequestMethod.POST)
    public int evaluate(@RequestParam(value = "id") String id,@RequestParam(value = "score") int score){
        return workOrderService.evaluate(id, score);
    }
}
