package com.rental.demo.service;

import com.rental.demo.entity.Evaluation;

import java.util.List;

public interface EvaluationService {
    public String post(Evaluation evaluation);
    public String delete(String id);
    public Evaluation findByid(String id);
    public List<Evaluation> findByTenantUsername(String name);
    public List<Evaluation> findByMaintenancemanUsername(String name);
    public int change(String id,String message);
}
