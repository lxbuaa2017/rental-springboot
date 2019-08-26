package com.rental.demo.repository;

import com.rental.demo.entity.Tenant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends MongoRepository<Tenant,String> {
    public Tenant findByUsername(String username);
}
