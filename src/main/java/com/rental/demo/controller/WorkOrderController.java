package com.rental.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

import static com.rental.demo.util.Constant.WOD_NOT_RES;

@RestController
@RequestMapping("/api")
public class WorkOrderController {
    @Autowired
    private WorkOrderService workOrderService;
    @Autowired
    private WorkOrderRepository workOrderRepository;
    @CrossOrigin
    @RequestMapping(value = "/workorder/add",method = RequestMethod.POST)
    @ResponseBody
    public int publish(@RequestBody Map<String,Object> map){
        String jsonString = JSON.toJSONString(map);
        WorkOrder workOrder = JSON.parseObject(jsonString, WorkOrder.class);
        workOrder.setStats(WOD_NOT_RES);
        workOrder.setCreatedTime(LocalDateTime.now());
        return workOrderService.addWorkOrder(workOrder);
    }

    @RequestMapping(value = "/workorder/distribution",method = RequestMethod.POST)
    @ResponseBody
    public int distribution(@RequestParam(value = "id") String id,@RequestParam(value = "name") String name){
        return workOrderService.distribution(id,name);
    }

    @RequestMapping(value = "/workorder/finish",method = RequestMethod.POST)
    @ResponseBody
    public int finish(@RequestParam(value = "id") String id){
        return workOrderService.finish(id);
    }

    @RequestMapping(value = "/workorder/evaluate",method = RequestMethod.POST)
    @ResponseBody
    public int evaluate(@RequestParam(value = "username") String id,@RequestParam(value = "score") int score){
        return workOrderService.evaluate(id, score);
    }

    @RequestMapping(value = "/workorder/getbyTenant",method = RequestMethod.POST)
    @ResponseBody
    public List<WorkOrder> getByTenant(@RequestParam String username){
        List<WorkOrder> list = workOrderRepository.findALLByTenantNameAndStats(username,6001);
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
    @ResponseBody
    public List<WorkOrder> getByStatus(@RequestParam(value = "username")String name){
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

    @RequestMapping(value = "/workorder/getAllOrder",method = RequestMethod.GET)
    @ResponseBody
    public List<WorkOrder> getAllOrder(){
        return workOrderRepository.findAllByStats(WOD_NOT_RES);
    }

    @RequestMapping(value = "/workorder/update",method = RequestMethod.POST)
    @ResponseBody
    public void getAllOrder(JSONObject jsonObject){
        String str=JSON.toJSONString(jsonObject);
        WorkOrder workOrder=(WorkOrder) JSONObject.parse(str);
        workOrderRepository.save(workOrder);
    }
}
