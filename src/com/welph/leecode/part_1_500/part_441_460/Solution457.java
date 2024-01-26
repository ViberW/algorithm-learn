package com.welph.leecode.part_1_500.part_441_460;

/**
 * 存在一个不含 0 的 环形 数组nums ，每个 nums[i] 都表示位于下标 i 的角色应该向前或向后移动的下标个数：
 * <p>
 * 如果 nums[i] 是正数，向前（下标递增方向）移动 |nums[i]| 步
 * 如果nums[i] 是负数，向后（下标递减方向）移动 |nums[i]| 步
 * 因为数组是 环形 的，所以可以假设从最后一个元素向前移动一步会到达第一个元素，而第一个元素向后移动一步会到达最后一个元素。
 * <p>
 * 数组中的 循环 由长度为 k 的下标序列 seq 标识：
 * <p>
 * 遵循上述移动规则将导致一组重复下标序列 seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...
 * 所有 nums[seq[j]] 应当不是 全正 就是 全负
 * k > 1
 * 如果 nums 中存在循环，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：nums = [2,-1,1,2,2]
 * 输出：true
 * 解释：存在循环，按下标 0 -> 2 -> 3 -> 0 。循环长度为 3 。
 * <p>
 * 示例 2：
 * 输入：nums = [-1,2]
 * 输出：false
 * 解释：按下标 1 -> 1 -> 1 ... 的运动无法构成循环，因为循环的长度为 1 。根据定义，循环的长度必须大于 1 。
 * <p>
 * 示例 3:
 * 输入：nums = [-2,1,-1,-2,-2]
 * 输出：false
 * 解释：按下标 1 -> 2 -> 1 -> ... 的运动无法构成循环，因为 nums[1] 是正数，而 nums[2] 是负数。
 * 所有 nums[seq[j]] 应当不是全正就是全负。
 * <p>
 * 提示：
 * 1 <= nums.length <= 5000
 * -1000 <= nums[i] <= 1000
 * nums[i] != 0
 * 进阶：你能设计一个时间复杂度为 O(n) 且 额外空间复杂度为 O(1) 的算法吗？
 */
public class Solution457 {

    public static void main(String[] args) {
        System.out.println(circularArrayLoop(new int[] { 2, -1, 1, 2, 2 }));
        System.out.println(circularArrayLoop(new int[] { -2, 1, -1, -2, -2 }));
        System.out.println(circularArrayLoop(new int[] { -1, 2 }));
        System.out.println(circularArrayLoop(new int[] { 2, 2, 2, 2, 2, 4, 7 }));
        System.out.println(circularArrayLoop(new int[] { -1, -1, -1 }));
    }

    /**
     * 使用hash表存储已经经历过的节点
     * //需要移动的位置 sum%nums.length
     * ----
     */
    public static boolean circularArrayLoop(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return false;
        }
        int limit = 1001;
        int next;
        boolean positive;
        for (int i = 0; i < len; i++) {
            // 节点被遍历过
            if (nums[i] >= limit) {
                continue;
            }
            positive = nums[i] > 0;
            int k = i;
            do {
                next = (nums[k] + k) % len;
                next = next < 0 ? len + next : next;
                if (next == k) {// 成为本身, 无法成环
                    break;
                }
                // 下一个节点被遍历过
                if (nums[next] >= limit) {
                    // 说明绕回来了
                    if (nums[next] == limit + i) {
                        return true;
                    } else {
                        break;
                    }
                }
                // 若原本为 顺时针且节点next小于0 或 逆时针且节点next大于0 ,说明next不满足当前循环方向
                if (positive ^ (nums[next] > 0)) {
                    break;
                }
                nums[k] = limit + i; // 加上limit 表示当前节点已经被遍历过了
                k = next;
            } while (nums[k] < limit);
        }
        return false;
    }

    /* 官方题解 */

    // 快慢指针
    // {@link Solution287}
    public boolean circularArrayLoop1(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                continue;
            }
            int slow = i, fast = next(nums, i);
            // 判断非零且方向相同
            while (nums[slow] * nums[fast] > 0 && nums[slow] * nums[next(nums, fast)] > 0) {
                if (slow == fast) {
                    if (slow != next(nums, slow)) {
                        return true;
                    } else {
                        break;
                    }
                }
                slow = next(nums, slow);
                fast = next(nums, next(nums, fast));
            }
            // 把经历过的节点置为0
            int add = i;
            while (nums[add] * nums[next(nums, add)] > 0) {
                int tmp = add;
                add = next(nums, add);
                nums[tmp] = 0;
            }
        }
        return false;
    }

    public int next(int[] nums, int cur) {
        int n = nums.length;
        return ((cur + nums[cur]) % n + n) % n; // 保证返回值在 [0,n) 中
    }

}
