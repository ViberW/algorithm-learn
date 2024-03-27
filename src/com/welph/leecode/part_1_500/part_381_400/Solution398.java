package com.welph.leecode.part_1_500.part_381_400;

import java.util.Random;

/**
 * 给定一个可能含有重复元素的整数数组，要求随机输出给定的数字的索引。 您可以假设给定的数字一定存在于数组中。
 * 注意：
 * 数组大小可能非常大。 使用太多额外空间的解决方案将不会通过测试。
 * <p>
 * 示例:
 * int[] nums = new int[] {1,2,3,3,3};
 * Solution solution = new Solution(nums);
 * <p>
 * // pick(3) 应该返回索引 2,3 或者 4。每个索引的返回概率应该相等。
 * solution.pick(3);
 * <p>
 * // pick(1) 应该返回 0。因为只有nums[0]等于1。
 * solution.pick(1);
 */
public class Solution398 {

    public static void main(String[] args) {
        Solution obj = new Solution(new int[]{1, 2, 3, 3, 3, 2, 1, 3});
        obj.pick(2);
    }

    /**
     * {@link Solution382} 水塘抽样
     */
    static class Solution {
        static Random random = new Random();
        int[] nums;
        int length;

        public Solution(int[] nums) {
            this.nums = nums;
            this.length = nums.length;
        }

        public int pick(int target) {
            int tl = 0;
            int r;
            int ret = -1;
            for (int i = 0; i < length; i++) {
                if (nums[i] == target) {
                    tl++;
                    r = random.nextInt(tl);
                    if (r == 0) {
                        ret = i;
                    }
                }
            }
            return ret;
        }

        //简化代码
        public int pick1(int target) {
            int ret = -1;
            for (int i = 0, tl = 0; i < length; i++) {
                if (nums[i] == target) {
                    tl++;
                    if (random.nextInt(tl) == 0) {
                        ret = i;
                    }
                }
            }
            return ret;
        }
    }

}
