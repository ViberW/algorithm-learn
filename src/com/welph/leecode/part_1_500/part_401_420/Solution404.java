package com.welph.leecode.part_1_500.part_401_420;

import com.welph.leecode.common.TreeNode;

/**
 * 计算给定二叉树的所有左叶子之和。
 * <p>
 * 示例：
 * <p>
 * |     3
 * |    / \
 * |   9  20
 * |     /  \
 * |    15   7
 * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 */
public class Solution404 {

    public static void main(String[] args) {
        TreeNode root = TreeNode.createTestData("[3,9,20,null,null,15,7]");
        System.out.println(sumOfLeftLeaves(root));
    }

    /**
     * 这里是叶子节点
     */
    public static int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        if (null != root.left && root.left.left == null
                && root.left.right == null) {
            sum += root.left.val;
        }
        sum += sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
        return sum;
    }
}
