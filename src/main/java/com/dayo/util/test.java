package com.dayo.util;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author DayoWong on 2018/9/30
 */
public class test {

    @Test
    public void testing(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm");//激活码有效时间一分钟
        System.out.println(format.format(new Date()));
    }
}
