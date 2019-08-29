package com.rental.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rental.demo.service.SendSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class SendSmsServiceController {
    @Autowired
    private SendSmsService sendSmsService;

    //@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
    @RequestMapping(value = "/sendSms", method = RequestMethod.POST)
    @ResponseBody
    public boolean sendSms(@RequestBody JSONObject jsonObject, HttpSession httpSession) {
        String code = sendSmsService.sendVerificationCode(jsonObject.getString("phone"));
        if (code.equals("发送失败"))
            return false;
        else {
            JSONObject res = new JSONObject();
            res.put("phone", jsonObject.getString("phone"));
            res.put("code", code);
            res.put("createTime", System.currentTimeMillis());
            httpSession.setAttribute("codeResult", res);
            return true;
        }
    }
}
