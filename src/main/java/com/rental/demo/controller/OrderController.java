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
    public int getOrderTotal(@RequestBody JSONObject username) {
        return orderService.getOrderTotal(username.getString("username"));
    }

    //@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
    @RequestMapping(value = "/getOrder", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getOrder(@RequestBody JSONObject jsonObject) {
        String str = orderService.getOrder(jsonObject.getString("username"), jsonObject.getString("id"), jsonObject.getString("type")).toJSONString();
        return JSONObject.parseObject("{\"type\":" + jsonObject.getString("type") + ",\n" + "\"order\":" + str + "}");
    }

    //@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
    @RequestMapping(value = "/getAllOrder", method = RequestMethod.POST)
    @ResponseBody
    public List<Object> getAllOrder(@RequestBody JSONObject jsonObject) {
        return orderService.getAllOrder(jsonObject.getString("username"));
    }

    @RequestMapping(value = "/getUnprocessedOrder", method = RequestMethod.GET)
    @ResponseBody
    public List<Object> getUnprocessedOrder() {
        return orderService.getUnprocessedOrder();
    }

    @RequestMapping(value = "/getProcessedOrder", method = RequestMethod.GET)
    @ResponseBody
    public List<Object> getProcessedOrder() {
        return orderService.getProcessedOrder();
    }
}
