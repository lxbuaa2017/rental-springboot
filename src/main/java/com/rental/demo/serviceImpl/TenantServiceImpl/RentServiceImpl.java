package com.rental.demo.serviceImpl.TenantServiceImpl;

import com.rental.demo.entity.ShortRentOrder;
import com.rental.demo.entity.Tenant;
import com.rental.demo.repository.ShortRentOrderRepository;
import com.rental.demo.service.TenantService.RentService;
import com.rental.demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentServiceImpl implements RentService {
    @Autowired
    private ShortRentOrderRepository shortRentOrderRepository;
    @Override
    public int shortRentalEnroll(ShortRentOrder shortRentOrder) {
        if(shortRentOrder.getRoom()==null)
            return Constant.ERROR;
       synchronized (shortRentOrder.getRoom()){//防止多次点击出现并发
           if(shortRentOrder.getRoom().getState()!= Constant.FREE){
               return Constant.ERROR;
           }
           else {
               shortRentOrder.getRoom().setState(Constant.RENTED);//把房间置为出租状态
               shortRentOrder.setState(Constant.WAIT_CONFIRM);//订单等待商家审核
               shortRentOrderRepository.save(shortRentOrder);//存入数据库使得商家可以审核
               return Constant.SUCCESS;
           }
       }
    }

    @Override
    public int longRental(Tenant tenant, String roomId) {
        return 0;
    }
}
