package com.welph.leecode.part_1_500.part_361_380;

/**
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为 摆动序列 。
 * 第一个差（如果存在的话）可能是正数或负数。仅有一个元素或者含两个不等元素的序列也视作摆动序列。
 * <p>
 * 例如，[1, 7, 4, 9, 2, 5] 是一个 摆动序列 ，因为差值 (6, -3, 5, -7, 3)是正负交替出现的。
 * <p>
 * 相反，[1, 4, 7, 2, 5]和[1, 7, 4, 5, 5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 * 子序列 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。
 * <p>
 * 给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,7,4,9,2,5]
 * 输出：6
 * 解释：整个序列均为摆动序列，各元素之间的差值为 (6, -3, 5, -7, 3) 。
 * <p>
 * 示例 2：
 * 输入：nums = [1,17,5,10,13,15,10,5,16,8]
 * 输出：7
 * 解释：这个序列包含几个长度为 7 摆动序列。
 * 其中一个是 [1, 17, 10, 13, 10, 16, 8] ，各元素之间的差值为 (16, -7, 3, -3, 6, -8) 。
 * <p>
 * 示例 3：
 * 输入：nums = [1,2,3,4,5,6,7,8,9]
 * 输出：2
 */
public class Solution376 {

    public static void main(String[] args) {
        System.out.println(wiggleMaxLength(new int[]{1, 7, 4, 9, 2, 5}));
        System.out.println(wiggleMaxLength(new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8}));
        System.out.println(wiggleMaxLength(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
    }

    /**
     * 这个贪心算法  666  看了思路
     */
    public static int wiggleMaxLength(int[] nums) {
        int length = nums.length;
        if (length < 2) {
            return length;
        }
        int up = 1;
        int down = 1;
        for (int i = 1; i < length; i++) {
            //这里贪心, 因为down是由于上一次的
            // 所以不管上一次包不包含:
            //  1. 包含, 那上次nums[i] < nums[i - 1] 所以up=down+1 没问题
            //  2. 不包含, 那上次可能是 nums[i] >= nums[i - 1],上次的down可以递推到再上一次. 中间的只可以删除掉.不影响up的计算
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            }
            if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }

    /* 官方题解(其他解法) */

    //动态规划
    public int wiggleMaxLength2(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        //前i个元素中的最长上升摆动序列(最长结尾是上升)
        int[] up = new int[n];
        //前i个元素中的最长下降摆动序列(最长结尾是下降)
        int[] down = new int[n];
        up[0] = down[0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = Math.max(up[i - 1], down[i - 1] + 1);
                down[i] = down[i - 1];//因为比i-1大, 则下降序列和i-1一样
            } else if (nums[i] < nums[i - 1]) {
                up[i] = up[i - 1]; //因为比i-1小, 则上升序列和i-1一样
                down[i] = Math.max(up[i - 1] + 1, down[i - 1]);
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }
        return Math.max(up[n - 1], down[n - 1]);
    }

    //对动态规划的优化
    public int wiggleMaxLength3(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int up = 1, down = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                up = Math.max(up, down + 1);
            } else if (nums[i] < nums[i - 1]) {
                down = Math.max(up + 1, down);
            }
        }
        return Math.max(up, down);
    }

    //贪心
    public int wiggleMaxLength5(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int prevdiff = nums[1] - nums[0];
        int ret = prevdiff != 0 ? 2 : 1;
        for (int i = 2; i < n; i++) {
            int diff = nums[i] - nums[i - 1];
            if ((diff > 0 && prevdiff <= 0) || (diff < 0 && prevdiff >= 0)) {
                ret++;
                prevdiff = diff;
            }
        }
        return ret;
    }
}
