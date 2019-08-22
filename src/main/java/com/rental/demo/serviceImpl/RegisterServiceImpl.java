package com.rental.demo.serviceImpl;

import com.rental.demo.entity.User;
import com.rental.demo.service.RegisterService;
import com.rental.demo.service.UserService;
import com.rental.demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserService userService;
    @Override
    public int register(User user) {
        String input_name=user.getUsername();
        if(userService.findByName(input_name)!=null)
            return Constant.userAreadyExist;
        else {
            userService.save(user);
            return Constant.success;
        }
    }
}
