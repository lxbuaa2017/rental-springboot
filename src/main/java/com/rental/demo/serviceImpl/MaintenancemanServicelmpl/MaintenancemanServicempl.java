package com.rental.demo.serviceImpl.MaintenancemanServicelmpl;

import com.rental.demo.entity.Maintenanceman;
import com.rental.demo.service.RepairmanService.MaintenancemanService;
import com.rental.demo.service.RepairmanService.RepairmanService;
import com.rental.demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaintenancemanServicempl implements MaintenancemanService {
    @Autowired
    private RepairmanService repairmanService;
    @Override
    public int add(Maintenanceman maintenanceman) {
        String input_name = maintenanceman.getUsername();
        if(repairmanService.ifexists(input_name)!= false)
            return Constant.MTM_ALREADY_EXIST;
        else{
            repairmanService.save(maintenanceman);
            return Constant.SUCCESS;
        }
    }

    @Override
    public int delete(String username) {
        if (repairmanService.ifexists(username)==false)
            return Constant.MTM_NOT_EXIST;
        else{
            repairmanService.delete(username);
            return Constant.SUCCESS;
        }
    }

    @Override
    public int change(Maintenanceman maintenanceman){
        String input_name = maintenanceman.getUsername();
        if (repairmanService.ifexists(input_name)==false)
            return Constant.MTM_NOT_EXIST;
        else{
            return repairmanService.change(maintenanceman);
        }
    }

    @Override
    public Maintenanceman findById(String id) {
        return repairmanService.findOne(id);
    }
}
