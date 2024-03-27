package com.welph.leecode.part_1_500.part_461_480;

/**
 * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
 * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
 * <p>
 * 示例 1：
 * 输入：x = 1, y = 4
 * 输出：2
 * 解释：
 * > 1 (0 0 0 1)
 * > 4 (0 1 0 0)
 * > ↑ ↑
 * 上面的箭头指出了对应二进制位不同的位置。
 * <p>
 * 示例 2：
 * 输入：x = 3, y = 1
 * 输出：1
 * <p>
 * 提示：
 * 0 <=x, y <= 2^31 - 1
 */
public class Solution461 {

    public static void main(String[] args) {
        System.out.println(hammingDistance(1, 4));
        System.out.println(hammingDistance(3, 1));
    }

    public static int hammingDistance(int x, int y) {
        // 逐步的往后移动
        int ret = 0;
        for (int i = 1; i <= 31; i++) {
            if ((x & 1) != (y & 1)) {
                ret++;
            }
            x >>>= 1;
            y >>>= 1;
        }
        return ret;
    }

    public int hammingDistance2(int x, int y) {
        int s = x ^ y, ret = 0;
        while (s != 0) {
            s &= s - 1; // 如101110&(101101)=101100 相当于是每次减少右边的不同值
            ret++;
        }
        return ret;
    }
}
