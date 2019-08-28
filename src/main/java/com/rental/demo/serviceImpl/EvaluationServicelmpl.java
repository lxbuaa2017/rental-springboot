package com.rental.demo.serviceImpl;

import com.rental.demo.entity.Evaluation;
import com.rental.demo.repository.EvaluationRepository;
import com.rental.demo.service.EvaluationService;
import com.rental.demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Evaluation> findByMaintenancemanUsername(String name) {
        return evaluationRepository.findByMaintenancemanUsername(name);
    }

    @Override
    public int change(String id,String message){
        if (evaluationRepository.existsById(id)==false)
            return Constant.EVA_NOT_EXIST;
        else {
            Optional<Evaluation> evaluation = evaluationRepository.findById(id);
            evaluation.get().setMessage(message);
            evaluation.get().setLastChangeTime(new Date());
            return Constant.SUCCESS;
        }
    }
}
