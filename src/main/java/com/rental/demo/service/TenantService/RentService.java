package com.rental.demo.service.TenantService;

import com.rental.demo.entity.LongRentOrder;
import com.rental.demo.entity.ShortRentOrder;

public interface RentService {
    public int shortRentalEnroll(ShortRentOrder shortRentOrder);
    public int longRental(LongRentOrder longRentOrder);

}
