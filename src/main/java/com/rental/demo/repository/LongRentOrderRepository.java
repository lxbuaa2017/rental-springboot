package com.rental.demo.repository;

import com.rental.demo.entity.LongRentOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LongRentOrderRepository extends MongoRepository<LongRentOrder,String> {
    public List<LongRentOrder> findByTenantName(String tenantName);
    public List<LongRentOrder> findAllByState(int state);
    public List<LongRentOrder> findAllByStateAndLeaveDayAfter(int state, String time);
    public List<LongRentOrder> findAllByUrlExists();
    public List<LongRentOrder> findAllByLeaveDayBefore(String now);
}
