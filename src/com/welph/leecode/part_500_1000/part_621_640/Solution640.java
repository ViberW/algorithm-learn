package com.welph.leecode.part_500_1000.part_621_640;

/**
 * 求解一个给定的方程，将x以字符串 "x=#value" 的形式返回。该方程仅包含 '+' ， '-' 操作，变量 x 和其对应系数。
 * 如果方程没有解或存在的解不为整数，请返回 "No solution" 。如果方程有无限解，则返回 “Infinite solutions” 。
 * 题目保证，如果方程中只有一个解，则 'x' 的值是一个整数。
 * <p>
 * 示例 1：
 * 输入: equation = "x+5-3+x=6+x-2"
 * 输出: "x=2"
 * <p>
 * 示例 2:
 * 输入: equation = "x=x"
 * 输出: "Infinite solutions"
 * <p>
 * 示例 3:
 * 输入: equation = "2x=x"
 * 输出: "x=0"
 * <p>
 * 提示:
 * 3 <= equation.length <= 1000
 * equation 只有一个 '='.
 * 方程由绝对值在 [0, 100]  范围内且无任何前导零的整数和变量 'x' 组成。
 */
public class Solution640 {

    public static void main(String[] args) {
        System.out.println(solveEquation("x+5-3+x=6+x-2"));
        System.out.println(solveEquation("x=x"));
        System.out.println(solveEquation("2x=x"));
        System.out.println(solveEquation("0x=0"));
    }

    public static String solveEquation(String equation) {
        char[] chars = equation.toCharArray();
        boolean left = true;
        int countX = 0;
        int val = 0;
        int cur = 0;
        boolean add = true;
        boolean hasCount = false;
        char c;
        for (int i = 0; i < chars.length; i++) {
            c = chars[i];
            if (c == '=' || c == '+' || c == '-') {
                val += add ? cur : -cur;
                if (c == '=') {
                    left = false;
                    add = false;
                } else if (c == '+') {
                    add = left;
                } else {
                    add = !left;
                }
                cur = 0;
                hasCount = false;
            } else if (c == 'x') {
                if (cur == 0 && !hasCount)
                    cur = 1;
                countX += add ? cur : -cur;
                cur = 0;
            } else {
                hasCount = true;
                cur = cur * 10 + (c - '0');
            }
        }
        val += add ? cur : -cur;
        return countX == 0 ? val != 0 ? "No solution" : "Infinite solutions" : "x=" + (-val / countX);
    }
}
