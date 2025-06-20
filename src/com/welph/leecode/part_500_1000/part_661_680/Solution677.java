package com.welph.leecode.part_500_1000.part_661_680;

import java.util.HashMap;
import java.util.Map;

/**
 * 设计一个 map ，满足以下几点:
 *
 * 字符串表示键，整数表示值
 * 返回具有前缀等于给定字符串的键的值的总和
 * 实现一个 MapSum 类：
 * MapSum() 初始化 MapSum 对象
 * void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，
 * 整数表示值 val 。如果键 key 已经存在，那么原来的键值对 key-value 将被替代成新的键值对。
 * int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
 *
 * 示例 1：
 * 输入：
 * ["MapSum", "insert", "sum", "insert", "sum"]
 * [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
 * 输出：
 * [null, null, 3, null, 5]
 * 解释：
 * MapSum mapSum = new MapSum();
 * mapSum.insert("apple", 3);
 * mapSum.sum("ap");           // 返回 3 (apple = 3)
 * mapSum.insert("app", 2);
 * mapSum.sum("ap");           // 返回 5 (apple + app = 3 + 2 = 5)
 *
 * 提示：
 * 1 <= key.length, prefix.length <= 50
 * key 和 prefix 仅由小写英文字母组成
 * 1 <= val <= 1000
 * 最多调用 50 次 insert 和 sum
 */
public class Solution677 {

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        System.out.println(mapSum.sum("ap"));// 返回 3 (apple = 3)
        mapSum.insert("app", 2);
        System.out.println(mapSum.sum("ap"));// 返回 5 (apple + app = 3 + 2 = 5)
    }

    static class MapSum {
        TrieNode root;
        Map<String, Integer> map;

        public MapSum() {
            root = new TrieNode();
            map = new HashMap<>();
        }

        public void insert(String key, int val) {
            Integer lastValue = map.getOrDefault(key, 0);
            map.put(key, val);
            val -= lastValue;
            TrieNode curr = root;
            for (int i = 0; i < key.length(); i++) {
                int index = key.charAt(i) - 'a';
                if (curr.children[index] == null) {
                    curr.children[index] = new TrieNode();
                }
                curr = curr.children[index];
                curr.sum += val;
            }
        }

        public int sum(String prefix) {
            TrieNode curr = root;
            for (int i = 0; i < prefix.length(); i++) {
                int index = prefix.charAt(i) - 'a';
                if (curr.children[index] == null) {
                    return 0;
                }
                curr = curr.children[index];
            }
            return curr.sum;
        }

        public static class TrieNode {
            public TrieNode[] children = new TrieNode[26];
            public int sum = 0;
        }
    }
}
