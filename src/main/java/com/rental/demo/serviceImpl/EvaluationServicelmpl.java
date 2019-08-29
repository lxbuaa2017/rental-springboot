package com.rental.demo.serviceImpl;

import com.rental.demo.entity.Evaluation;
import com.rental.demo.repository.EvaluationRepository;
import com.rental.demo.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EvaluationServicelmpl implements EvaluationService {
    @Autowired
    EvaluationRepository evaluationRepository;
    @Override
    public String post(Evaluation evaluation) {
        return evaluationRepository.save(evaluation).getId();
    }

    @Override
    public String delete(String id) {
        evaluationRepository.deleteById(id);
        return id;
    }


    @Override
    public Evaluation findByid(String id) {
        return evaluationRepository.findById(id).get();
    }

}
