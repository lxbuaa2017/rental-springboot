package com.rental.demo.repository;

import com.rental.demo.entity.LandLord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LandLordRepository extends MongoRepository<LandLord,Long> {
}
