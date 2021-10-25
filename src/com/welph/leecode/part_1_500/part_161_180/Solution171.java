package com.welph.leecode.part_1_500.part_161_180;

/**
 * 给定一个Excel表格中的列名称，返回其相应的列序号。
 * 例如，
 * .    A -> 1
 * .    B -> 2
 * .    C -> 3
 * .    ...
 * .    Z -> 26
 * .    AA -> 27
 * .    AB -> 28
 * .    ...
 * 示例 1:
 * <p>
 * 输入: "A"
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: "AB"
 * 输出: 28
 * 示例 3:
 * <p>
 * 输入: "ZY"
 * 输出: 701
 */
public class Solution171 {

    public static void main(String[] args) {
        System.out.println(titleToNumber("ZY"));
    }

    /**
     * {@link Solution168}
     */
    public static int titleToNumber(String s) {
        int val = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            val *= 26;
            char c = s.charAt(i);
            val += c - 'A' + 1;
        }
        return val;
    }
}
