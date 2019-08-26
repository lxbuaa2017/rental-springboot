package com.rental.demo.repository;

import com.rental.demo.entity.Evaluation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EvaluationRepository extends MongoRepository<Evaluation,String> {
    List<Evaluation> findByTenantUsername(String name);
}
