package com.welph.leecode.part_201_220;

/**
 * 给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。
 * <p>
 * 示例 1:
 * 输入: [5,7]
 * 输出: 4
 * <p>
 * 示例 2:
 * 输入: [0,1]
 * 输出: 0
 */
public class Solution201 {

    public static void main(String[] args) {
        System.out.println(rangeBitwiseAnd(5, 7));
    }

    //从m到n   m+1, m+2 ... n  所有数字的与
    //只需要考虑到.最小位的  -- x&(x+1) 最大前缀
    public static int rangeBitwiseAnd(int m, int n) {
        int offset = 0;
        for (; m != n; ++offset) {
            m = m >> 1;
            n = n >> 1;
        }
        return n << offset;
    }
}
