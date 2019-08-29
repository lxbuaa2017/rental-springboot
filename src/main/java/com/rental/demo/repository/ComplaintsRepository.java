package com.rental.demo.repository;

import com.rental.demo.entity.Complaints;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComplaintsRepository extends MongoRepository<Complaints,String> {
}
