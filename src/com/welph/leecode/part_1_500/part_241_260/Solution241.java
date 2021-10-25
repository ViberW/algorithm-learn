package com.welph.leecode.part_1_500.part_241_260;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。
 * 你需要给出所有可能的组合的结果。有效的运算符号包含 +,  -  以及  *  。
 * <p>
 * 示例  1:
 * 输入: "2-1-1"
 * 输出: [0, 2]
 * 解释:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * <p>
 * 示例  2:
 * 输入: "2*3-4*5"
 * 输出: [-34, -14, -10, -10, 10]
 * 解释:
 * 2*(3-(4*5)) = -34
 * (2*3)-(4*5) = -14
 * (2*(3-4))*5 = -10
 * 2*((3-4)*5) = -10
 * ((2*3)-4)*5 = 10
 */
public class Solution241 {

    public static void main(String[] args) {
        // System.out.println(diffWaysToCompute("2-1-1"));
        System.out.println(diffWaysToCompute("2*3-4*5"));
    }

    //+ - *
    //分治算法  假定每个运算符的前后都是合法的()
    public static List<Integer> diffWaysToCompute(String expression) {
        return compute(expression.toCharArray(), 0, expression.length());
    }

    public static List<Integer> compute(char[] chars, int start, int end) {
        List<Integer> res = new ArrayList<>();
        char c;
        int total = 0;
        boolean allNum = true;
        for (int i = start; i < end; i++) {
            c = chars[i];
            if ('+' == c || '-' == c || '*' == c) {
                allNum = false;
                List<Integer> l = compute(chars, start, i);
                List<Integer> r = compute(chars, i + 1, end);
                for (Integer ll : l) {
                    for (Integer rr : r) {
                        if ('+' == c) {
                            res.add(ll + rr);
                        } else if ('-' == c) {
                            res.add(ll - rr);
                        } else {
                            res.add(ll * rr);
                        }
                    }
                }
            } else if (allNum) {
                total = 10 * total + (c - '0');
            }
        }
        if (allNum) {
            res.add(total);
        }
        return res;
    }
}
