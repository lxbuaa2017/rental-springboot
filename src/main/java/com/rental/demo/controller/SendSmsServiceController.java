package com.rental.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rental.demo.entity.LongRentOrder;
import com.rental.demo.service.SendSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/")
public class SendSmsServiceController {
    @Autowired
    private SendSmsService sendSmsService;
    @CrossOrigin
    @RequestMapping(value = "/sendSms",method = RequestMethod.POST)
    @ResponseBody
    public boolean sendSms(@RequestParam("phone")String phone,HttpSession httpSession
                          ){
        String code=sendSmsService.sendVerificationCode(phone);
        if(code.equals("发送失败"))
            return false;
        else{
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("phone",phone);
            jsonObject.put("code",code);
            jsonObject.put("createTime",System.currentTimeMillis());
            httpSession.setAttribute("codeResult",jsonObject);
            return true;
        }
    }
    public boolean sendRemindSms(@RequestParam("long_rent_order") LongRentOrder longRentOrder, HttpSession httpSession
    ){
        String phone=longRentOrder.getTenant().getPhone();
        String code=sendSmsService.sendReminderSms(phone);
        if(code.equals("发送失败"))
            return false;
        else{
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("phone",phone);
            jsonObject.put("code",code);
            jsonObject.put("createTime",System.currentTimeMillis());
            httpSession.setAttribute("codeResult",jsonObject);
            return true;
        }
    }
}
