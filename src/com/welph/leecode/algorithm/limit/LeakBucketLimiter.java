package com.welph.leecode.algorithm.limit;

/**
 * @author Viber
 * @version 1.0
 * @apiNote 漏桶
 * 任意速率的请求进入. 固定速率的请求流出
 * 桶满则请求拒绝
 * 缺陷: 当短时间大量请求,即使服务没负载,请求依旧会在桶中等待一段时间
 * @since 2022/1/25 15:56
 */
public class LeakBucketLimiter {

    //每秒流出速度
    public final int rate;
    public final long capacity;
    //剩余水量
    public long surplus;
    public long lastTimestamp = System.currentTimeMillis();

    public LeakBucketLimiter(int rate, long capacity) {
        this.rate = rate;
        this.capacity = capacity;
        this.surplus = rate;
    }

    public boolean acquire() {
        long now = System.currentTimeMillis();
        long generate = rate * ((now - lastTimestamp) / 1000);
        if (generate > 0) {
            surplus = Math.max(0, surplus - generate); //固定流出
            lastTimestamp = now;
        }
        if (surplus < capacity) {//任意速率流入,这样就能 保证一秒钟不超过capacity的存在
            surplus++; //todo 这里需要改进, 和时间相关
            return true;
        }
        return false;
    }
}
