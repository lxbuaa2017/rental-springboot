package com.rental.demo.service;

import com.rental.demo.entity.Evaluation;

import java.util.List;

public interface EvaluationService {
    public Long post(Evaluation evaluation);
    public Long delete(Long id);
    public Evaluation findByid(Long id);
}
