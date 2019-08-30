package com.rental.demo.util;

public class Constant {
public static final int SUCCESS =1000;
    public static final int ERROR =9999;

    //注册登录
    public static final int USER_ALREADY_EXIST =1028;

    //订单
    public static final int WAIT_CONFIRM =1823;//待审核
    public static final int WAIT_PAY =1824;//待付租金
    public static final int PAID =1825;//已付租金

    //房间状态
    public static final int RENTED =2001;
    public static final int FREE =2002;


    //师傅
    public static final int MTM_ALREADY_EXIST = 3001;
    public static final int MTM_NOT_EXIST = 3002;

    //投诉
    public static final int COM_NOT_EXIST = 4002;
    public static final int USER_HAS_NO_COM = 4001;
    public static final int REPLIED=4004;//已回复
    public static final int UNREPLIED=4003;//未回复

    //房间
    public static final int ROOM_AREADY_EXIST =2003;
    public static final int LONGRENT=2004;//只能长租
    public static final int SHORTRENT=2005;//只能短租
    public static final int BOTHRENT=2006;//长租短租都可以

    //评价
    public static final int EVA_NOT_EXIST = 5001;

    //工单
    public static final int WOD_NOT_RES = 6001;//工单待处理
    public static final int WOD_ING_RES = 6002;//工单处理中
    public static final int WOD_FIS_RES = 6003;//工单已处理
    public static final int WOD_ALR_RES = 6004;//工单已评价
}
