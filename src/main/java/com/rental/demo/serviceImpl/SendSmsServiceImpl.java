package com.rental.demo.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.rental.demo.service.SendSmsService;
import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SendSmsServiceImpl implements SendSmsService {
    @Override
    public String sendVerificationCode(String phone) {
        String verificationCode=String.format("%06d", new Random().nextInt(999999));
        ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com",
                "102458", "f9ab7d85-bea0-48ef-875b-6895f4838061");
        try {
            String result = client.send(phone, "您的验证码为:" + verificationCode + "，该码有效期为5分钟，请勿泄露。       --青年租房网");
            JSONObject json = JSONObject.parseObject(result);
            if (json.getIntValue("code")!=0){//发送短信失败
                //System.out.println(json.get("data"));
                return  "发送失败";
            }
            else return verificationCode;
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
