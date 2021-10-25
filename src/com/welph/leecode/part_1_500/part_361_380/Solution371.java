package com.welph.leecode.part_1_500.part_361_380;

/**
 * 不使用运算符 + 和 - ，计算两整数 a 、b 之和。
 * <p>
 * 示例 1:
 * <p>
 * 输入: a = 1, b = 2
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: a = -2, b = 3
 * 输出: 1
 */
public class Solution371 {

    public static void main(String[] args) {
        System.out.println(getSum(1, 2));
    }

    public static int getSum(int a, int b) {
        int sum = (a ^ b); //不相同的位数
        int c = (a & b) << 1;//相同的位数, 相加进位
        if (c != 0) {
            return getSum(sum, c);
        }
        return sum;
    }
}
