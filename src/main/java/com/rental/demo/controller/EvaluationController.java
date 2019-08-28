package com.rental.demo.controller;

import com.alibaba.fastjson.JSON;
import com.rental.demo.entity.Evaluation;
import com.rental.demo.repository.MaintenancemanRepository;
import com.rental.demo.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class EvaluationController {
    @Autowired
    private EvaluationService evaluationService;
    @Autowired
    private MaintenancemanRepository maintenancemanRepository;
    @CrossOrigin
    @RequestMapping(value = "/evaluation",method = RequestMethod.POST)
    @ResponseBody
    public String post(@RequestBody Map<String,Object> map) {
        String jsonString = JSON.toJSONString(map);
        Evaluation evaluation = JSON.parseObject(jsonString, Evaluation.class);
        evaluation.setCreatedTime(new Date());
        evaluation.setLastChangeTime(new Date());
        return evaluationService.post(evaluation);
    }
    @RequestMapping(value = "/evaluation",method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(String id){
        return evaluationService.delete(id);
    }

    @RequestMapping(value = "/evaluation/change",method = RequestMethod.POST)
    @ResponseBody
    public int change(String id,String message){
        return evaluationService.change(id, message);
    }

    @RequestMapping(value = "/evaluation/findByMTM",method = RequestMethod.GET)
    @ResponseBody
    public List<Evaluation> findByMaintenancemanUsername(String name){
        return evaluationService.findByMaintenancemanUsername(name);
    }
}
