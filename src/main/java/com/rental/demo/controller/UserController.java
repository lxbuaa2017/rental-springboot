package com.rental.demo.controller;

import com.rental.demo.entity.Tenant;
import com.rental.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/1")
    public String test(){
        Tenant tenant =new Tenant("luoxian","176110abc","17611030267","1753312844@qq.com",true,19);
        return userService.save(tenant).toString();
    }
}
