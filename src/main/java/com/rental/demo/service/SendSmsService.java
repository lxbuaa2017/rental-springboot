package com.rental.demo.service;

import com.rental.demo.entity.LongRentOrder;

public interface SendSmsService {
        public String sendVerificationCode(String phone);
        public String sendReminderSms(LongRentOrder longRentOrder);        //每月23日发送短信
}
