package com.rental.demo.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.rental.demo.entity.LongRentOrder;
import com.rental.demo.service.SendSmsService;
import com.rental.demo.util.Constant;
import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Random;

@Service
public class SendSmsServiceImpl implements SendSmsService {
    @Override
    public String sendVerificationCode(String phone) {
        String verificationCode=String.valueOf(new Random().nextInt(999999));
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

    @Override
    public String sendReminderSms(LongRentOrder longRentOrder) {      //每天固定时间运行一次,如果是23号,发送短信
        String phone=longRentOrder.getTenant().getPhone();
        Calendar time=Calendar.getInstance();   //获取当前时间
        int day=time.get(Calendar.DAY_OF_MONTH);    //获取当前日期
        if(day!=23){
            longRentOrder.setReminded(false);
            return "不在预订的发送时间,发送失败";
        }
        else if(day==23&&!longRentOrder.isReminded()){  //如果23号没有提醒过
            ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com",
                    "102458", "f9ab7d85-bea0-48ef-875b-6895f4838061");
            try{
                String result = client.send(phone, "现在距离月底还有一周,请及时提交长租租金,若您已提交,请忽视本条信息.       --青年租房网" );
                JSONObject json = JSONObject.parseObject(result);
                if (json.getIntValue("code")!=0) {//发送短信失败
                    return "发送失败";
                }
                else {
                    longRentOrder.setReminded(true);
                    return null;
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
