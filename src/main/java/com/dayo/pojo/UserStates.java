package com.dayo.pojo;

/**
 * @author DayoWong on 2018/9/20
 * 用于标记账号状态
 */
public abstract class UserStates {
    //非数据库类
    public UserStates() {
    }
    public static final int INACTIVATION = 0;//未激活
    public static final int ADMIN = 1;//管理员
    public static final int ACTIVATION = 2;//激活
    public static final int BANNED = 3;  //封号
    public static final int MUTE = 4;  //禁言
    public static final int RELEASE = 2;//解封
}
