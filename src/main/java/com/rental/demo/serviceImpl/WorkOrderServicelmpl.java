package com.rental.demo.serviceImpl;

import com.rental.demo.entity.Maintenanceman;
import com.rental.demo.entity.WorkOrder;
import com.rental.demo.repository.MaintenancemanRepository;
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
    @Autowired
    private MaintenancemanRepository maintenancemanRepository;
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
        else if(maintenancemanRepository.existsByUsername(maintenancemanName)==false){
            return Constant.MTM_NOT_EXIST;
        }
        else{
            workOrder.setMaintenanceName(maintenancemanName);
            workOrder.setStats(Constant.WOD_ING_RES);
            workOrderRepository.deleteById(WOId);
            workOrderRepository.save(workOrder);
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
        workOrderRepository.deleteById(WOId);
        workOrderRepository.save(workOrder);
        return workOrder.getStats();
    }

    @Override
    public int evaluate(String WOId, int score) {
        if (workOrderRepository.existsById(WOId)==false){
            return Constant.ERROR;
        }
        WorkOrder workOrder = workOrderRepository.findById(WOId).get();
        Maintenanceman maintenanceman = maintenancemanRepository.findByUsername(workOrder.getMaintenanceName());
        double Ascore = maintenanceman.getAscore();
        double Times = maintenanceman.getTimes();
        maintenanceman.setAscore(Ascore+score);
        maintenanceman.setTimes(Times+1);
        maintenancemanRepository.deleteById(maintenanceman.getUsername());
        maintenancemanRepository.save(maintenanceman);
        return Constant.SUCCESS;
    }

}
