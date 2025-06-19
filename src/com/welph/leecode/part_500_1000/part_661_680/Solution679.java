package com.welph.leecode.part_500_1000.part_661_680;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个长度为4的整数数组 cards 。你有 4 张卡片，每张卡片上都包含一个范围在 [1,9] 的数字。
 * 您应该使用运算符 ['+', '-', '*', '/'] 和括号 '(' 和 ')' 将这些卡片上的数字排列成数学表达式，以获得值24。
 *
 * 你须遵守以下规则:
 * 除法运算符 '/' 表示实数除法，而不是整数除法。
 * 例如， 4 /(1 - 2 / 3)= 4 /(1 / 3)= 12 。
 * 每个运算都在两个数字之间。特别是，不能使用 “-” 作为一元运算符。
 * 例如，如果 cards =[1,1,1,1] ，则表达式 “-1 -1 -1 -1” 是 不允许 的。
 * 你不能把数字串在一起
 * 例如，如果 cards =[1,2,1,2] ，则表达式 “12 + 12” 无效。
 * 如果可以得到这样的表达式，其计算结果为 24 ，则返回 true ，否则返回 false 。
 *
 * 示例 1:
 * 输入: cards = [4, 1, 8, 7]
 * 输出: true
 * 解释: (8-4) * (7-1) = 24
 *
 * 示例 2:
 * 输入: cards = [1, 2, 1, 2]
 * 输出: false
 *
 * 提示:
 * cards.length == 4
 * 1 <= cards[i] <= 9
 */
public class Solution679 {

    public static void main(String[] args) {
        System.out.println(judgePoint24(new int[]{4, 1, 8, 7}));
        System.out.println(judgePoint24(new int[]{1, 2, 1, 2}));
    }

    //官方题解
    static final int TARGET = 24;
    static final double EPSILON = 1e-6;
    static final int ADD = 0, MULTIPLY = 1, SUBTRACT = 2, DIVIDE = 3;

    //回溯法, 遍历所有的可能情况
    public static boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>();
        for (int num : nums) {
            list.add((double) num);
        }
        return solve(list);
    }

    //先从4个数字选择2个. 共12种选法, 通过加减乘除计算出1个数, 这样就剩余3个数
    //从3个数中选择2个, 共6种选法, 通过加减乘除计算出1个数, 这样剩余2个数
    ///最后两个数通过加减乘除计算最终结果
    //========= 加法和乘法都满足交换律 所以这种情况要跳过
    public static boolean solve(List<Double> list) {
        if (list.isEmpty()) {
            return false;
        }
        if (list.size() == 1) {
            return Math.abs(list.get(0) - TARGET) < EPSILON;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    List<Double> list2 = new ArrayList<>();
                    for (int k = 0; k < size; k++) {
                        if (k != i && k != j) {
                            list2.add(list.get(k));
                        }
                    }
                    for (int k = 0; k < 4; k++) {
                        if (k < 2 && i > j) { //跳过加乘的交换律问题
                            continue;
                        }
                        if (k == ADD) {
                            list2.add(list.get(i) + list.get(j));
                        } else if (k == MULTIPLY) {
                            list2.add(list.get(i) * list.get(j));
                        } else if (k == SUBTRACT) {
                            list2.add(list.get(i) - list.get(j));
                        } else if (k == DIVIDE) {
                            if (Math.abs(list.get(j)) < EPSILON) {
                                continue;
                            } else {
                                list2.add(list.get(i) / list.get(j));
                            }
                        }
                        if (solve(list2)) {//3个数字. 2个数字
                            return true;
                        }
                        list2.remove(list2.size() - 1);
                    }
                }
            }
        }
        return false;
    }
}
