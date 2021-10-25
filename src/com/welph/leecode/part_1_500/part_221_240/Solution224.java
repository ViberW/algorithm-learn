package com.welph.leecode.part_1_500.part_221_240;

import java.util.Stack;

/**
 * 实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。
 * <p>
 * 示例 1：
 * 输入：s = "1 + 1"
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 * <p>
 * 示例 3：
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 * <p>
 * 提示：
 * 1 <= s.length <= 3 * 105
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 */
public class Solution224 {

    public static void main(String[] args) {
       // System.out.println(calculate("1 + 1"));
        System.out.println(calculate("2-1 + 2"));
       // System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));
    }

    //解析 并使用栈
    public static int calculate(String s) {
        Stack<Integer> sign = new Stack<>();
        sign.push(1);
        int ans = 0, num = 0, op = 1;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == ' ') continue;
            if ('0' <= c && c <= '9') {
                num = num * 10 + (c - '0');
                continue;
            }
            ans += op * num;
            num = 0;
            if (c == '+') {
                op = sign.peek();
            } else if (c == '-') {
                op = -sign.peek();
            } else if (c == '(') {
                sign.push(op);
            } else if (c == ')') {
                sign.pop();
            }
        }
        ans += op * num;
        return ans;
    }

}
