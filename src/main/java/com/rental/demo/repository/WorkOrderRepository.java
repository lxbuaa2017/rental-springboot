package com.rental.demo.repository;

import com.rental.demo.entity.WorkOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOrderRepository extends MongoRepository<WorkOrder,Long> {
}
