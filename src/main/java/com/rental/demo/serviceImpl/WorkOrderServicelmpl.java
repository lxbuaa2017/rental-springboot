package com.rental.demo.serviceImpl;

import com.rental.demo.entity.Maintenanceman;
import com.rental.demo.entity.WorkOrder;
import com.rental.demo.repository.WorkOrderRepository;
import com.rental.demo.service.RepairmanService.MaintenancemanService;
import com.rental.demo.service.WorkOrderService;
import com.rental.demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkOrderServicelmpl implements WorkOrderService {
    @Autowired
    private WorkOrderRepository workOrderRepository;
    @Autowired
    private MaintenancemanService maintenancemanService;
    @Override
    public int addWorkOrder(WorkOrder workOrder) {
        workOrderRepository.save(workOrder);
        return Constant.SUCCESS;
    }

    @Override
    public int distribution(String WOId, String maintenancemanName) {
        if (workOrderRepository.existsById(WOId)==false){
            return Constant.ERROR;
        }
        WorkOrder workOrder = workOrderRepository.findById(WOId).get();
        if(workOrder.getStats() != Constant.WOD_NOT_RES){
            return workOrder.getStats();
        }
        else{
            workOrder.setMaintenanceName(maintenancemanName);
            return Constant.SUCCESS;
        }
    }

    @Override
    public int finish(String WOId) {
        if (workOrderRepository.existsById(WOId)==false){
            return Constant.ERROR;
        }
        WorkOrder workOrder = workOrderRepository.findById(WOId).get();
        workOrder.setStats(Constant.WOD_FIS_RES);
        return workOrder.getStats();
    }

    @Override
    public int evaluate(String WOId, int score) {
        if (workOrderRepository.existsById(WOId)==false){
            return Constant.ERROR;
        }
        WorkOrder workOrder = workOrderRepository.findById(WOId).get();
        Maintenanceman maintenanceman = maintenancemanService.findByUsername(workOrder.getMaintenanceName());
        maintenanceman.setAscore(maintenanceman.getAscore()+score);
        maintenanceman.setTimes(maintenanceman.getTimes()+1);
        return Constant.SUCCESS;
    }
}
