package com.welph.leecode.part_500_1000.part_521_540;

import com.welph.leecode.common.TreeNode;

/**
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * 差值是一个正数，其数值等于两值之差的绝对值。
 * <p>
 * 示例 1：
 * 输入：root = [4,2,6,1,3]
 * 输出：1
 * <p>
 * 示例 2：
 * 输入：root = [1,0,48,null,null,12,49]
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * 树中节点的数目范围是 [2, 10^4]
 * 0 <= Node.val <= 10^5
 */
public class Solution530 {

    public static void main(String[] args) {
        System.out.println(getMinimumDifference(TreeNode.createTestData("[4,2,6,1,3]")));
        System.out.println(getMinimumDifference(TreeNode.createTestData("[1,0,48,null,null,12,49]")));
        System.out.println(getMinimumDifference(TreeNode.createTestData("[236,104,701,null,227,null,911]")));
    }

    /**
     * 任意节点
     * 左右子树的最大和最小值.做比较
     */
    public static int getMinimumDifference(TreeNode root) {
        int[] difference = new int[3];
        getMinimumDifferenceRange(root, difference);
        return difference[0];
    }

    public static void getMinimumDifferenceRange(TreeNode root, int[] difference) {
        int minValue = Integer.MAX_VALUE;
        int max = root.val;
        int min = root.val;
        if (root.left != null) {
            getMinimumDifferenceRange(root.left, difference);
            min = difference[2];
            minValue = Math.min(Math.min(minValue, root.val - difference[1]), difference[0]);
        }
        if (root.right != null) {
            getMinimumDifferenceRange(root.right, difference);
            max = difference[1];
            minValue = Math.min(Math.min(minValue, difference[2] - root.val), difference[0]);
        }
        difference[0] = minValue;
        difference[1] = max;
        difference[2] = min;
    }
}
