package com.welph.leecode.part_1_500.part_101_120;

import com.welph.leecode.common.TreeNode;

/**
 * . 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * .
 * . 说明: 叶子节点是指没有子节点的节点。
 * .
 * . 示例: 
 * . 给定如下二叉树，以及目标和 sum = 22，
 * .
 * .               5
 * .              / \
 * .             4   8
 * .            /   / \
 * .           11  13  4
 * .          /  \      \
 * .         7    2      1
 * . 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 */
public class Solution112 {

    public static void main(String[] args) {
        //TreeNode root = TreeNode.createTestData("[5,4,8,11,null,13,4,7,2,null,null,null,1]");
        TreeNode root2 = TreeNode.createTestData("[1,2]");
        // System.out.println(hasPathSum(root, 22));
        System.out.println(hasPathSum(root2, 1));
    }

    /**
     * 最能理解的是深度优先搜索
     */
    public static boolean hasPathSum(TreeNode root, int sum) {
        //记录一个节点到另个节点的和
        if (root == null) {
            return false;
        }
        return hasPathSum(root, 0, sum);
    }

    public static boolean hasPathSum(TreeNode root, int curr, int sum) {
        curr = curr + root.val;
        if (root.left != null && hasPathSum(root.left, curr, sum)) {
            return true;
        }
        if (root.right != null && hasPathSum(root.right, curr, sum)) {
            return true;
        }
        return root.left == null && root.right == null && curr == sum;
    }
}
