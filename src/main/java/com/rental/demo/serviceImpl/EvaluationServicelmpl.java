package com.rental.demo.serviceImpl;

import com.rental.demo.entity.Evaluation;
import com.rental.demo.repository.EvaluationRepository;
import com.rental.demo.service.EvaluationService;
import com.rental.demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationServicelmpl implements EvaluationService {
    @Autowired
    EvaluationRepository evaluationRepository;
    @Override
    public String post(Evaluation evaluation) {
        evaluationRepository.save(evaluation);
        return evaluation.getId();
    }

    @Override
    public String delete(String id) {
        if(evaluationRepository.existsById(id)==false){
            return String.valueOf(Constant.EVA_NOT_EXIST);
        }
        else {
            evaluationRepository.deleteById(id);
            return id;
        }
    }


    @Override
    public Evaluation findByid(String id) {
        return evaluationRepository.findById(id).get();
    }

    @Override
    public List<Evaluation> findByTenantUsername(String name) {
        return evaluationRepository.findByTenantUsername(name);
    }

}
