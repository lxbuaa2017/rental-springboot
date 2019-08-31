package com.rental.demo.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class RandomLocalDate {
    public LocalDateTime randomLocalDateTime(int startDay, int endDay){

        int plusMinus = 1;
        if(startDay < 0 && endDay > 0){
            plusMinus = Math.random()>0.5?1:-1;
            if(plusMinus>0){
                startDay = 0;
            }else{
                endDay = Math.abs(startDay);
                startDay = 0;
            }
        }else if(startDay < 0 && endDay < 0){
            plusMinus = -1;

            //两个数交换
            startDay = startDay + endDay;
            endDay  = startDay - endDay;
            startDay = startDay -endDay;

            //取绝对值
            startDay = Math.abs(startDay);
            endDay = Math.abs(endDay);

        }

        LocalDate day = LocalDate.now().plusDays(plusMinus * (int)Math.random()*(endDay-startDay)+startDay);
        int hour = (int)Math.random()*23+1;
        int minute = (int)Math.random()*60;
        int second = (int)Math.random()*60;
        LocalTime time = LocalTime.of(hour, minute, second);
        return LocalDateTime.of(day, time);
    }
}
