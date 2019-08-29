package com.rental.demo.service;

import com.rental.demo.entity.Complaints;

public interface ComplaintsService {
    public String publish(Complaints complaints);
    public int delete(String id);
}
