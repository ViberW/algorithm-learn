package com.welph.leecode.algorithm.queue;

/**
 * 循环队列代码
 */
public class CirculeQueue {
    private int head = 0;
    private int tail = 0;
    private String[] items;
    private int capacity;

    public CirculeQueue(int capacity) {
        this.capacity = capacity;
        this.items = new String[capacity];
    }

    /**
     * 简单的忽略capacity为1的情况,最终有一个节点为空数据;
     */
    public boolean enqueue(String s) {
        //如果队满,则返回false
        if ((tail + 1) % capacity == head) {
            return false;
        }
        items[tail] = s;
        tail = (tail + 1) % capacity;
        return true;
    }

    public String dequeue() {
        //说明没有数据了
        if (head == tail) {
            return null;
        }
        String s = items[head];
        head = (head + 1) % capacity;
        return s;
    }
}
