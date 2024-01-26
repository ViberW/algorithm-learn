package com.welph.leecode.part_1_500.part_421_440;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
 * 进阶：你可以在 O(n) 的时间解决这个问题吗？
 * <p>
 * 示例 1：
 * 输入：nums = [3,10,5,25,2,8]
 * 输出：28
 * 解释：最大运算结果是 5 XOR 25 = 28.
 * <p>
 * 示例 2：
 * 输入：nums = [0]
 * 输出：0
 * <p>
 * 示例 3：
 * 输入：nums = [2,4]
 * 输出：6
 * <p>
 * 示例 4：
 * 输入：nums = [8,10,2]
 * 输出：10
 * <p>
 * 示例 5：
 * 输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 * 输出：127
 * <p>
 * 提示：
 * 1 <= nums.length <= 2 * 10^4
 * 0 <= nums[i] <= 2^31 - 1
 */
public class Solution421 {

    public static void main(String[] args) {
        System.out.println(findMaximumXOR(new int[] { 3, 10, 5, 25, 2, 8 }));
    }

    /**
     * 寻找最大的异或值
     * -------------- 看了思路
     * 思路分析：这种题就不要暴力法，指名道姓说要O(n)。根据提示需要使用建树。
     * 首先我们需要知道，二进制高位为1会大于低位的所有和，比如"11111111"最高位代表的"1"按权展开为128，
     * 而后面的“1111111”按权展开的和也只是127。所以进行异或时应该尽量选择高位异或结果为“1”的。
     * 第一步：遍历数组，我们按照二进制[31,30,…,1, 0]各位的状态进行建树，left放置0，right放置1。
     * 比如某个int型数的二进制是"0110110…"，我们需要将其放置到[left,right,right,left,right,right,left…]。
     * 第二步：遍历数组，按照贪心策略，尽量维持当前选择的方向能保证当前能位异或结果为1。
     */
    public static int findMaximumXOR(int[] nums) {
        // 构建trie树
        TrieNode root = new TrieNode();
        TrieNode current;
        for (int num : nums) {
            current = root;
            for (int i = 31; i >= 0; i--) {
                if (((num >> i) & 1) == 0) {
                    if (current.left == null) {
                        current.left = new TrieNode();
                    }
                    current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = new TrieNode();
                    }
                    current = current.right;
                }
            }
            current.left = new TrieNode(num);
        }
        int max = 0;
        for (int num : nums) {
            current = root;
            for (int i = 31; i >= 0; i--) {
                // 相反方向走
                if (((num >> i) & 1) == 0) {
                    if (current.right != null) {
                        current = current.right;
                    } else {
                        current = current.left;
                    }
                } else {
                    if (current.left != null) {
                        current = current.left;
                    } else {
                        current = current.right;
                    }
                }
            }
            max = Math.max(max, num ^ current.left.data);
        }
        return max;
    }

    public static class TrieNode {
        public int data;
        public TrieNode left;
        public TrieNode right;

        public TrieNode() {
        }

        public TrieNode(int data) {
            this.data = data;
        }
    }

    /* 官方的第一种比较慢的解法 快速解法为上面的字典树方法 */

    public int findMaximumXOR2(int[] nums) {
        int x = 0;
        for (int k = 30; k >= 0; --k) {
            Set<Integer> seen = new HashSet<Integer>();
            // 将所有的 pre^k(a_j) 放入哈希表中
            for (int num : nums) {
                // 如果只想保留从最高位开始到第 k 个二进制位为止的部分
                // 只需将其右移 k 位
                seen.add(num >> k);
            }

            // 目前 x 包含从最高位开始到第 k+1 个二进制位为止的部分
            // 我们将 x 的第 k 个二进制位置为 1，即为 x = x*2+1
            int xNext = x * 2 + 1;
            boolean found = false;

            // 枚举 i
            for (int num : nums) {
                if (seen.contains(xNext ^ (num >> k))) {
                    found = true;
                    break;
                }
            }

            if (found) {
                x = xNext;
            } else {
                // 如果没有找到满足等式的 a_i 和 a_j，那么 x 的第 k 个二进制位只能为 0
                // 即为 x = x*2
                x = xNext - 1;
            }
        }
        return x;
    }

}
