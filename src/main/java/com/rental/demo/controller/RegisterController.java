package com.rental.demo.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
import com.rental.demo.entity.User;
import com.rental.demo.service.RegisterService;
import com.rental.demo.service.UserService;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
public class RegisterController {
    @Autowired
    private UserService userService;
    @Autowired
    private RegisterService registerService;
    @CrossOrigin
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public int register(@RequestBody Map<String,Object> map){
        String jsonString= JSON.toJSONString(map);
        User user= JSON.parseObject(jsonString,User.class);
        return registerService.register(user);
    }
}
