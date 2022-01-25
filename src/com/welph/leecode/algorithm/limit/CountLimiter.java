package com.welph.leecode.algorithm.limit;

/**
 * @author Viber
 * @version 1.0
 * @apiNote 计数器
 * @since 2022/1/25 15:56
 */
public class CountLimiter {
    //最大申请数量
    final int maxCount;
    //重置间隔
    final long interval;

    long nextTime;
    int count;

    public CountLimiter(int maxCount, long interval) {
        this.maxCount = maxCount;
        this.interval = interval;
    }

    public boolean acquire() {
        if (System.currentTimeMillis() > nextTime) {
            count = 0;
            nextTime = System.currentTimeMillis() + interval;
        }
        if (count > maxCount) {
            return false;
        }
        count++;
        return true;
    }
}
