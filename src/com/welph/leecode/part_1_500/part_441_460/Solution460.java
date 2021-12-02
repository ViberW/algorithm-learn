package com.welph.leecode.part_1_500.part_441_460;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现 LFUCache 类：
 * LFUCache(int capacity) - 用数据结构的容量capacity 初始化对象
 * int get(int key)- 如果键存在于缓存中，则获取键的值，否则返回 -1。
 * void put(int key, int value)- 如果键已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量时，则应该在插入新项之前，使最不经常使用的项无效。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近最久未使用 的键。
 * 注意「项的使用次数」就是自插入该项以来对其调用 get 和 put 函数的次数之和。使用次数会在对应项被移除后置为 0 。
 * <p>
 * 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。
 * 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。
 * <p>
 * 示例：
 * 输入：
 * ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
 * 输出：
 * [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
 * <p>
 * 解释：
 * .// cnt(x) = 键 x 的使用计数
 * .// cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
 * .LFUCache lFUCache = new LFUCache(2);
 * .lFUCache.put(1, 1);   // cache=[1,_], cnt(1)=1
 * .lFUCache.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
 * .lFUCache.get(1);      // 返回 1
 * .                      // cache=[1,2], cnt(2)=1, cnt(1)=2
 * .lFUCache.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
 * .                      // cache=[3,1], cnt(3)=1, cnt(1)=2
 * .lFUCache.get(2);      // 返回 -1（未找到）
 * .lFUCache.get(3);      // 返回 3
 * .                      // cache=[3,1], cnt(3)=2, cnt(1)=2
 * .lFUCache.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
 * .                      // cache=[4,3], cnt(4)=1, cnt(3)=2
 * .lFUCache.get(1);      // 返回 -1（未找到）
 * .lFUCache.get(3);      // 返回 3
 * .                      // cache=[3,4], cnt(4)=1, cnt(3)=3
 * .lFUCache.get(4);      // 返回 4
 * .                      // cache=[3,4], cnt(4)=2, cnt(3)=3
 * <p>
 * 提示：
 * 0 <=capacity, key, value <= 104
 * 最多调用 105 次 get 和 put 方法
 */
public class Solution460 {

    public static void main(String[] args) {
        // cnt(x) = 键 x 的使用计数
        // cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
        LFUCache lFUCache = new LFUCache(2);
        lFUCache.put(1, 1);   // cache=[1,_], cnt(1)=1
        lFUCache.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        System.out.println(lFUCache.get(1));      // 返回 1
        // cache=[1,2], cnt(2)=1, cnt(1)=2
        lFUCache.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
        // cache=[3,1], cnt(3)=1, cnt(1)=2
        System.out.println(lFUCache.get(2));      // 返回 -1（未找到）
        System.out.println(lFUCache.get(3));      // 返回 3
        // cache=[3,1], cnt(3)=2, cnt(1)=2
        lFUCache.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
        // cache=[4,3], cnt(4)=1, cnt(3)=2
        System.out.println(lFUCache.get(1));      // 返回 -1（未找到）
        System.out.println(lFUCache.get(3));      // 返回 3
        // cache=[3,4], cnt(4)=1, cnt(3)=3
        System.out.println(lFUCache.get(4));     // 返回 4
        // cache=[3,4], cnt(4)=2, cnt(3)=3


     /*   LFUCache lFUCache = new LFUCache(1);
        lFUCache.put(2, 1);
        lFUCache.get(2);
        lFUCache.put(3, 2);
        lFUCache.get(2);
        lFUCache.get(3);*/
    }

    /**
     * 和{@link com.welph.leecode.part_1_500.part_141_160.Solution146}LRU类似
     * ----大节点包含小节点, 一层一层的往后顺
     */
    static class LFUCache {

        Map<Integer, Entry> cache;
        int size = 0;
        int capacity;
        Node head;
        Node tail;

        public LFUCache(int capacity) {
            this.head = this.tail = new Node(-1);
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
            upTime(entry);
            return entry.value;
        }

        public void put(int key, int value) {
            if (capacity == 0) {
                return;
            }
            Entry entry = cache.get(key);
            if (null == entry) {
                entry = new Entry(key, value);
                if (size >= capacity) {
                    Node pre = tail.pre;
                    Entry innerPre = pre.tail.pre.pre;
                    pre.tail.pre.next = null;
                    pre.tail.pre.pre = null;
                    cache.remove(pre.tail.pre.key);
                    pre.tail.pre = innerPre;
                    innerPre.next = pre.tail;
                    if (pre.head.next == pre.tail) {
                        pre.pre.next = pre.next;
                        pre.next.pre = pre.pre;
                    }
                } else {
                    size++;
                }
                upTime(entry);
                cache.put(key, entry);
            } else {
                entry.value = value;
                upTime(entry);
            }
        }

        private void upTime(Entry entry) {
            entry.refer++;
            boolean isNew = entry.root == null;
            if (!isNew) {
                entry.pre.next = entry.next;
                entry.next.pre = entry.pre;
            }
            if (isNew) {
                entry.root = tail;
            }
            Node pre = entry.root.pre;
            if (pre == head || pre.refer != entry.refer) { //说明是新节点
                pre = new Node(entry.refer);
                entry.root.pre.next = pre;
                pre.pre = entry.root.pre;
                pre.next = entry.root;
                entry.root.pre = pre;
            }
            Entry next = pre.head.next;
            pre.head.next = entry;
            entry.next = next;
            entry.pre = pre.head;
            next.pre = entry;
            if (!isNew && entry.root.head.next == entry.root.tail) {
                entry.root.pre.next = entry.root.next;
                entry.root.next.pre = entry.root.pre;
            }
            entry.root = pre;
        }

        class Node {
            Node pre;
            Node next;
            Entry head;
            Entry tail;
            int refer;

            public Node(int refer) {
                head = tail = new Entry();
                head.next = tail;
                tail.pre = head;
                this.refer = refer;
            }
        }

        class Entry {
            int key;
            int value;
            int refer;
            Entry pre;
            Entry next;
            Node root;

            public Entry() {
            }

            public Entry(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }
}
