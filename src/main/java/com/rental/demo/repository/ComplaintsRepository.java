package com.rental.demo.repository;

import com.rental.demo.entity.Complaints;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ComplaintsRepository extends MongoRepository<Complaints,String> {
    List<Complaints> findByTenantUsername(String name);
}
