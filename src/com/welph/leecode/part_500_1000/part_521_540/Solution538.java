package com.welph.leecode.part_500_1000.part_521_540;

import com.welph.leecode.common.TreeNode;

/**
 * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），
 * 使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
 * <p>
 * 提醒一下，二叉搜索树满足下列约束条件：
 * <p>
 * 节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。
 * 左右子树也必须是二叉搜索树。
 * 注意：本题和 1038: https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/ 相同
 * <p>
 * 示例 1：
 * 输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * 输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 * <p>
 * 示例 2：
 * 输入：root = [0,null,1]
 * 输出：[1,null,1]
 * <p>
 * 示例 3：
 * 输入：root = [1,0,2]
 * 输出：[3,3,2]
 * <p>
 * 示例 4：
 * 输入：root = [3,2,4,1]
 * 输出：[7,9,4,10]
 * <p>
 * 提示：
 * 树中的节点数介于 0 和 104 之间。
 * 每个节点的值介于 -104 和 104 之间。
 * 树中的所有值 互不相同 。
 * 给定的树为二叉搜索树。
 */
public class Solution538 {

    public static void main(String[] args) {
//        TreeNode.print(convertBST(TreeNode.createTestData("[0,null,1]")));
//        TreeNode.print(convertBST(TreeNode.createTestData("[3,2,4,1]")));
        TreeNode.print(convertBST(TreeNode.createTestData("[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]")));
        TreeNode.print(convertBST(TreeNode.createTestData("[]")));
    }

    /**
     * 从右子树开始遍历
     */
    public static TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return root;
        }
        convertBST1(root, 0);
        return root;
    }

    static TreeNode convertBST1(TreeNode root, int addition) {
        if (root.right != null) {
            root.val += convertBST1(root.right, addition).val;
        } else {
            root.val += addition;
        }
        if (root.left != null) {
            return convertBST1(root.left, root.val);
        }
        return root;
    }

    /* 官方题解 */
    //Morris遍历, 从left开始就是反着来的了
    public TreeNode convertBST1(TreeNode root) {
        int sum = 0;
        TreeNode node = root;

        while (node != null) {
            if (node.right == null) {
                sum += node.val;
                node.val = sum;
                node = node.left;
            } else {
                TreeNode succ = getSuccessor(node);
                if (succ.left == null) {
                    succ.left = node;
                    node = node.right;
                } else {
                    succ.left = null;
                    sum += node.val;
                    node.val = sum;
                    node = node.left;
                }
            }
        }

        return root;
    }

    public TreeNode getSuccessor(TreeNode node) {
        TreeNode succ = node.right;
        while (succ.left != null && succ.left != node) {
            succ = succ.left;
        }
        return succ;
    }

}
