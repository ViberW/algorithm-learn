package com.welph.leecode.part_1_500.part_121_140;

import com.welph.leecode.common.TreeNode;

/**
 * .给定一个二叉树，它的每个结点都存放一个0-9的数字，每条从根到叶子节点的路径都代表一个数字。
 * .例如，从根到叶子节点路径 1->2->3 代表数字 123。
 * .计算从根到叶子节点生成的所有数字之和。
 * .说明:叶子节点是指没有子节点的节点。
 * .示例 1:
 * .
 * .输入: [1,2,3]
 * .    1
 * .   / \
 * .  2   3
 * .输出: 25
 * .解释:
 * .从根到叶子节点路径 1->2 代表数字 12.
 * .从根到叶子节点路径 1->3 代表数字 13.
 * .因此，数字总和 = 12 + 13 = 25.
 * .示例 2:
 * .输入: [4,9,0,5,1]
 * .    4
 * .   / \
 * .  9   0
 * . / \
 * .5   1
 * .输出: 1026
 * .解释:
 * .从根到叶子节点路径 4->9->5 代表数字 495.
 * .从根到叶子节点路径 4->9->1 代表数字 491.
 * .从根到叶子节点路径 4->0 代表数字 40.
 * .因此，数字总和 = 495 + 491 + 40 = 1026.
 */
public class Solution129 {

    public static void main(String[] args) {
        TreeNode testData = TreeNode.createTestData("[]");

        System.out.println(sumNumbers(testData));
    }

    /**
     * 深度优先搜索就可以了
     */
    public static int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return sumNumbers(root, 0);
    }

    public static int sumNumbers(TreeNode root, int val) {
        int total = 0;
        if (root.left == null && root.right == null) {
            //叶子节点
            total += val + root.val;
        } else {
            if (root.left != null) {
                total += sumNumbers(root.left, (val + root.val) * 10);
            }
            if (root.right != null) {
                total += sumNumbers(root.right, (val + root.val) * 10);
            }
        }
        return total;
    }
}
