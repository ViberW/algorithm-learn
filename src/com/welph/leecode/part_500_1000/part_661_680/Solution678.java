package com.welph.leecode.part_500_1000.part_661_680;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你一个只包含三种字符的字符串，支持的字符类型分别是 '('、')' 和 '*'。
 * 请你检验这个字符串是否为有效字符串，如果是 有效 字符串返回 true 。
 *
 * 有效 字符串符合如下规则：
 * 任何左括号 '(' 必须有相应的右括号 ')'。
 * 任何右括号 ')' 必须有相应的左括号 '(' 。
 * 左括号 '(' 必须在对应的右括号之前 ')'。
 * '*' 可以被视为单个右括号 ')' ，或单个左括号 '(' ，或一个空字符串 ""。
 *
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 *
 * 示例 2：
 * 输入：s = "(*)"
 * 输出：true
 *
 * 示例 3：
 * 输入：s = "(*))"
 * 输出：true
 *
 * 提示：
 * 1 <= s.length <= 100
 * s[i] 为 '('、')' 或 '*'
 */
public class Solution678 {

    public static void main(String[] args) {
        System.out.println(checkValidString1("()"));
        System.out.println(checkValidString1("(*)"));
        System.out.println(checkValidString1("(*))"));
    }

    //贪心
    public static boolean checkValidString(String s) {
        Deque<Integer> left = new LinkedList<>();
        Deque<Integer> anti = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                left.push(i);
            } else if (c == ')') {
                if (!left.isEmpty()) {
                    left.pop();
                } else if (!anti.isEmpty()) {
                    anti.pop();
                } else {
                    return false;
                }
            } else {
                anti.push(i);
            }
        }
        //根据left和anti的位置判断
        while (!left.isEmpty() && !anti.isEmpty()) {
            if (left.pop() > anti.pop()) {
                return false;
            }
        }
        return left.isEmpty();
    }


    //动态规划
    public static boolean checkValidString1(String s) {
        //dp[l][r] 代表l到r范围内是否为合法的
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '*') {
                dp[i][i] = true;
            }
        }
        for (int r = 1; r < n; r++) {
            char rc = s.charAt(r);
            for (int l = r - 1; l >= 0; l--) {
                char lc = s.charAt(l);
                if ((rc == ')' || rc == '*') && (lc == '(' || lc == '*')) {
                    dp[l][r] = r - l <= 1 || dp[l + 1][r - 1];
                }
                for (int k = l; k < r && !dp[l][r]; k++) {
                    dp[l][r] = dp[l][k] && dp[k + 1][r];
                }
            }
        }
        return dp[0][n - 1];
    }

    //官方题解: 贪心
    public static boolean checkValidString2(String s) {
        //左括号的最小数量.  左括号的最大数量
        int minCount = 0, maxCount = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                minCount++;
                maxCount++;
            } else if (c == ')') {
                minCount = Math.max(minCount - 1, 0);
                maxCount--;
                if (maxCount < 0) { //最大的也不能够匹配完
                    return false;
                }
            } else {
                minCount = Math.max(minCount - 1, 0);  //*当作)或者空字符串
                maxCount++;  //把*当作(
            }
        }
        return minCount == 0;
    }
}
