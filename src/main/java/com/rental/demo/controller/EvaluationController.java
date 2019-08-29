package com.rental.demo.controller;

import com.alibaba.fastjson.JSON;
import com.rental.demo.entity.Evaluation;
import com.rental.demo.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/")
public class EvaluationController {
    @Autowired
    private EvaluationService evaluationService;
    @CrossOrigin
    @RequestMapping(value = "/evaluation",method = RequestMethod.POST)
    @ResponseBody
    public Long post(@RequestBody Map<String,Object> map) {
        String jsonString = JSON.toJSONString(map);
        Evaluation evaluation = JSON.parseObject(jsonString, Evaluation.class);
        evaluation.setCreatedTime(new Date());
        evaluation.setLastChangeTime(new Date());
        return evaluationService.post(evaluation);
    }
    @RequestMapping(value = "/evaluation",method = RequestMethod.DELETE)
    @ResponseBody
    public Long delete(Long id){
        return evaluationService.delete(id);
    }
}
