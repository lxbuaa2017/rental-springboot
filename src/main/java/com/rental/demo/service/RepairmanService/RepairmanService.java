package com.rental.demo.service.RepairmanService;

import com.rental.demo.entity.Maintenanceman;

import java.util.List;

public interface RepairmanService {
    public Maintenanceman findOne(String id);
    public Maintenanceman save(Maintenanceman maintenanceman);
    public List<Maintenanceman> queryAll();
    public int change(Maintenanceman maintenanceman);
    public int delete(String username);
    public boolean ifexists(String id);
}
