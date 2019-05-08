package com.welph.leecode.common;

/**
 * @author: Admin
 * @date: 2019/5/8
 * @Description: {相关描述}
 */
public class Print {

    public static void time(Funtion funtion) {
        long begin = System.currentTimeMillis();
        funtion.run();
        System.out.println("Print.time:" + (System.currentTimeMillis() - begin));
    }

    public interface Funtion {
        void run();
    }
}
