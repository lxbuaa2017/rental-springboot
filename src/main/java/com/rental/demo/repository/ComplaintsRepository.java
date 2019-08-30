package com.rental.demo.repository;

import com.rental.demo.entity.Complaints;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ComplaintsRepository extends MongoRepository<Complaints,String> {
    public List<Complaints> findAllByTenantUsernameAndState(String username,int state);
    public List<Complaints> findAllByState(int state);
}
