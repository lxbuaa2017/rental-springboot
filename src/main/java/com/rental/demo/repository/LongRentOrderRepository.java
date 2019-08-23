package com.rental.demo.repository;

import com.rental.demo.entity.LongRentOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LongRentOrderRepository extends MongoRepository<LongRentOrder,Long> {
}
