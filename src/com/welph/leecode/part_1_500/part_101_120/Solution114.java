package com.welph.leecode.part_1_500.part_101_120;

import com.welph.leecode.common.TreeNode;

/**
 * . 给定一个二叉树，原地将它展开为一个单链表。
 * .
 * .
 * .
 * . 例如，给定二叉树
 * .
 * .     1
 * .    / \
 * .   2   5
 * .  / \   \
 * . 3   4   6
 * . 将其展开为：
 * .
 * . 1
 * .  \
 * .   2
 * .    \
 * .     3
 * .      \
 * .       4
 * .        \
 * .         5
 * .          \
 * .           6
 */
public class Solution114 {

    public static void main(String[] args) {
        TreeNode root = TreeNode.createTestData("[1,2,5,3,4,null,6]");
        flatten(root);
        TreeNode.printFront(root);
    }

    /**
     * 这里需要`原地` 且右子树单链表
     * 类似于{@link com.welph.leecode.part_1_500.part_81_100.Solution94}的莫里斯遍历(Morris)转换问题
     */
    public static void flatten(TreeNode root) {
        //仅仅需要将右子树放到左子树的右子树中;
        TreeNode pre;
        while (root != null) {
            if (root.left == null) {
                root = root.right;
            } else {
                pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = root.right;
                root.right = root.left;
                root.left = null;
                root = root.right;
            }
        }
    }

}
