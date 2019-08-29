package com.rental.demo.service.RepairmanService;

import com.rental.demo.entity.Maintenanceman;

public interface MaintenancemanService {
    public int add(Maintenanceman maintenanceman);
    public int delete(String username);
    public int change(Maintenanceman maintenanceman);
}
