package com.rental.demo.controller;

import com.alibaba.fastjson.JSON;
import com.rental.demo.entity.Maintenanceman;
import com.rental.demo.service.RepairmanService.MaintenancemanService;
import com.rental.demo.service.RepairmanService.RepairmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/")
public class MaintenancemanController {
    @Autowired
    private MaintenancemanService maintenancemanService;
    @Autowired
    private RepairmanService repairmanService;
    @CrossOrigin
    @RequestMapping(value = "/maintenanceman",method = RequestMethod.POST)
    @ResponseBody
    public int post(@RequestBody Map<String,Object> map){
        String jsonString = JSON.toJSONString(map);
        Maintenanceman maintenanceman = JSON.parseObject(jsonString,Maintenanceman.class);
        return maintenancemanService.add(maintenanceman);
    }

    @RequestMapping(value = "/maintenanceman",method = RequestMethod.DELETE)
    @ResponseBody
    public int delete(@RequestParam(value = "username") String username){
        return repairmanService.delete(username);
    }

    @RequestMapping(value = "/maintenanceman/findById",method = RequestMethod.GET)
    @ResponseBody
    public Maintenanceman findById(@RequestParam(value = "username")String username)
    {
        return maintenancemanService.findById(username);
    }
}
