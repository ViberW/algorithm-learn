package com.welph.leecode.part_1_500.part_1_20;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * @author: Admin
 * @date: 2019/5/16
 * @Description: {相关描述}
 */
public class Solution20 {

    public static void main(String[] args) {
        System.out.println(isValid("([)]"));
        System.out.println(isValid("()[]{}"));
    }

    public static boolean isValid(String s) {
        char[] right = new char[]{')', '}', ']'};
        Stack<Integer> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(0);
            } else if (ch == '{') {
                stack.push(1);
            } else if (ch == '[') {
                stack.push(2);
            } else {
                if (stack.isEmpty() || ch != right[stack.pop()]) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
