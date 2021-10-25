package com.welph.leecode.part_1_500.part_381_400;

/**
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * <p>
 * 示例：
 * s = "leetcode"
 * 返回 0
 * <p>
 * s = "loveleetcode"
 * 返回 2
 * <p>
 * 提示：你可以假定该字符串只包含小写字母。
 */
public class Solution387 {

    public static void main(String[] args) {
        System.out.println(firstUniqChar("leetcode"));
        System.out.println(firstUniqChar("loveleetcode"));
    }

    public static int firstUniqChar(String s) {
        Node[] arr = new Node[26];
        Node n;
        int index;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            index = s.charAt(i) - 'a';
            n = arr[index];
            if (null == n) {
                n = new Node();
                n.index = i;
                arr[index] = n;
            }
            n.count++;
        }
        int target = len;
        for (Node node : arr) {
            if (node != null && node.count == 1) {
                target = Math.min(node.index, target);
            }
        }
        return target == len ? -1 : target;
    }

    static class Node {
        int count;
        int index;
    }
}
