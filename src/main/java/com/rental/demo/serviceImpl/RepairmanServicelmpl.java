package com.rental.demo.serviceImpl;

import com.rental.demo.entity.Maintenanceman;
import com.rental.demo.repository.MaintenancemanRepository;
import com.rental.demo.service.RepairmanService.RepairmanService;
import com.rental.demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairmanServicelmpl implements RepairmanService {
    @Autowired
    private MaintenancemanRepository maintenancemanRepository;
    @Override
    public Maintenanceman findOne(String id){
        return maintenancemanRepository.findById(id).get();
    }



    @Override
    public Maintenanceman findByRealName(String name) {
        return maintenancemanRepository.findByRealname(name);
    }

    @Override
    public Maintenanceman save(Maintenanceman maintenanceman) {
        return maintenancemanRepository.save(maintenanceman);
    }

    @Override
    public List<Maintenanceman> queryAll() {
        return maintenancemanRepository.findAll();
    }

    @Override
    public int change(Maintenanceman maintenanceman) {
        maintenancemanRepository.deleteById(maintenanceman.getUsername());
        maintenancemanRepository.save(maintenanceman);
        return Constant.SUCCESS;
    }

    @Override
    public int delete(String username) {
        maintenancemanRepository.deleteById(username);
        return Constant.SUCCESS;
    }

    @Override
    public boolean ifexists(String id) {
        return maintenancemanRepository.existsById(id);
    }


}
