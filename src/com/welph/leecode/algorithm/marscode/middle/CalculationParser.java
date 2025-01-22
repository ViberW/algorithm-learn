package com.welph.leecode.algorithm.marscode.middle;

import java.util.*;

/**
 * 问题描述
 * 小F面临一个编程挑战：实现一个基本的计算器来计算简单的字符串表达式的值。
 * 该字符串表达式有效，并可能包含数字（0-9）、运算符+、-及括号()。
 * 注意，字符串中不包含空格。除法运算应只保留整数结果。
 *
 * 请实现一个解析器计算这些表达式的值，且不使用任何内置的eval函数。
 */
public class CalculationParser {

    static int end = 0;

    public static int solution(String expression) {
        // Please write your code here
        end = 0;
        char[] chars = expression.toCharArray();
        return solution(chars, 0, expression.length());
    }

    public static int solution(char[] chars, int l, int r) {
        Deque<Integer> deque = new ArrayDeque<>();
        int num;
        char preSign = '+';
        for (int i = l; i < r; i++) {
            if (chars[i] == '(') {
                num = solution(chars, i + 1, r);
                i = end;
            } else if (chars[i] == ')') {
                end = i;
                break;
            } else if (chars[i] >= '0' && chars[i] <= '9') {
                num = chars[i] - '0';
            } else {
                preSign = chars[i];
                continue;
            }
            if (preSign == '+') {
                deque.push(num);
            } else if (preSign == '-') {
                deque.push(-num);
            } else if (preSign == '*') {
                deque.push(deque.pop() * num);
            } else if (preSign == '/') {
                deque.push(deque.pop() / num);
            }
        }
        int ans = 0;
        while (!deque.isEmpty()) {
            ans += deque.pop();
        }
        return ans;
    }


    public static void main(String[] args) {
        // You can add more test cases here
        System.out.println(solution("1+1") == 2);
        System.out.println(solution("3+4*5/(3+2)") == 7);
        System.out.println(solution("4+2*5-2/1") == 12);
        System.out.println(solution("(1+(4+5+2)-3)+(6+8)") == 23);
    }
}
