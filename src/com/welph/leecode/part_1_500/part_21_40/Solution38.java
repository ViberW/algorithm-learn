package com.welph.leecode.part_1_500.part_21_40;

import java.util.Stack;

/**
 * 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
 * <p>
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 * <p>
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。
 * <p>
 * 注意：整数顺序将表示为一个字符串。
 */
public class Solution38 {

    public static void main(String[] args) {
        System.out.println(countAndSay(5));
    }

    public static String countAndSay(int n) {
        StringBuilder s = new StringBuilder("1");
        Stack<Character> stack = new Stack<>();
        StringBuilder sb;
        while (--n > 0) {
            sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (!stack.isEmpty() && stack.peek() != s.charAt(i)) {
                    sb.append(stack.size()).append(stack.peek());
                    stack.clear();
                }
                stack.push(s.charAt(i));
            }
            if (!stack.isEmpty()) {
                sb.append(stack.size()).append(stack.peek());
            }
            stack.clear();
            s = sb;
        }
        return s.toString();
    }
}
