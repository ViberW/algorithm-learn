package com.welph.leecode.part_1_500.part_141_160;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 * <p>
 * --pop、top 和 getMin 操作总是在 非空栈 上调用。
 */
public class Solution155 {
    public static void main(String[] args) {
        //["MinStack","push","push","push","getMin","pop","top","getMin"]
        //[[],[-2],[0],[-3],[],[],[],[]]
        MinStack obj = new MinStack();
        obj.push(-2);
        obj.push(0);
        obj.push(-3);
        System.out.println(obj.getMin());
        obj.pop();
        System.out.println(obj.top());
        System.out.println(obj.getMin());
    }


    static class MinStack {

        Entry[] data;
        int size = 0;
        int capacity;
        int minCapacity;
        Entry[] entries; //小顶堆

        public MinStack() {
            this.minCapacity = 10;
            this.capacity = minCapacity;
            data = new Entry[capacity];
            entries = new Entry[capacity + 1]; //从下标为1开始, 方便计算
        }

        public void push(int x) {
            expand();
            Entry entry = new Entry(x);
            data[size++] = entry;
            insertHeapify(entry);
        }

        private void insertHeapify(Entry entry) {
            int hold = size;
            for (; hold > 1 && entry.value < entries[hold / 2].value; hold /= 2) {
                entries[hold] = entries[hold / 2];
                entries[hold].index = hold;
            }
            entry.index = hold;
            entries[hold] = entry;
        }

        public void pop() {
            --size;
            Entry entry = data[size];
            deleteHeapify(entry);
            data[size] = null;
            shrink();
        }

        private void deleteHeapify(Entry entry) {
            Entry lastEntry = entries[size + 1];
            if (lastEntry != entry) {
                int hold = entry.index;
                int child;
                for (; hold * 2 <= size; hold = child) {
                    child = hold * 2;
                    if (child != size && entries[child + 1].value < entries[child].value)
                        child++;
                    if (entries[child].value < lastEntry.value) {
                        entries[hold] = entries[child];
                        entries[hold].index = hold;
                    } else {
                        break;
                    }
                }
                entries[hold] = lastEntry;
                lastEntry.index = hold;
            }
            entries[size + 1] = null;
        }


        public int top() {
            return data[size - 1].value;
        }

        public int getMin() {
            return entries[1].value;
        }

        private void shrink() {
            if (minCapacity != capacity && capacity / 4 >= size) {
                capacity = Math.max(capacity / 2, minCapacity);
                rangeCapacity();
            }
        }

        private void expand() {
            if (size >= capacity) {
                capacity = capacity * 2;
                rangeCapacity();
            }
        }

        private void rangeCapacity() {
            Entry[] newData = new Entry[capacity];
            System.arraycopy(data, 0, newData, 0, size);
            this.data = newData;
            Entry[] newEntries = new Entry[capacity + 1];
            System.arraycopy(entries, 1, newEntries, 1, size);
            this.entries = newEntries;
        }

        private static class Entry {
            int index;
            int value;

            public Entry(int value) {
                this.value = value;
            }
        }
    }
}
