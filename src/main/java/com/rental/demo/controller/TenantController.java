package com.rental.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rental.demo.entity.Tenant;
import com.rental.demo.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TenantController {
    @Autowired
    private TenantRepository tenantRepository;
    @PostMapping("/tenant/update")
    public String updateTenant(@RequestBody Tenant tenant){
       return JSON.toJSONString(tenantRepository.save(tenant));
    }
}
