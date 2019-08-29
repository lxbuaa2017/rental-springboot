package com.rental.demo.controller;

import com.alibaba.fastjson.JSON;
import com.rental.demo.entity.Complaints;
import com.rental.demo.service.ComplaintsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/")
public class ComplaintsController {
    @Autowired
    private ComplaintsService complaintsService;
    @CrossOrigin
    @RequestMapping(value = "/Complaints",method = RequestMethod.POST)
    @ResponseBody
    public String publish(@RequestBody Map<String,Object> map){
        String jsonString = JSON.toJSONString(map);
        Complaints complaints = JSON.parseObject(jsonString, Complaints.class);
        complaints.setCreatedTime(new Date());
        return complaintsService.publish(complaints);
    }

    @RequestMapping(value = "/Complaints",method = RequestMethod.DELETE)
    public int delete(@RequestParam("id") String id){
        return complaintsService.delete(id);
    }

    @GetMapping("/4")
    public Complaints test(){
        Complaints complaints = new Complaints("132456","not good",new Date(),"y199387","17236123");
        return complaints;
    }
}
