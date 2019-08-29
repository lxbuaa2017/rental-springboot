package com.rental.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.rental.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;
    //@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
    @RequestMapping(value = "/getOrderTotal", method = RequestMethod.POST)
    @ResponseBody
    public int getOrderTotal(HttpSession httpSession){
        if(httpSession.getAttribute("username")==null)
            return 0;
        else {
            return orderService.getOrderTotal(
                    httpSession.getAttribute("username").toString());
        }
    }
    //@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
    @RequestMapping(value = "/getOrder", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getOrder(String id,String type,HttpSession httpSession){
        if(httpSession.getAttribute("username")==null)
            return null;
        String username=httpSession.getAttribute("username").toString();
        return orderService.getOrder(username,id,type);
    }
    //@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
    @RequestMapping(value = "/getAllOrder", method = RequestMethod.POST)
    @ResponseBody
    public List<Object> getAllOrder(HttpSession httpSession){
        if(httpSession.getAttribute("username")==null)
            return null;
        String username=httpSession.getAttribute("username").toString();
        return orderService.getAllOrder(username);
    }
}
