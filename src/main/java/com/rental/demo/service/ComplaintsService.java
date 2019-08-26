package com.rental.demo.service;

import com.rental.demo.entity.Complaints;

import java.util.List;

public interface ComplaintsService {
    public String publish(Complaints complaints);
    public int delete(String id);
    public List<Complaints> findByTenantUsername(String name);

}
