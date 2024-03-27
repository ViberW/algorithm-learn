package com.welph.leecode.part_1_500.part_401_420;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你一个以字符串表示的非负整数num 和一个整数 k ，移除这个数中的 k 位数字，
 * 使得剩下的数字最小。请你以字符串形式返回这个最小的数字。
 * <p>
 * <p>
 * 示例 1 ：
 * 输入：num = "1432219", k = 3
 * 输出："1219"
 * 解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。
 * <p>
 * 示例 2 ：
 * 输入：num = "10200", k = 1
 * 输出："200"
 * 解释：移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * <p>
 * 示例 3 ：
 * 输入：num = "10", k = 2
 * 输出："0"
 * 解释：从原数字移除所有的数字，剩余为空就是 0 。
 * <p>
 * 提示：
 * 1 <= k <= num.length <= 105
 * num 仅由若干位数字（0 - 9）组成
 * 除了 0 本身之外，num 不含任何前导零
 */
public class Solution402 {

    public static void main(String[] args) {
        // System.out.println(removeKdigits("1432219", 3));
        System.out.println(removeKdigits("132", 3));
        // System.out.println(removeKdigits("1000", 1));
        // System.out.println(removeKdigits("10200", 1));
    }

    /**
     * {@link com.welph.leecode.algorithm.greedy.MoveK}
     * i比i+1大则移除
     */
    public static String removeKdigits(String num, int k) {
        char[] chars = num.toCharArray();
        int limit = chars.length - 1;
        char ch;
        LABEL: while (k-- > 0) {
            for (int i = 0; i < limit; i++) {
                ch = chars[i];
                if (ch > chars[i + 1]) {
                    System.arraycopy(chars, i + 1, chars, i, limit - i);
                    limit--;
                    continue LABEL;
                }
            }
            limit--;
        }
        // 解决10200 => 0200的问题
        int i = 0;
        while (i <= limit && chars[i] == '0') {
            i++;
        }
        if (i > limit) {
            return "0";
        }
        StringBuilder builder = new StringBuilder();
        for (; i <= limit; i++) {
            builder.append(chars[i]);
        }
        return builder.toString();
    }

    /* 官方题解 */

    // 贪心+单调栈
    public String removeKdigits2(String num, int k) {
        Deque<Character> deque = new LinkedList<Character>(); // 使用单调栈的方式比每次遍历好 省的移动数组
        int length = num.length();
        for (int i = 0; i < length; ++i) {
            char digit = num.charAt(i);
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > digit) {
                deque.pollLast();
                k--;
            }
            deque.offerLast(digit);
        }

        for (int i = 0; i < k; ++i) {
            deque.pollLast();
        }

        StringBuilder ret = new StringBuilder();
        boolean leadingZero = true;
        while (!deque.isEmpty()) {
            char digit = deque.pollFirst();
            if (leadingZero && digit == '0') {
                continue;
            }
            leadingZero = false;
            ret.append(digit);
        }
        return ret.length() == 0 ? "0" : ret.toString();
    }
}
