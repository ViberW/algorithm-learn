package com.welph.leecode.part_1_500.part_101_120;

import com.welph.leecode.common.TreeNode;

import java.util.LinkedList;

/**
 * .给定一个二叉树，找出其最小深度。
 * .
 * .最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * .
 * .说明: 叶子节点是指没有子节点的节点。
 * .
 * .示例:
 * .
 * .给定二叉树 [3,9,20,null,null,15,7],
 * .
 * .    3
 * .   / \
 * .  9  20
 * .    /  \
 * .   15   7
 * .返回它的最小深度  2.
 */
public class Solution111 {

    public static void main(String[] args) {
        TreeNode root = TreeNode.createTestData("[3,9,20,null,null,15,7]");
        TreeNode root2 = TreeNode.createTestData("[1,2]");
        System.out.println(minDepth(root));
        System.out.println(minDepth(root2));
    }

    /**
     * 若当前节点没有左右节点,则代表为节点;
     */
    public static int minDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int expect = 1;
        int depth = 1;
        TreeNode pop;
        while (!queue.isEmpty()) {
            pop = queue.pop();
            if (pop.left == null && pop.right == null) {
                break;
            }
            if (pop.left != null) {
                queue.addLast(pop.left);
            }
            if (pop.right != null) {
                queue.addLast(pop.right);
            }
            if (--expect == 0) {
                depth++;
                expect = queue.size();
            }
        }
        return depth;
    }

}