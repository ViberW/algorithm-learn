package com.welph.leecode.part_361_380;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 给定两个以升序排列的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 * <p>
 * 示例 1:
 * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * 输出: [1,2],[1,4],[1,6]
 * 解释: 返回序列中的前 3 对数：
 * [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * <p>
 * 示例 2:
 * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * 输出: [1,1],[1,1]
 * 解释: 返回序列中的前 2 对数：
 * [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * <p>
 * 示例 3:
 * 输入: nums1 = [1,2], nums2 = [3], k = 3
 * 输出: [1,3],[2,3]
 * 解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
 * <p>
 * 提示:
 * 1 <= nums1.length, nums2.length <= 10^4
 * -10^9 <= nums1[i], nums2[i] <= 10^9
 * nums1, nums2 均为升序排列
 * 1 <= k <= 1000
 */
public class Solution373 {

    public static void main(String[] args) {
        System.out.println(kSmallestPairs(
                new int[]{1, 7, 11},
                new int[]{2, 4, 6}, 3));
        System.out.println(kSmallestPairs(
                new int[]{1, 1, 2},
                new int[]{1, 2, 3}, 2));
        System.out.println(kSmallestPairs(
                new int[]{1, 2},
                new int[]{3}, 3));
    }

    /**
     * 优先队列 : 使用小顶堆来处理, 这里就直接使用 {@link PriorityQueue} 之前的一些问题有自己实现过
     */
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(value
                -> value.sum));
        List<List<Integer>> res = new ArrayList<>();
        int len1 = Math.min(k, nums1.length);
        int len2 = nums2.length;
        int num2 = nums2[0];
        for (int i = 0; i < len1; i++) {
            queue.add(new Node(nums1[i], num2, 0));
        }
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            res.add(new ArrayList<Integer>() {{
                add(poll.num1);
                add(poll.num2);
            }});
            if (res.size() == k) {
                break;
            }
            if (poll.index2 + 1 < len2) {
                queue.add(new Node(poll.num1, nums2[poll.index2 + 1], poll.index2 + 1));
            }
        }
        return res;
    }

    static class Node {
        int num1;
        int num2;
        int index2;
        int sum;

        public Node(int num1, int num2, int index2) {
            this.num1 = num1;
            this.num2 = num2;
            this.index2 = index2;
            this.sum = num1 + num2;
        }
    }
}
