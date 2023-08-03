package com.welph.leecode.part_1_500.part_21_40;

import java.util.Stack;

/**
 * 给定一个只包含 '('和 ')'的字符串，找出最长的包含有效括号的子串的长度。
 * <p>
 * 示例1:
 * <p>
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 * <p>
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 * <p>
 */
public class Solution32 {

    public static void main(String[] args) {
        System.out.println(longestValidParentheses("(()"));//2
        System.out.println(longestValidParentheses2(")()())"));//4
        System.out.println(longestValidParentheses("()((())()"));//6
    }

    /**
     * 关于这个算法，最初版本执行速度太慢。
     * 之后看了题解 发现由于开始只有从左到右的获取最大值，
     * 没考虑到之后再来一次的从右到左的操作，导致写出了不满足total=0，则start+1的从新遍历曹祖
     * <p>
     * 之后看了题解，直接加上从右到左，速度突变
     *
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {
        //首先要找到属于有效括号，左右相等的数量，并且左始终大于等于右
        char[] chars = s.toCharArray();
        int len = chars.length;
        int total;
        int max = 0;
        char ch;
        total = 0;
        for (int i = 0, start = 0; i < len; i++) {
            ch = chars[i];
            if ('(' == ch) {
                total++;
            } else {
                total--;
            }
            if (total == 0) {
                max = Math.max(i - start + 1, max);
            } else if (total < 0) {
                total = 0;
                start = i + 1;
            }
        }
        total = 0;
        for (int i = len - 1, start = len - 1; i >= 0; i--) {
            ch = chars[i];
            if (')' == ch) {
                total++;
            } else {
                total--;
            }
            if (total == 0) {
                max = Math.max(start - i + 1, max);
            } else if (total < 0) {
                total = 0;
                start = i - 1;
            }
        }

        return max;
    }

    /*********以下是依照题解思路写出的**********/
    public static int longestValidParentheses2(String s) {
        //使用动态规划方法
        /**
         * 以左括号 则为0
         * 以右括号 则需要看前一括号：
         *     若左括号： dp[i] = dp[i-2]+2
         *     若右括号，则需要看i-dp[i-1]-1位置：
         *          若左括号：dp[i] =  dp[i - 1] + dp[i-dp[i-1]-2] + 2
         *          若右括号：0；
         */
        char[] chars = s.toCharArray();
        int len = chars.length;
        int[] dp = new int[len];
        int max = 0;
        for (int i = 1; i < len; i++) {
            if (')' == chars[i]) {
                if ('(' == chars[i - 1]) {
                    dp[i] = dp[i - 2] + 2;
                } else if ('(' == chars[i - dp[i - 1] - 1]) {
                    dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2;
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }


    public static int longestValidParentheses3(String s) {
        //栈
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }
}
