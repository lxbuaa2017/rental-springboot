package com.rental.demo.service;

import com.rental.demo.entity.Complaints;

public interface ComplaintsService {
    public Long publish(Complaints complaints);
    public int delete(Long id);
}
