package com.welph.leecode.part_500_1000.part_501_520;

import com.welph.leecode.common.TreeNode;

import java.util.*;

/**
 * 给你一个二叉树的根结点 root ，请返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，
 * 返回所有出现次数最多的子树元素和（不限顺序）。
 * 一个结点的 「子树元素和」 定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。
 * <p>
 * 示例 1：
 * 输入: root = [5,2,-3]
 * 输出: [2,-3,4]
 * <p>
 * 示例 2：
 * 输入: root = [5,2,-5]
 * 输出: [2]
 * <p>
 * 提示:
 * 节点数在 [1, 10^4] 范围内
 * -10^5 <= Node.val <= 10^5
 */
public class Solution508 {

    public static void main(String[] args) {
        TreeNode root = TreeNode.createTestData("[5,2,-3]");
        System.out.println(Arrays.toString(findFrequentTreeSum(root)));
        root = TreeNode.createTestData("[5,2,-5]");
        System.out.println(Arrays.toString(findFrequentTreeSum(root)));
    }

    /**
     * 深度搜索, 以每个节点进行处理
     */
    public static int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> count = new HashMap<>();
        int[] max = new int[1];
        findFrequentTreeSum(root, count, max);
        int maxCount = max[0];
        List<Integer> ret = new ArrayList<>();
        count.forEach((v, c) -> {
            if (maxCount == c) {
                ret.add(v);
            }
        });
        return ret.stream().mapToInt(value -> value).toArray();
    }

    private static int findFrequentTreeSum(TreeNode root, Map<Integer, Integer> count, int[] max) {
        int total = 0;
        if (root.left != null) {
            total += findFrequentTreeSum(root.left, count, max);
        }
        if (root.right != null) {
            total += findFrequentTreeSum(root.right, count, max);
        }
        total += root.val;
        int c = count.getOrDefault(total, 0) + 1;
        if (max[0] < c) {
            max[0] = c;
        }
        count.put(total, c);
        return total;
    }
}
