package com.rental.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rental.demo.entity.Complaints;
import com.rental.demo.repository.ComplaintsRepository;
import com.rental.demo.service.ComplaintsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static com.rental.demo.util.Constant.REPLIED;
import static com.rental.demo.util.Constant.UNREPLIED;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintsController {
    @Autowired
    private ComplaintsService complaintsService;
    @Autowired
    private ComplaintsRepository complaintsRepository;
    //@CrossOrigin
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String addComplain(@RequestBody Map<String,Object> map){
        String jsonString = JSON.toJSONString(map);
        Complaints complaints = JSON.parseObject(jsonString, Complaints.class);
        complaints.setCreatedTime(LocalDateTime.now());
        complaints.setState(UNREPLIED);
        return complaintsService.addComplain(complaints);
    }

    //@CrossOrigin
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public int delete(@RequestParam("id") String id){
        return complaintsService.delete(id);
    }
    //@CrossOrigin
    @RequestMapping(value = "/reply",method = RequestMethod.POST)
    @ResponseBody
    public void reply(@RequestBody JSONObject jsonObject){
        String reply=jsonObject.getString("replyMessage");
        String id=jsonObject.getString("complaintId");
        Complaints complaints=complaintsRepository.findById(id).get();
        complaints.setReply(reply);
        complaints.setState(REPLIED);
        complaintsRepository.save(complaints);
    }

    //@CrossOrigin
    @RequestMapping(value="/getTenantComplaints",method = RequestMethod.POST)
    @ResponseBody
    public List<Complaints> getTenantComplaints(@RequestParam String username){
        List<Complaints> list= complaintsRepository.findAllByTenantUsername(username);
        Collections.sort(list, new Comparator<Complaints>() {
            @Override
            public int compare(Complaints o1, Complaints o2) {
                if(o1.getCreatedTime().isAfter(o2.getCreatedTime()))
                    return -1;
                else
                    return 1;
            }
        });
        return list;
    }
}
