package com.rental.demo.repository;

import com.rental.demo.entity.ShortRentOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortRentOrderRepository extends MongoRepository<ShortRentOrder,Long> {

}
