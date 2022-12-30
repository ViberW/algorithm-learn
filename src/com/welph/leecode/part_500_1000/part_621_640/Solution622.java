package com.welph.leecode.part_500_1000.part_621_640;

/**
 * 设计你的循环队列实现。 循环队列是一种线性数据结构，
 * 其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，
 * 即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
 * 你的实现应该支持如下操作：
 * <p>
 * MyCircularQueue(k): 构造器，设置队列长度为 k 。
 * Front: 从队首获取元素。如果队列为空，返回 -1 。
 * Rear: 获取队尾元素。如果队列为空，返回 -1 。
 * enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
 * deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
 * isEmpty(): 检查循环队列是否为空。
 * isFull(): 检查循环队列是否已满。
 * <p>
 * 示例：
 * MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
 * circularQueue.enQueue(1);  // 返回 true
 * circularQueue.enQueue(2);  // 返回 true
 * circularQueue.enQueue(3);  // 返回 true
 * circularQueue.enQueue(4);  // 返回 false，队列已满
 * circularQueue.Rear();  // 返回 3
 * circularQueue.isFull();  // 返回 true
 * circularQueue.deQueue();  // 返回 true
 * circularQueue.enQueue(4);  // 返回 true
 * circularQueue.Rear();  // 返回 4
 * <p>
 * 提示：
 * 所有的值都在 0 至 1000 的范围内；
 * 操作数将在 1 至 1000 的范围内；
 * 请不要使用内置的队列库。
 */
public class Solution622 {

    public static void main(String[] args) {
        MyCircularQueue obj = new MyCircularQueue(6);
        System.out.println(obj.enQueue(6));
        System.out.println(obj.Rear());
        System.out.println(obj.Rear());
        System.out.println(obj.deQueue());
        System.out.println(obj.enQueue(5));
        System.out.println(obj.Rear());
        System.out.println(obj.deQueue());
        System.out.println(obj.Front());
        System.out.println(obj.deQueue());
        System.out.println(obj.deQueue());
        System.out.println(obj.deQueue());
    }

    /**
     * {@link com.welph.leecode.algorithm.queue.CirculeQueue}
     */
    static class MyCircularQueue {

        final int capacity;
        final int[] queue;
        int head;
        int tail;

        public MyCircularQueue(int k) {
            this.capacity = k + 1;
            this.queue = new int[capacity];
        }

        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }
            queue[tail] = value;
            tail = (tail + 1) % capacity;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }
            head = (head + 1) % capacity;
            return true;
        }

        //获取头数据
        public int Front() {
            if (isEmpty()) {
                return -1;
            }
            return queue[head];
        }

        //获取尾数据
        public int Rear() {
            if (isEmpty()) {
                return -1;
            }
            return queue[(tail + capacity - 1) % capacity];
        }

        public boolean isEmpty() {
            return head == tail;
        }

        public boolean isFull() {
            return (tail + 1) % capacity == head;
        }
    }

}
