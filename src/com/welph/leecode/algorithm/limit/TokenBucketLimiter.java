package com.welph.leecode.algorithm.limit;

/**
 * @author Viber
 * @version 1.0
 * @apiNote 令牌桶
 * 令牌以固定速率生成
 * 生成的令牌存入桶中,多余的令牌则丢弃
 * 请求到达时,尝试从桶中取令牌.便可执行,否则丢弃
 * @since 2022/1/25 15:57
 */
public class TokenBucketLimiter {

    //每秒生成速度
    public final int rate;
    public final long capacity;
    //剩余token
    public long tokens;
    public long lastTimestamp = System.currentTimeMillis();

    public TokenBucketLimiter(int rate, long capacity) {
        this.rate = rate;
        this.capacity = capacity;
    }

    public boolean acquire() {
        long now = System.currentTimeMillis();
        long generate = rate * ((now - lastTimestamp) / 1000);
        if (generate > 0) {
            tokens = Math.min(capacity, tokens + generate); //固定生成
            lastTimestamp = now;
        }
        if (tokens > 0) {
            tokens--;
            return true;
        }
        return false;
    }
}
