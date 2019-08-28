package com.rental.demo.controller;

import com.alibaba.fastjson.JSON;
import com.rental.demo.entity.Tenant;
import com.rental.demo.service.TenantService.TenantRegisterService;
import com.rental.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
public class RegisterController {
    @Autowired
    private UserService userService;
    @Autowired
    private TenantRegisterService tenantRegisterService;
    @CrossOrigin(origins = "http://114.115.160.38:8080", allowCredentials = "true")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public int register(@RequestBody Map<String,Object> map){
        String jsonString= JSON.toJSONString(map);
        Tenant tenant = JSON.parseObject(jsonString, Tenant.class);
        return tenantRegisterService.register(tenant);
    }
}
