package com.welph.leecode.part_1_500.part_101_120;

import com.welph.leecode.common.TreeNode;

/**
 * . 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * .
 * . 本题中，一棵高度平衡二叉树定义为：
 * .
 * . 一个二叉树每个节点的左右两个子树的高度差的绝对值不超过1。
 * .
 * . 示例 1:
 * .
 * . 给定二叉树 [3,9,20,null,null,15,7]
 * .
 * .     3
 * .    / \
 * .   9  20
 * .     /  \
 * .    15   7
 * . 返回 true 。
 */
public class Solution110 {

    public static void main(String[] args) {
        TreeNode root = TreeNode.createTestData("[1,2,2,3,3,null,null,4,4]");
        System.out.println(isBalanced(root));
    }

    /**
     * 递归查看左子树和右子树的高度差.看看是否满足即可
     */
    public static boolean isBalanced(TreeNode root) {
        return balance(root) >= 0;
    }

    public static int balance(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int lh = balance(root.left);
        if (lh < 0) {
            return lh;
        }
        int rh = balance(root.right);
        if (rh < 0) {
            return rh;
        }
        return Math.abs(rh - lh) > 1 ? -1 : Math.max(rh, lh) + 1;
    }
}
