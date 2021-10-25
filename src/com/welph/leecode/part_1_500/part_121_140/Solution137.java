package com.welph.leecode.part_1_500.part_121_140;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * 示例 1:
 * 输入: [2,2,3,2]
 * 输出: 3
 * 示例2:
 * <p>
 * 输入: [0,1,0,1,0,1,99]
 * 输出: 99
 */
public class Solution137 {

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 1, 0, 1, 99};
        System.out.println(singleNumber(nums));
    }

    /**
     * 从{@link Solution136} todo 位运算 值得回味
     * -- 巧妙 使用两个值保存
     * 0 ^ x = x,
     * x ^ x = 0；
     * x & ~x = 0,
     * x & ~0 =x;
     */
    public static int singleNumber(int[] nums) {
        int a = 0;  //用于保存额外多出来的一值
        int b = 0;  //目标值
        for (int num : nums) {
            b = (b ^ num) & ~a; //若是之前的多出来的一值 没有匹配,则等同于b ^ num
            a = (a ^ num) & ~b;
        }
        return b;
    }
}
