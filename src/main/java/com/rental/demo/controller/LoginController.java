package com.rental.demo.controller;

import com.rental.demo.entity.Tenant;
import com.rental.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private UserService userService;

    //@CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public int login(@RequestBody Map<String, Object> map) {
        if (map == null)
            return 0;
        else {
            Tenant tenant = userService.findByName(map.get("username").toString());
            if (tenant == null) {
                return 0;
            }
            String input_pwd = map.get("password").toString();
            if (input_pwd.equals(tenant.getPassword()))
                return 1;
            else
                return 0;
        }
    }
}
