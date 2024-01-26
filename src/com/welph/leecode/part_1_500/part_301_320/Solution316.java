package com.welph.leecode.part_1_500.part_301_320;

import java.util.*;

/**
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * 注意：该题与 1081
 * https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters
 * 相同
 * <p>
 * 示例 1：
 * 输入：s = "bcabc"
 * 输出："abc"
 * <p>
 * 示例 2：
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 104
 * s 由小写英文字母组成
 */
public class Solution316 {

    public static void main(String[] args) {
        // System.out.println(removeDuplicateLetters("cbacdcbc"));
        System.out.println(removeDuplicateLetters("ecbacba"));
    }

    // 因为字典序列要最低. 越大的字符则越靠后
    // 使用栈, 若不存在, 则判断栈顶是否大于当前值, 且后续是否存在栈顶的元素,则抛弃栈顶元素,添加当期元素
    public static String removeDuplicateLetters(String s) {
        int length = s.length();
        // 预先处理字符
        Map<Character, Integer> map = new HashMap<>();
        char c;
        for (int i = length - 1; i >= 0; i--) {
            c = s.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, i);
            }
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            c = s.charAt(i);
            if (stack.contains(c)) {
                continue;
            }
            while (!stack.isEmpty() && stack.peek() > c && map.get(stack.peek()) > i) {
                stack.pop();// 后续还有
            }
            stack.push(c);
        }
        StringBuilder builder = new StringBuilder();
        for (Character character : stack) {
            builder.append(character);
        }
        return builder.toString();
    }

    /* 官方题解 也是贪心+单调栈 */
    // 写法更好, 同样的思路
    public String removeDuplicateLetters2(String s) {
        boolean[] vis = new boolean[26];
        int[] num = new int[26];
        for (int i = 0; i < s.length(); i++) {
            num[s.charAt(i) - 'a']++;
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!vis[ch - 'a']) {
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) > ch) {
                    if (num[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                        vis[sb.charAt(sb.length() - 1) - 'a'] = false;
                        sb.deleteCharAt(sb.length() - 1);
                    } else {
                        break;
                    }
                }
                vis[ch - 'a'] = true;
                sb.append(ch);
            }
            num[ch - 'a'] -= 1;
        }
        return sb.toString();
    }

}
