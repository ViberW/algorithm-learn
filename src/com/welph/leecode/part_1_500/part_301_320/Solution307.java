package com.welph.leecode.part_1_500.part_301_320;

/**
 * 给你一个数组 nums ，请你完成两类查询，其中一类查询要求更新数组下标对应的值，另一类查询要求返回数组中某个范围内元素的总和。
 * 实现 NumArray 类：
 * NumArray(int[] nums) 用整数数组 nums 初始化对象
 * void update(int index, int val) 将 nums[index] 的值更新为 val
 * int sumRange(int left, int right) 返回子数组 nums[left, right] 的总和（即，nums[left] + nums[left + 1], ..., nums[right]）
 * <p>
 * 示例：
 * 输入：
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * 输出：
 * [null, 9, null, 8]
 * <p>
 * 解释：
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // 返回 9 ，sum([1,3,5]) = 9
 * numArray.update(1, 2);   // nums = [1,2,5]
 * numArray.sumRange(0, 2); // 返回 8 ，sum([1,2,5]) = 8
 * <p>
 * 提示：
 * 1 <= nums.length <= 3 * 104
 * -100 <= nums[i] <= 100
 * 0 <= index < nums.length
 * -100 <= val <= 100
 * 0 <= left <= right < nums.length
 * 最多调用 3 * 104 次 update 和 sumRange 方法
 */
public class Solution307 {

    public static void main(String[] args) {
        int[] nums = {1, 3, 5};
        NumArray numArray = new NumArray(nums);
        System.out.println(numArray.sumRange(0, 2));
        numArray.update(1, 2);
        System.out.println(numArray.sumRange(0, 2));
    }

    //树状数组
    static class NumArray {
        int[] tree;
        int[] nums;

        public NumArray(int[] nums) {
            int length = nums.length;
            this.nums = nums;
            tree = new int[length + 1];
            for (int i = 0; i < length; i++) {
                add(i + 1, nums[i]);
            }
        }

        public void update(int index, int val) {
            int v = val - nums[index];
            nums[index] = val;
            index++;
            for (int pos = index; pos < tree.length; pos += lowbit(pos)) {
                tree[pos] += v;
            }
        }

        public int sumRange(int left, int right) {
            return query(right + 1) - query(left);
        }

        private void add(int index, int val) {
            for (int pos = index; pos < tree.length; pos += lowbit(pos)) {
                tree[pos] += val;
            }
        }

        private int query(int i) {
            int ans = 0;
            for (int pos = i; pos > 0; pos -= lowbit(pos))
                ans += tree[pos];
            return ans;
        }

        private int lowbit(int i) {
            return i & -i;
        }
    }
}
