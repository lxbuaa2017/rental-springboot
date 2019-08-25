package com.rental.demo.util;

public class Constant {
    //操作成功
    public static final int SUCCESS =1000;
    public static final int ERROR =9999;

    //注册登录
    public static final int USER_AREADY_EXIST =1028;

    //订单
    public static final int WAIT_CONFIRM =1823;//待审核
    public static final int WAIT_PAY =1824;//待付租金
    public static final int PAID =1825;//已付租金

    //房间状态
    public static final int RENTED =2001;
    public static final int FREE =2002;
    public static final int ROOM_AREADY_EXIST =2003;
    public static final int LONGRENT=2004;//只能长租
    public static final int SHORTRENT=2005;//只能短租
    public static final int BOTHRENT=2006;//长租短租都可以
}
