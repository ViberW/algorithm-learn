package com.welph.leecode.part_1_500.part_281_300;

/**
 * 给定一个包含n + 1 个整数的数组nums ，其数字都在 1 到 n之间（包括 1 和 n），可知至少存在一个重复的整数。
 * 假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,3,4,2,2]
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：nums = [3,1,3,4,2]
 * 输出：3
 * <p>
 * 示例 3：
 * 输入：nums = [1,1]
 * 输出：1
 * <p>
 * 示例 4：
 * 输入：nums = [1,1,2]
 * 输出：1
 * <p>
 * 提示：
 * 2 <= n <= 3 * 104
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
 * <p>
 * 进阶：
 * 如何证明 nums 中至少存在一个重复的数字?
 * 你可以在不修改数组 nums 的情况下解决这个问题吗？
 * 你可以只用常量级 O(1) 的额外空间解决这个问题吗？
 * 你可以设计一个时间复杂度小于 O(n2) 的解决方案吗？
 */
public class Solution287 {

    public static void main(String[] args) {
        int[] nums = { 1, 3, 4, 2, 2 };
        System.out.println(findDuplicate(nums));
        int[] nums1 = { 1, 3, 4, 2, 2 };
        System.out.println(findDuplicate1(nums1));
        int[] nums2 = { 1, 1, 2 };
        System.out.println(findDuplicate1(nums2));
    }

    // 修改数组的方式, 因为值小于nums.length. 值找到对应的下标
    public static int findDuplicate(int[] nums) {
        int val;
        for (int i = 0; i < nums.length;) {
            val = nums[i];
            if (nums[val] != val) {
                swap(nums, val, i);
            } else if (val != i) {
                return val;
            } else {
                i++;
            }
        }
        return 0;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /* 官方题解 */

    // 二分查找
    public int findDuplicate2(int[] nums) {
        int n = nums.length;
        // 针对数值的二分, 而非数组
        int l = 1, r = n - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            int cnt = 0;
            for (int i = 0; i < n; ++i) {
                if (nums[i] <= mid) {
                    cnt++;
                }
            }
            if (cnt <= mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
                ans = mid; // 因为重复,所以一定是cnt大于mid值的
            }
        }
        return ans;
    }

    // 二进制
    // 考虑第 i 位，我们记 nums 数组中二进制展开后第 i 位为 1 的数有 x 个，
    // 数字 [1,n]这 n 个数二进制展开后第 i 位为 1 的数有 y 个，那么重复的数第 i 位为 1 当且仅当 x>y
    public int findDuplicate3(int[] nums) {
        int n = nums.length, ans = 0;
        int bit_max = 31;
        while (((n - 1) >> bit_max) == 0) { // 缩小范围
            bit_max -= 1;
        }
        for (int bit = 0; bit <= bit_max; ++bit) {
            int x = 0, y = 0;
            for (int i = 0; i < n; ++i) {
                if ((nums[i] & (1 << bit)) != 0) {
                    x += 1;
                }
                if (i >= 1 && ((i & (1 << bit)) != 0)) {
                    y += 1;
                }
            }
            if (x > y) {
                ans |= 1 << bit;
            }
        }
        return ans;
    }

    /**
     * todo 通过题解了解到快慢方法 good
     * 使用快慢指针, 最终一定会存在快慢两个点相等的地方
     * {@link com.welph.leecode.part_1_500.part_141_160.Solution141}
     * {@link com.welph.leecode.part_1_500.part_141_160.Solution142} 极度类似
     */
    public static int findDuplicate1(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow]; // 走一步
            fast = nums[nums[fast]]; // 走两步
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
