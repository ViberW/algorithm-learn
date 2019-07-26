package com.welph.leecode.common;

public class Time {

    public static void time(Function function) {
        long begin = System.currentTimeMillis();
        function.apply();
        System.out.println("execute apply time: " + (System.currentTimeMillis() - begin));
    }

    public static void time(Function... functions) {
        for (int i = 0; i < functions.length; i++) {
            long begin = System.currentTimeMillis();
            functions[i].apply();
            System.out.println("queue[" + i + "] execute apply time: " + (System.currentTimeMillis() - begin));
        }
    }
}
