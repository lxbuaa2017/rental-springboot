package com.rental.demo.service;

import com.rental.demo.entity.WorkOrder;

import java.util.List;

public interface WorkOrderService {
    public int addWorkOrder(WorkOrder workOrder);
    public int distribution(String WOId,String maintenancemanName);
    public int finish(String WOId);
    public int evaluate(String WOId,int score);
}
