package com.rental.demo.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rental.demo.entity.LongRentOrder;
import com.rental.demo.entity.ShortRentOrder;
import com.rental.demo.repository.LongRentOrderRepository;
import com.rental.demo.repository.ShortRentOrderRepository;
import com.rental.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private LongRentOrderRepository longRentOrderRepository;
    @Autowired
    private ShortRentOrderRepository shortRentOrderRepository;
    @Override
    public int getOrderTotal(String username) {
        return longRentOrderRepository.findByTenantName(username).size()+shortRentOrderRepository.findByTenantName(username).size();
    }

    @Override
    public JSONObject getOrder(String username,String id,String type) {
        JSONObject jsonObject=null;
        if(type.equals("长租")){
            LongRentOrder longRentOrder=longRentOrderRepository.findById(id).get();
            if(longRentOrder!=null){
                if(!longRentOrder.getTenantName().equals(username))
                    return null;
            }
            String str= JSON.toJSONString(longRentOrder);
            String str1="{\"type\":"+type+",\n" +
                    "    \"order\":"+str+"}";
            return JSONObject.parseObject(str1);
        }
        else if(type.equals("短租")){
            ShortRentOrder shortRentOrder=shortRentOrderRepository.findById(id).get();
            if(shortRentOrder!=null){
                if(!shortRentOrder.getTenantName().equals(username))
                    return null;
            }
            String str= JSON.toJSONString(shortRentOrder);
            String str1="{\"type\":"+type+",\n" +
                    "    \"order\":"+str+"}";
            return JSONObject.parseObject(str1);
        }
        return jsonObject;
    }

    @Override
    public List<Object> getAllOrder(String username) {
        List<Object> list=new LinkedList<>();
        List<ShortRentOrder> shortRentOrders=shortRentOrderRepository.findByTenantName(username);
        List<LongRentOrder> longRentOrders=longRentOrderRepository.findByTenantName(username);
        list.addAll(shortRentOrders);
        list.addAll(longRentOrders);
        Collections.sort(list, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                LocalDateTime t1,t2;
                if(o1 instanceof ShortRentOrder){
                    t1=((ShortRentOrder)o1).getCreatedTime();
                }
                else {
                    t1=((LongRentOrder)o1).getCreatedTime();
                }
                if(o2 instanceof ShortRentOrder){
                    t2=((ShortRentOrder)o2).getCreatedTime();
                }
                else {
                    t2=((LongRentOrder)o2).getCreatedTime();
                }
                if(t1.isAfter(t2))
                    return -1;
                else
                    return 1;
            }
        });
        List<Object> orderList=new ArrayList<>();
        for(Object object:list){
            JSONObject jsonObject=new JSONObject();
            if(object instanceof ShortRentOrder)
                jsonObject.put("type","短租");
            else
                jsonObject.put("type","长租");
            jsonObject.put("object",object);
            orderList.add(jsonObject);
        }
        return orderList;
    }

    @Override
    //182 357
    public List<Object> getUnprocessedOrder() {
        List<Object> list=new ArrayList<>();
        List<LongRentOrder> longRentOrders=new ArrayList<>();
        List<LongRentOrder> longRentOrders1=longRentOrderRepository.findAllByState(1823);
        List<LongRentOrder> longRentOrders2=longRentOrderRepository.findAllByState(1825);
        List<LongRentOrder> longRentOrders3=longRentOrderRepository.findAllByState(1827);
        longRentOrders.addAll(longRentOrders1);longRentOrders.addAll(longRentOrders2);longRentOrders.addAll(longRentOrders3);
        for(LongRentOrder longRentOrder:longRentOrders){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("type","长租");
            jsonObject.put("object",longRentOrder);
            list.add(jsonObject);
        }
        List<ShortRentOrder> shortRentOrders=new ArrayList<>();
        List<ShortRentOrder> shortRentOrders1=shortRentOrderRepository.findAllByState(1823);
        List<ShortRentOrder> shortRentOrders2=shortRentOrderRepository.findAllByState(1825);
        List<ShortRentOrder> shortRentOrders3=shortRentOrderRepository.findAllByState(1827);
        shortRentOrders.addAll(shortRentOrders1);shortRentOrders.addAll(shortRentOrders2);shortRentOrders.addAll(shortRentOrders3);
        for(ShortRentOrder shortRentOrder:shortRentOrders){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("type","短租");
            jsonObject.put("object",shortRentOrder);
            list.add(jsonObject);
        }
        return list;
    }

    @Override
    public List<Object> getProcessedOrder() {
        List<Object> list=new ArrayList<>();
        List<LongRentOrder> longRentOrders=new ArrayList<>();
        List<LongRentOrder> longRentOrders1=longRentOrderRepository.findAllByState(1824);
        List<LongRentOrder> longRentOrders2=longRentOrderRepository.findAllByState(1826);
        longRentOrders.addAll(longRentOrders1);longRentOrders.addAll(longRentOrders2);
        for(LongRentOrder longRentOrder:longRentOrders){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("type","长租");
            jsonObject.put("object",longRentOrder);
            list.add(jsonObject);
        }
        List<ShortRentOrder> shortRentOrders=new ArrayList<>();
        List<ShortRentOrder> shortRentOrders1=shortRentOrderRepository.findAllByState(1824);
        List<ShortRentOrder> shortRentOrders2=shortRentOrderRepository.findAllByState(1826);
        shortRentOrders.addAll(shortRentOrders1);shortRentOrders.addAll(shortRentOrders2);
        for(ShortRentOrder shortRentOrder:shortRentOrders){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("type","短租");
            jsonObject.put("object",shortRentOrder);
            list.add(jsonObject);
        }
        return list;
    }
}
