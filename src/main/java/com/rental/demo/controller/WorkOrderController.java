package com.rental.demo.controller;

import com.alibaba.fastjson.JSON;
import com.rental.demo.entity.Complaints;
import com.rental.demo.entity.WorkOrder;
import com.rental.demo.repository.WorkOrderRepository;
import com.rental.demo.service.WorkOrderService;
import com.rental.demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class WorkOrderController {
    @Autowired
    private WorkOrderService workOrderService;
    @Autowired
    private WorkOrderRepository workOrderRepository;
    @CrossOrigin
    @RequestMapping(value = "/workorder/add",method = RequestMethod.POST)
    public int publish(@RequestBody Map<String,Object> map){
        String jsonString = JSON.toJSONString(map);
        WorkOrder workOrder = JSON.parseObject(jsonString, WorkOrder.class);
        workOrder.setStats(Constant.WOD_NOT_RES);
        workOrder.setCreatedTime(LocalDateTime.now());
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

    @RequestMapping(value = "/workorder/getbyTenant",method = RequestMethod.POST)
    public List<WorkOrder> getByTenant(@RequestParam(value = "name") String name){
        List<WorkOrder> list = workOrderRepository.findAllByTenantName(name);
        Collections.sort(list, new Comparator<WorkOrder>() {
            @Override
            public int compare(WorkOrder o1, WorkOrder o2) {
                if(o1.getCreatedTime().isAfter(o2.getCreatedTime()))
                    return -1;
                else
                    return 1;
            }
        });
        return list;
    }

    @RequestMapping(value = "/workorder/getfinish",method = RequestMethod.POST)
    public List<WorkOrder> getByStatus(@RequestParam(value = "name")String name){
        List<WorkOrder> list = workOrderRepository.findALLByTenantNameAndStats(name,Constant.WOD_FIS_RES);
        Collections.sort(list, new Comparator<WorkOrder>() {
            @Override
            public int compare(WorkOrder o1, WorkOrder o2) {
                if(o1.getCreatedTime().isAfter(o2.getCreatedTime()))
                    return -1;
                else
                    return 1;
            }
        });
        return list;
    }
}
