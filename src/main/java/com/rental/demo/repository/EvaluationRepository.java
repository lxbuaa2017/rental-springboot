package com.rental.demo.repository;

import com.rental.demo.entity.Evaluation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EvaluationRepository extends MongoRepository<Evaluation,String> {

}
