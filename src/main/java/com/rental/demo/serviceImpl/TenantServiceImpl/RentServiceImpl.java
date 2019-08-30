package com.rental.demo.serviceImpl.TenantServiceImpl;

import com.rental.demo.entity.LongRentOrder;
import com.rental.demo.entity.Room;
import com.rental.demo.entity.ShortRentOrder;
import com.rental.demo.entity.Tenant;
import com.rental.demo.repository.LongRentOrderRepository;
import com.rental.demo.repository.RoomRepository;
import com.rental.demo.repository.ShortRentOrderRepository;
import com.rental.demo.service.TenantService.RentService;
import com.rental.demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.rental.demo.util.Constant.*;

@Service
public class RentServiceImpl implements RentService {
    @Autowired
    private ShortRentOrderRepository shortRentOrderRepository;
    @Autowired
    private LongRentOrderRepository longRentOrderRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Override
    public int shortRentalEnroll(ShortRentOrder shortRentOrder) {
        if(shortRentOrder.getRoom()==null)
            return ERROR;
       synchronized (shortRentOrder.getRoom()){//防止多次点击出现并发
           if(shortRentOrder.getRoom().getState()!= FREE){
               return ERROR;
           }
           else {
               Room room=shortRentOrder.getRoom();//把房间置为出租状态
               room.setState(RENTED);
               room.setRentTime(LocalDateTime.now());
               roomRepository.save(room);
               shortRentOrder.setState(WAIT_CONFIRM);//订单等待商家审核
               shortRentOrderRepository.save(shortRentOrder);//存入数据库使得商家可以审核
               return SUCCESS;
           }
       }
    }

    @Override
    public int longRentalEnroll(LongRentOrder longRentOrder) {
        if (longRentOrder.getRoom()==null)
            return ERROR;
        synchronized (longRentOrder.getRoom()){
            if (longRentOrder.getRoom().getState()!=FREE){
                return ERROR;
            }
            else{
                Room room=longRentOrder.getRoom();
                room.setState(RENTED);
                room.setRentTime(LocalDateTime.now());
                roomRepository.save(room);
                longRentOrder.setState(Constant.WAIT_CONFIRM);
                longRentOrderRepository.save(longRentOrder);
                return SUCCESS;
            }
        }
    }
}
