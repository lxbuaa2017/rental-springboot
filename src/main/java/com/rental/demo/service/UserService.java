package com.rental.demo.service;

import com.rental.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    public User findOne(Long id);
    public User findByName(String name);
    public User save(User user);
    public List<User> queryAll();
}
