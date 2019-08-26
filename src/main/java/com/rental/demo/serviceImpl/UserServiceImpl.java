package com.rental.demo.serviceImpl;

import com.rental.demo.entity.Tenant;
import com.rental.demo.repository.TenantRepository;
import com.rental.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TenantRepository tenantRepository;
    @Override
    public Tenant findOne(String id) {
        return tenantRepository.findById(id).get();
    }

    @Override
    public Tenant save(Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    @Override
    public List<Tenant> queryAll() {
        return tenantRepository.findAll();
    }

    @Override
    public Tenant findByName(String username) {
        return tenantRepository.findByUsername(username);
    }
}
