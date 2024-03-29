package com.rental.demo.service.TenantService;

import com.rental.demo.entity.LongRentOrder;
import com.rental.demo.entity.ShortRentOrder;
import com.rental.demo.entity.Tenant;

public interface RentService {
    public int shortRentalEnroll(ShortRentOrder shortRentOrder);
    public int longRentalEnroll(LongRentOrder longRentOrder);
}
