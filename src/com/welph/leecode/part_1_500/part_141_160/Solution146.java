package com.welph.leecode.part_1_500.part_141_160;

import java.util.HashMap;
import java.util.Map;

/**
 * 运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * 如果关键字不存在，则插入该组「关键字-值」。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 进阶：你是否可以在O(1) 时间复杂度内完成这两种操作？
 * <p>
 * //todo 当然 使用 继承于linkedList 重写方法最快速, 这里选择自己实现
 */
public class Solution146 {

    public static void main(String[] args) {
        LRUCache obj = new LRUCache(2);
        obj.put(1, 1);
        obj.put(2, 2);
        System.out.println(obj.get(1));
        obj.put(3, 3);
        System.out.println(obj.get(2));
        obj.put(4, 4);
        System.out.println(obj.get(1));
        System.out.println(obj.get(3));
        System.out.println(obj.get(4));
    }

    static class LRUCache {

        Map<Integer, Entry> cache;
        int size = 0;
        int capacity;
        LinkNode head;
        LinkNode tail;

        public LRUCache(int capacity) {
            this.head = new LinkNode(null);
            this.tail = new LinkNode(null);
            head.next = tail;
            tail.pre = head;
            this.capacity = capacity;
            this.cache = new HashMap<>(capacity);
        }

        public int get(int key) {
            Entry entry = cache.get(key);
            if (null == entry) {
                return -1;
            }
            upTime(entry, false);
            return entry.value;
        }

        public void put(int key, int value) {
            Entry entry = cache.get(key);
            if (null == entry) {
                entry = new Entry();
                entry.value = value;
                entry.node = new LinkNode(key);
                if (size >= capacity) {
                    LinkNode pre = tail.pre.pre;
                    tail.pre.next = null;
                    tail.pre.pre = null;
                    cache.remove(tail.pre.key);
                    tail.pre = pre;
                    pre.next = tail;
                } else {
                    size++;
                }
                upTime(entry, true);
                cache.put(key, entry);
            } else {
                entry.value = value;
                upTime(entry, false);
            }
        }

        private void upTime(Entry entry, boolean insert) {
            LinkNode target = entry.node;
            if (!insert) {
                target.pre.next = target.next;
                target.next.pre = target.pre;
            }
            LinkNode next = head.next;
            head.next = entry.node;
            entry.node.next = next;
            entry.node.pre = head;
            next.pre = entry.node;
        }

        class Entry {
            int value;
            LinkNode node;
        }

        class LinkNode {
            LinkNode pre;
            LinkNode next;
            Integer key;

            public LinkNode(Integer key) {
                this.key = key;
            }
        }
    }

}
