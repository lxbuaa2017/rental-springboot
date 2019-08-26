package com.rental.demo.controller;

import com.alibaba.fastjson.JSON;
import com.rental.demo.entity.Complaints;
import com.rental.demo.service.ComplaintsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class ComplaintsController {
    @Autowired
    private ComplaintsService complaintsService;

    @CrossOrigin
    @RequestMapping(value = "/complaints/create", method = RequestMethod.POST)
    @ResponseBody
    public String publish(@RequestBody Map<String, Object> map) {
        String jsonString = JSON.toJSONString(map);
        Complaints complaints = JSON.parseObject(jsonString, Complaints.class);
        complaints.setCreatedTime(new Date());
        return complaintsService.publish(complaints);
    }

    @RequestMapping(value = "/complaints/delete", method = RequestMethod.DELETE)
    public int delete(@RequestParam("id") String id) {
        return complaintsService.delete(id);
    }

    @RequestMapping(value = "/complaints/findbyname", method = RequestMethod.GET)
    public List<Complaints> findByname(@RequestParam("name") String name) {
        return complaintsService.findByTenantUsername(name);
    }
}
