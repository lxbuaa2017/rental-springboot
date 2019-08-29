package com.rental.demo.service;

import com.rental.demo.entity.Complaints;

public interface ComplaintsService {
    public String addComplain(Complaints complaints);
    public int delete(String id);
}
