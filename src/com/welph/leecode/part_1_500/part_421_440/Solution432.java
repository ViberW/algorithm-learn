package com.welph.leecode.part_1_500.part_421_440;

import java.util.*;

/**
 * 请你实现一个数据结构支持以下操作：
 * <p>
 * Inc(key) - 插入一个新的值为 1 的 key。或者使一个存在的 key 增加一，保证 key 不为空字符串。
 * Dec(key) - 如果这个 key 的值是 1，那么把他从数据结构中移除掉。否则使一个存在的 key 值减一。
 * --- 如果这个 key 不存在，这个函数不做任何事情。key 保证不为空字符串。
 * GetMaxKey() - 返回 key 中值最大的任意一个。如果没有元素存在，返回一个空字符串"" 。
 * GetMinKey() - 返回 key 中值最小的任意一个。如果没有元素存在，返回一个空字符串""。
 * <p>
 * 挑战：
 * 你能够以 O(1) 的时间复杂度实现所有操作吗？
 */
public class Solution432 {

    public static void main(String[] args) {
        AllOne obj = new AllOne();
        obj.inc("hello");
        obj.inc("hello");
        obj.inc("leet");
        System.out.println(obj.getMaxKey());
        System.out.println(obj.getMinKey());
        // ["AllOne","inc","inc","getMaxKey","getMinKey","inc","getMaxKey","getMinKey"]
        // [[],["hello"],["hello"],[],[],["leet"],[],[]]

    }

    static class AllOne {

        Map<String, Integer> map = new HashMap<>();
        TreeMap<Integer, Node> nodes = new TreeMap<>();

        public AllOne() {
        }

        public void inc(String key) {
            int val = map.getOrDefault(key, 0);
            Node node = nodes.computeIfAbsent(val + 1, Node::new);
            node.list.add(key);
            Node origin = nodes.get(val);
            if (null != origin) {
                origin.list.remove(key);
                if (origin.list.isEmpty()) {
                    nodes.remove(val);
                }
            }
            map.put(key, val + 1);
        }

        public void dec(String key) {
            Integer val = map.get(key);
            if (val == null) {
                return;
            }
            if (val > 1) {
                Node node = nodes.computeIfAbsent(val - 1, Node::new);
                node.list.add(key);
            }
            Node origin = nodes.get(val);
            origin.list.remove(key);
            if (origin.list.isEmpty()) {
                nodes.remove(val);
            }
            map.put(key, val - 1);
        }

        public String getMaxKey() {
            Map.Entry<Integer, Node> entry = nodes.lastEntry();
            if (null == entry) {
                return "";
            }
            return entry.getValue().list.iterator().next();
        }

        public String getMinKey() {
            Map.Entry<Integer, Node> entry = nodes.firstEntry();
            if (null == entry) {
                return "";
            }
            return entry.getValue().list.iterator().next();
        }

        static class Node {
            int val = 0;

            public Node(int val) {
                this.val = val;
            }

            Set<String> list = new HashSet<>();
        }
    }

    /* 官方题解 */
    // 双向链表+哈希表
    /*
     * 思路和我的方法差不多, 但是因为自实现了node链表, 相较于我的treemap效率高, 少了找node的时间
     */
    class AllOne2 {
        Node root;
        Map<String, Node> nodes;

        public AllOne2() {
            root = new Node();
            root.prev = root;
            root.next = root; // 初始化链表哨兵，下面判断节点的 next 若为 root，则表示 next 为空（prev 同理）
            nodes = new HashMap<String, Node>();
        }

        public void inc(String key) {
            if (nodes.containsKey(key)) {
                // 获取当前key存在的node
                Node cur = nodes.get(key);
                Node nxt = cur.next;
                // 若cur的最后为root或者数量大于cur+1,则说明要新插入一个节点
                if (nxt == root || nxt.count > cur.count + 1) {
                    nodes.put(key, cur.insert(new Node(key, cur.count + 1)));
                } else {
                    nxt.keys.add(key);
                    nodes.put(key, nxt);
                }
                // cur移除key,若为空则删除node
                cur.keys.remove(key);
                if (cur.keys.isEmpty()) {
                    cur.remove();
                }
            } else { // key 不在链表中
                if (root.next == root || root.next.count > 1) {
                    // 若当天链表为空或最近的一个node数量已经是大于1的集合, 则插入一个数量为1的集合node
                    nodes.put(key, root.insert(new Node(key, 1)));
                } else {
                    // 最近的就是数量为1的集合node
                    root.next.keys.add(key);
                    // 标记key存在的node
                    nodes.put(key, root.next);
                }
            }
        }

        public void dec(String key) {
            Node cur = nodes.get(key);
            if (cur.count == 1) { // key 仅出现一次，将其移出 nodes
                nodes.remove(key);
            } else {
                Node pre = cur.prev;
                // 前一个node为root或者前数量小于cur-1,则说明要新插入一个新节点node
                if (pre == root || pre.count < cur.count - 1) {
                    nodes.put(key, cur.prev.insert(new Node(key, cur.count - 1)));
                } else {
                    pre.keys.add(key);
                    nodes.put(key, pre);
                }
            }
            // cur移除key,若为空则删除node
            cur.keys.remove(key);
            if (cur.keys.isEmpty()) {
                cur.remove();
            }
        }

        public String getMaxKey() {
            // 循环node链表, 则prev为最大值
            return root.prev != null ? root.prev.keys.iterator().next() : "";
        }

        public String getMinKey() {
            // 循环node链表, 则next为最小值
            return root.next != null ? root.next.keys.iterator().next() : "";
        }
    }

    class Node {
        Node prev;
        Node next;
        Set<String> keys;
        int count;

        public Node() {
            this("", 0);
        }

        public Node(String key, int count) {
            this.count = count;
            keys = new HashSet<String>();
            keys.add(key);
        }

        public Node insert(Node node) { // 在 this 后插入 node
            node.prev = this;
            node.next = this.next;
            node.prev.next = node;
            node.next.prev = node;
            return node;
        }

        public void remove() {
            this.prev.next = this.next;
            this.next.prev = this.prev;
        }
    }

}
