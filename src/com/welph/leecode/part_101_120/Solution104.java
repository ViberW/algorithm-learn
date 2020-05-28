package com.welph.leecode.part_101_120;

import com.welph.leecode.common.TreeNode;

/**
 * .给定一个二叉树，找出其最大深度。
 * .
 * .二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * .
 * .说明: 叶子节点是指没有子节点的节点。
 * .
 * .示例：
 * .给定二叉树 [3,9,20,null,null,15,7]，
 * .
 * .    3
 * .   / \
 * .  9  20
 * .    /  \
 * .   15   7
 * .返回它的最大深度 3 。
 */
public class Solution104 {

    public static void main(String[] args) {
        TreeNode testData = TreeNode.createTestData("[3,9,20,null,null,15,7]");
        System.out.println(maxDepth(testData));
    }

    /**
     * 深度优先搜索
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
