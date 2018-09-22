package com.dayo.pojo;

/**
 * @author DayoWong on 2018/9/20
 * 用于标记账号状态
 */
public abstract class State {
    //非数据库类
    public State() {
    }
    public static final int INACTIVATION = 0;//未激活
    public static final int ACTIVATION = 1;//激活
    public static final int BANNED = 2;  //封禁
}
