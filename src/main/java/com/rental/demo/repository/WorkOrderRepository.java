package com.rental.demo.repository;

import com.rental.demo.entity.WorkOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkOrderRepository extends MongoRepository<WorkOrder,String> {
    public WorkOrder findByTenantName(String TenantName);
    public WorkOrder findByMaintenanceName(String MaintenanceName);
}
