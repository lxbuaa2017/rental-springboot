package com.rental.demo.repository;

import com.rental.demo.entity.Maintenanceman;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MaintenancemanRepository extends MongoRepository<Maintenanceman,String> {
    public Maintenanceman findByRealname(String name);
    public Maintenanceman findByUsername(String name);
    public boolean existsByUsername(String name);
}
