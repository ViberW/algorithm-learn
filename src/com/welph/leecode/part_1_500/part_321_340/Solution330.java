package com.welph.leecode.part_1_500.part_321_340;

/**
 * 给定一个已排序的正整数数组 nums，和一个正整数 n 。从 [1, n] 区间内选取任意个数字补充到 nums 中，
 * 使得 [1, n] 区间内的任何数字都可以用 nums 中某几个数字的和来表示。
 * 请输出满足上述要求的最少需要补充的数字个数。
 * <p>
 * 示例 1:
 * 输入: nums = [1,3], n = 6
 * 输出: 1
 * 解释:
 * 根据 nums 里现有的组合 [1], [3], [1,3]，可以得出 1, 3, 4。
 * 现在如果我们将 2 添加到 nums 中， 组合变为: [1], [2], [3], [1,3], [2,3], [1,2,3]。
 * 其和可以表示数字 1, 2, 3, 4, 5, 6，能够覆盖 [1, 6] 区间里所有的数。
 * 所以我们最少需要添加一个数字。
 * <p>
 * 示例 2:
 * 输入: nums = [1,5,10], n = 20
 * 输出: 2
 * 解释: 我们需要添加 [2, 4]。
 * <p>
 * 示例 3:
 * 输入: nums = [1,2,2], n = 5
 * 输出: 0
 */
public class Solution330 {

    public static void main(String[] args) {
        System.out.println(minPatches(new int[] { 1, 3 }, 6));
        System.out.println(minPatches(new int[] { 1, 5, 10 }, 20));
        System.out.println(minPatches(new int[] { 1, 2, 2 }, 5));
        System.out.println(minPatches(new int[] { 1, 2, 31, 33 }, 2147483647));
    }

    // 尽量选择满足的. 1~n的所有值的总和K, 在1~n中能找到任意的数字和等于1~k范围内的值;
    public static int minPatches(int[] nums, int n) {
        int res = 0;
        // 不断变更res的, 直到res>n
        int mid = n / 2;
        // 防止数据越界, 需要对N除2;
        int ans = 0;
        for (int i = 0; i < nums.length && res < n;) {
            int num = nums[i];
            if (num - res <= 1) { // 因为nums是已排序好的.不用担心重复的数值插入
                if (num > mid) {
                    return ans;
                }
                res += num;
                i++;
            } else {
                ans++; //插入一个值, 这个值为 res+1
                if (res > mid) {
                    return ans;
                }
                res += res + 1;
            }
        }
        while (res < n) {
            ans++;
            if (res > mid) {
                return ans;
            }
            res += res + 1;
        }
        return ans;
    }

    /* 官方题解的写法 也是贪心算法 */
    /*
     * 对于正整数 x，如果区间 [1,x−1] 内的所有数字都已经被覆盖，且 x 在数组中，
     * 则区间 [1,2x−1][内的所有数字也都被覆盖
     * ---------------------------------------
     * 贪心逻辑:
     * 每次找到未被数组 nums 覆盖的最小的整数 x，在数组中补充 x，然后寻找下一个未被覆盖的最小的整数，
     * 重复上述步骤直到区间 [1,n]中的所有数字都被覆盖。
     */
    public int minPatches2(int[] nums, int n) {
        int patches = 0;
        long x = 1;
        int length = nums.length, index = 0;
        while (x <= n) {
            if (index < length && nums[index] <= x) {
                x += nums[index];
                index++;
            } else {
                x *= 2;
                patches++;
            }
        }
        return patches;
    }

}
