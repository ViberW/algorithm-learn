package com.welph.leecode.part_500_1000.part_621_640;

/**
 * @author Viber
 * @version 1.0
 * @apiNote
 * @since 2023/5/6 16:00
 */
public class Solution641 {

    public static void main(String[] args) {
        MyCircularDeque obj = new MyCircularDeque(4);
        boolean param_1 = obj.insertFront(1);
        boolean param_2 = obj.insertLast(2);
        boolean param_3 = obj.deleteFront();
        boolean param_4 = obj.deleteLast();
        int param_5 = obj.getFront();
        int param_6 = obj.getRear();
        boolean param_7 = obj.isEmpty();
        boolean param_8 = obj.isFull();
    }

    //使用数组加左右指针更快, 类似于环形数组那样
    static class MyCircularDeque {

        private final Node head;
        private final Node tail;
        private final int capacity;
        private int size = 0;

        public MyCircularDeque(int k) {
            this.head = new Node(0);
            this.tail = new Node(0);
            head.next = tail;
            tail.pre = head;
            this.capacity = k;
        }

        public boolean insertFront(int value) {
            if (size == capacity) {
                return false;
            }
            Node node = new Node(value);
            if (head.next != null) {
                node.next = head.next;
                head.next.pre = node;
            }
            node.pre = head;
            head.next = node;
            size++;
            return true;
        }

        public boolean insertLast(int value) {
            if (size == capacity) {
                return false;
            }
            Node node = new Node(value);
            if (tail.pre != null) {
                node.pre = tail.pre;
                tail.pre.next = node;
            }
            node.next = tail;
            tail.pre = node;
            size++;
            return true;
        }

        public boolean deleteFront() {
            if (size == 0) {
                return false;
            }
            if (head.next.next != null) {
                head.next.next.pre = head;
                head.next = head.next.next;
            } else {
                head.next = null;
            }
            size--;
            return true;
        }

        public boolean deleteLast() {
            if (size == 0) {
                return false;
            }
            if (tail.pre.pre != null) {
                tail.pre.pre.next = tail;
                tail.pre = tail.pre.pre;
            } else {
                tail.pre = null;
            }
            size--;
            return true;
        }

        public int getFront() {
            return size == 0 ? -1 : head.next.val;
        }

        public int getRear() {
            return size == 0 ? -1 : tail.pre.val;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == capacity;
        }

        class Node {

            Node pre;

            Node next;

            int val;

            public Node(int val) {
                this.val = val;
            }
        }
    }

}
