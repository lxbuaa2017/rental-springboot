package com.rental.demo.repository;

import com.rental.demo.entity.WorkOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface WorkOrderRepository extends MongoRepository<WorkOrder,String> {
    public List<WorkOrder> findAllByTenantName(String name);
    public List<WorkOrder> findALLByMaintenanceName(String name);
    public List<WorkOrder> findALLByTenantNameAndStats(String name,int stats);
    public List<WorkOrder> findAllByStats(int stats);
}
