package com.welph.leecode.part_1_500.part_441_460;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums ，返回 nums 中所有 等差子序列 的数目。
 * 如果一个序列中 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该序列为等差序列。
 * <p>
 * 例如，[1, 3, 5, 7, 9]、[7, 7, 7, 7] 和 [3, -1, -5, -9] 都是等差序列。
 * 再例如，[1, 1, 2, 5, 7] 不是等差序列。
 * 数组中的子序列是从数组中删除一些元素（也可能不删除）得到的一个序列。
 * <p>
 * 例如，[2,5,10] 是 [1,2,1,2,4,1,5,10] 的一个子序列。
 * 题目数据保证答案是一个 32-bit 整数。
 * <p>
 * 示例 1：
 * 输入：nums = [2,4,6,8,10]
 * 输出：7
 * 解释：所有的等差子序列为：
 * [2,4,6]
 * [4,6,8]
 * [6,8,10]
 * [2,4,6,8]
 * [4,6,8,10]
 * [2,4,6,8,10]
 * [2,6,10]
 * <p>
 * 示例 2：
 * 输入：nums = [7,7,7,7,7]
 * 输出：16
 * 解释：数组中的任意子序列都是等差子序列。
 * <p>
 * 提示：
 * 1 <= nums.length <= 1000
 * -2^31 <= nums[i] <= 2^31 - 1
 */
public class Solution446 {

    public static void main(String[] args) {
//        System.out.println(numberOfArithmeticSlices(new int[]{2, 4, 6, 8, 10}));
//        System.out.println(numberOfArithmeticSlices(new int[]{7, 7, 7, 7, 7}));
//        System.out.println(numberOfArithmeticSlices(new int[]{0, 2000000000, -294967296}));
        System.out.println(numberOfArithmeticSlices(new int[]{-2147483648, 0, -2147483648}));
    }

    /**
     * {@link com.welph.leecode.part_1_500.part_401_420.Solution413}
     * ---这里允许删除.
     * 动态规划  dp[][]
     */
    public static int numberOfArithmeticSlices(int[] nums) {
        int len = nums.length;
        if (len <= 2) {
            return 0;
        }
        Map<Long, Integer>[] maps = new HashMap[len];
        maps[0] = new HashMap<>(2);
        maps[1] = new HashMap<>(4);
        maps[1].put(nums[1] + 0L - nums[0], 0);
        long a;
        int ret = 0;
        Map<Long, Integer> cur;
        for (int i = 2; i < len; i++) {
            maps[i] = cur = new HashMap<>();
            for (int j = 0; j < i; j++) {
                a = nums[i] + 0L - nums[j];
                Integer b = maps[j].get(a);
                if (b != null) {
                    ret += b + 1;
                }
                Integer c = cur.get(a);
                cur.put(a, (null == c ? 0 : ++c) + (b != null ? b + 1 : 0));
            }
        }
        return ret;
    }
}
