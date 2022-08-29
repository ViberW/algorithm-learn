package com.welph.leecode.part_1_500.part_461_480;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两个整数的汉明距离 指的是这两个数字的二进制数对应位不同的数量。
 * 给你一个整数数组 nums，请你计算并返回 nums 中任意两个数之间 汉明距离的总和 。
 * <p>
 * 示例 1：
 * 输入：nums = [4,14,2]
 * 输出：6
 * 解释：在二进制表示中，4 表示为 0100 ，14 表示为 1110 ，2表示为 0010 。（这样表示是为了体现后四位之间关系）
 * 所以答案为：
 * HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6
 * <p>
 * 示例 2：
 * 输入：nums = [4,14,4]
 * 输出：4
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^4
 * 0 <= nums[i] <= 10^9
 * 给定输入的对应答案符合 32-bit 整数范围
 */
public class Solution477 {

    public static void main(String[] args) {
//        System.out.println(totalHammingDistance(new int[]{4, 14, 2}));
        System.out.println(totalHammingDistance(new int[]{4, 14, 4, 14}));
    }

    /**
     * {@link Solution461}
     * 每一位进行一次比较
     */
    public static int totalHammingDistance(int[] nums) {
        //需要设置一个缓存信息
        int length = nums.length;
        //进行排序
        int[] c = {0, 0};
        int total = 0;
        for (int i = 0; i < 31; i++) {
            c[0] = 0;
            c[1] = 0;
            for (int j = 0; j < length; j++) {
                //统计每个位数的额
                c[nums[j] & 1]++;
                nums[j] >>= 1;
            }
            total += c[0] * c[1];
        }
        return total;
    }

}
