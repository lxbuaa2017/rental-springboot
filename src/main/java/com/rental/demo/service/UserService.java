package com.rental.demo.service;

import com.rental.demo.entity.Tenant;

import java.util.List;

public interface UserService {
    public Tenant findOne(Long id);
    public Tenant findByName(String name);
    public Tenant save(Tenant tenant);
    public List<Tenant> queryAll();
}
