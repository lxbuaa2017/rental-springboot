package com.rental.demo.controller;

import com.alibaba.fastjson.JSON;
import com.rental.demo.entity.Maintenanceman;
import com.rental.demo.repository.MaintenancemanRepository;
import com.rental.demo.service.RepairmanService.MaintenancemanService;
import com.rental.demo.service.RepairmanService.RepairmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MaintenancemanController {
    @Autowired
    private MaintenancemanService maintenancemanService;
    @Autowired
    private RepairmanService repairmanService;
    @Autowired
    private MaintenancemanRepository maintenancemanRepository;
    //@CrossOrigin
    @RequestMapping(value = "/maintenanceman",method = RequestMethod.POST)
    @ResponseBody
    public int add(@RequestBody Map<String,Object> map){
        String jsonString = JSON.toJSONString(map);
        //return jsonString+" "+JSON.toJSONString(new Maintenanceman("y199387","174589654","17845612378","HuMZ",35,true,0));
        Maintenanceman maintenanceman = JSON.parseObject(jsonString,Maintenanceman.class);
        return maintenancemanService.add(maintenanceman);
    }

    @RequestMapping(value = "/maintenanceman/delete",method = RequestMethod.POST)
    @ResponseBody
    public int delete(@RequestParam(value = "username") String username){
        return repairmanService.delete(username);
    }

    @RequestMapping(value = "/miantenanceman/getscore",method = RequestMethod.GET)
    @ResponseBody
    public double showscore(@RequestParam(value = "username")String username){
        Maintenanceman maintenanceman = maintenancemanService.findByUsername(username);
        return maintenanceman.getAscore()/maintenanceman.getTimes();
    }

    @RequestMapping(value = "/miantenanceman/getAll",method = RequestMethod.GET)
    @ResponseBody
    public List<Maintenanceman> getAll(){
        return maintenancemanRepository.findAll();
    }
}
