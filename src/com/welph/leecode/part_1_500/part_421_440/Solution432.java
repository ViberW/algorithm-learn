package com.welph.leecode.part_1_500.part_421_440;

import java.util.*;

/**
 * 请你实现一个数据结构支持以下操作：
 * <p>
 * Inc(key) - 插入一个新的值为 1 的 key。或者使一个存在的 key 增加一，保证 key 不为空字符串。
 * Dec(key) - 如果这个 key 的值是 1，那么把他从数据结构中移除掉。否则使一个存在的 key 值减一。
 * ---  如果这个 key 不存在，这个函数不做任何事情。key 保证不为空字符串。
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
        //["AllOne","inc","inc","getMaxKey","getMinKey","inc","getMaxKey","getMinKey"]
        //[[],["hello"],["hello"],[],[],["leet"],[],[]]

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
}
