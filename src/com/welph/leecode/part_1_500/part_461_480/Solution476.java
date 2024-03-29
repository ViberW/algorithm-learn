package com.welph.leecode.part_1_500.part_461_480;

/**
 * 对整数的二进制表示取反（0 变 1 ，1 变 0）后，再转换为十进制表示，可以得到这个整数的补数。
 * <p>
 * 例如，整数 5 的二进制表示是 "101" ，取反后得到 "010" ，再转回十进制表示得到补数 2 。
 * 给你一个整数 num ，输出它的补数。
 * <p>
 * 示例 1：
 * 输入：num = 5
 * 输出：2
 * 解释：5 的二进制表示为 101（没有前导零位），其补数为 010。所以你需要输出 2 。
 * <p>
 * 示例 2：
 * 输入：num = 1
 * 输出：0
 * 解释：1 的二进制表示为 1（没有前导零位），其补数为 0。所以你需要输出 0 。
 * <p>
 * 提示：
 * 1 <= num < 2^31
 */
public class Solution476 {

    public static void main(String[] args) {
        System.out.println(findComplement(8));
        System.out.println(findComplement(5));
        System.out.println(findComplement(1));
    }

    /**
     * 异或
     * 5: 101 ^ 111 010
     * 但需要找到离5最近的数值. 这里考虑到是正整数, 所以用一个负数的值.
     */
    public static int findComplement(int num) {
        // 不断补全相关的值
        int high = 0;
        for (int i = 0; i <= 30; i++) {
            if (num >= 1 << i) {
                high = i;
            } else {
                break;
            }
        }
        // 找到最大的关联数值后
        int val = high == 30 ? 0x7fffffff : (1 << (high + 1)) - 1;
        return val ^ num;
    }

    // 另一种位运算;
     // {@link HashMap.tableSizeFor()}
    int findComplement2(int num) {
        int t = num;
        t |= t >> 1;
        t |= t >> 2;
        t |= t >> 4;
        t |= t >> 8;
        t |= t >> 16;
        //到目前为止 找到了num的最大ffff的值,如num最开始=0b_1000_0000, 那么最终t为0b_1111_1111
        return ~num & t;
    }

}
