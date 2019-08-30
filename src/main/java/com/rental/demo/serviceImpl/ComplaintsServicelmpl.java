package com.rental.demo.serviceImpl;

import com.rental.demo.entity.Complaints;
import com.rental.demo.repository.ComplaintsRepository;
import com.rental.demo.service.ComplaintsService;
import com.rental.demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplaintsServicelmpl implements ComplaintsService {
    @Autowired
    private ComplaintsRepository complaintsRepository;
    @Override
    public String addComplain(Complaints complaints) {
        complaintsRepository.save(complaints);
        return complaints.getId();
    }

    @Override
    public int delete(String id) {
        if(complaintsRepository.findById(id)==null){
            return Constant.COM_NOT_EXIST;
        }
        else {
            complaintsRepository.deleteById(id);
            return Constant.SUCCESS;
        }
    }
}
