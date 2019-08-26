package com.rental.demo.serviceImpl.TenantServiceImpl;

import com.rental.demo.entity.Tenant;
import com.rental.demo.service.TenantService.TenantRegisterService;
import com.rental.demo.service.UserService;
import com.rental.demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantRegisterServiceImpl implements TenantRegisterService {
    @Autowired
    private UserService userService;
    @Override
    public int register(Tenant tenant) {
        String input_name= tenant.getUsername();
        if(userService.findByName(input_name)!=null)
            return Constant.USER_ALREADY_EXIST;
        else {
            userService.save(tenant);
            return Constant.SUCCESS;
        }
    }
}
