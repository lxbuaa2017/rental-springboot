package com.rental.demo.repository;

import com.rental.demo.entity.Code;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeRepository extends MongoRepository<Code,String> {
    public List<Code> findByPhone(String phone);
}
