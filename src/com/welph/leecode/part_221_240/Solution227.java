package com.welph.leecode.part_221_240;

import java.util.Stack;

/**
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格。 整数除法仅保留整数部分。
 * <p>
 * 示例1:
 * 输入: "3+2*2"
 * 输出: 7
 * <p>
 * 示例 2:
 * 输入: " 3/2 "
 * 输出: 1
 * <p>
 * 示例 3:
 * 输入: " 3+5 / 2 "
 * 输出: 5
 * <p>
 * 说明：
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 */
public class Solution227 {

    public static void main(String[] args) {
        System.out.println(calculate("3+2*2"));
        System.out.println(calculate(" 3/2 "));
        System.out.println(calculate(" 3+5 / 2 "));
        System.out.println(calculate(" 0*1 "));
    }

    //先乘除 再加减
    public static int calculate(String s) {
        char[] chars = s.toCharArray();
        int ans = 0, num = 0, op = 1;
        Integer pre = null;
        for (char c : chars) {
            if (c == ' ') continue;
            if ('0' <= c && c <= '9') {
                num = num * 10 + (c - '0');
                continue;
            }
            if (pre != null) {
                num = pre >= 0 ? pre * num : -pre / num;
            }
            switch (c) {
                case '+':
                case '-':
                    ans += op * num;
                    pre = null;
                    op = c == '+' ? 1 : -1;
                    break;
                case '*':
                    pre = num;
                    break;
                case '/':
                    pre = -num;
                    break;
            }
            num = 0;
        }
        if (pre != null) {
            num = pre >= 0 ? pre * num : -pre / num;
        }
        ans += op * num;
        return ans;
    }
}
