package com.welph.leecode.algorithm.limit;

/**
 * @author Viber
 * @version 1.0
 * @apiNote 滑动窗口
 * @since 2022/1/25 15:56
 */
public class WindowsLimiter {

    private final int maxCount;
    //时间窗口的从长度
    private final int windowsSize;
    //每个小节的长度
    private final long interval;
    //使用一个数组来表示
    private final Entry[] windows;
    //当前index
    private int current;
    //当前总量
    private int currentCount;

    public WindowsLimiter(int maxCount, int windowsSize, long interval) {
        this.maxCount = maxCount;
        this.windowsSize = windowsSize;
        this.interval = interval;
        //初始化数组列表
        windows = new Entry[windowsSize];
        for (int i = 0; i < windowsSize; i++) {
            windows[i] = new Entry();
        }
    }

    public boolean acquire() {
        long position = position();
        Entry entry = windows[current];
        if (position > entry.position) {
            long delta = Math.min(windowsSize, position - entry.position);
            int sub = 0;
            for (int i = current + 1; i < windowsSize; i++) {
                sub += windows[i].count;
                delta--;
            }
            for (int i = 0; i < delta; i++) {
                sub += windows[i].count;
            }
            currentCount -= sub;
            current = (int) (position % windowsSize);
            entry = windows[current];
            entry.position = position;
            entry.count = 0;
        }
        if (currentCount < maxCount) {
            currentCount++;
            entry.count++;
            return true;
        }
        return false;
    }

    private long position() {
        return System.currentTimeMillis() / interval;
    }

    class Entry {
        long position;
        int count;
    }
}
