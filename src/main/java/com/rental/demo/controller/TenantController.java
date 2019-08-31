package com.rental.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rental.demo.entity.Tenant;
import com.rental.demo.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TenantController {
    @Autowired
    private TenantRepository tenantRepository;

    @PostMapping("/tenant/update")
    public String updateTenant(@RequestBody JSONObject jsonObject) {
        Map<String, Object> map = (Map<String, Object>) jsonObject.get("tenant");
        String jsonString = JSON.toJSONString(map);
        Tenant tenant = JSONObject.parseObject(jsonString, Tenant.class);
        return JSON.toJSONString(tenantRepository.save(tenant));
    }

    @RequestMapping(value = "/tenant/getAll", method = RequestMethod.GET)
    public List<Tenant> getAllTenant() {
        return tenantRepository.findAll();
    }

    @RequestMapping(value = "/tenant/getByName", method = RequestMethod.GET)
    @ResponseBody
    public Tenant getByName(@RequestParam String name) {
        return tenantRepository.findById(name).get();
    }
}
