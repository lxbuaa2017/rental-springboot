package com.rental.demo.repository;

import com.rental.demo.entity.ShortRentOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShortRentOrderRepository extends MongoRepository<ShortRentOrder,String> {
    public List<ShortRentOrder> findByTenantName(String tenantName);
    public List<ShortRentOrder> findAllByState(int state);
    public List<ShortRentOrder> findAllByLeaveDayBefore(String now);

}
