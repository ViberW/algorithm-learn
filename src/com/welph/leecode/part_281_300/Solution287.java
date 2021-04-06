package com.welph.leecode.part_281_300;

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
        int[] nums = {1, 3, 4, 2, 2};
        System.out.println(findDuplicate(nums));
        int[] nums1 = {1, 3, 4, 2, 2};
        System.out.println(findDuplicate1(nums1));
        int[] nums2 = {1, 1, 2};
        System.out.println(findDuplicate1(nums2));
    }

    //修改数组的方式, 因为值小于nums.length. 值找到对应的下标
    public static int findDuplicate(int[] nums) {
        int val;
        for (int i = 0; i < nums.length; ) {
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

    /**
     * todo 通过题解了解到快慢方法 good
     * 使用快慢指针, 最终一定会存在快慢两个点相等的地方
     * {@link com.welph.leecode.part_141_160.Solution141}
     * {@link com.welph.leecode.part_141_160.Solution142}  极度类似
     */
    public static int findDuplicate1(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow]; //走一步
            fast = nums[nums[fast]]; //走两步
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
