package com.welph.leecode.part_1_500.part_441_460;

import com.welph.leecode.common.TreeNode;

/**
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。
 * 返回二叉搜索树（有可能被更新）的根节点的引用。
 * 一般来说，删除节点可分为两个步骤：
 * <p>
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * <p>
 * 示例 1:
 * 输入：root = [5,3,6,2,4,null,7], key = 3
 * 输出：[5,4,6,2,null,null,7]
 * 解释：给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 * <p>
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,7], key = 0
 * 输出: [5,3,6,2,4,null,7]
 * 解释: 二叉树不包含值为 0 的节点
 * <p>
 * 示例 3:
 * 输入: root = [], key = 0
 * 输出: []
 * <p>
 * 提示:
 * 节点数的范围 [0, 104].
 * -105 <= Node.val <= 105
 * 节点值唯一
 * root 是合法的二叉搜索树
 * -105 <= key <= 105
 * 进阶： 要求算法时间复杂度为 O(h)，h 为树的高度。
 */
public class Solution450 {

    public static void main(String[] args) {
        TreeNode root = TreeNode.createTestData("[5,3,6,2,4,null,7]");
        TreeNode.print(deleteNode(root, 3));
    }

    public static TreeNode deleteNode(TreeNode root, int key) {
        TreeNode parent = new TreeNode(0);
        parent.left = root;
        TreeNode pre = parent;
        while (root != null) {
            if (root.val == key) {
                break;
            } else if (root.val > key) {
                pre = root;
                root = root.left;
            } else {
                pre = root;
                root = root.right;
            }
        }
        if (root == null) {
            return parent.left;
        }
        TreeNode target = null;
        if (root.left != null) {
            target = root.left;
            if (root.right != null) {
                TreeNode tmp = target;
                while (tmp.right != null) {
                    tmp = tmp.right;
                }
                tmp.right = root.right;
            }
        } else if (root.right != null) {
            target = root.right;
        }
        if (pre.left == root) {
            pre.left = target;
        } else {
            pre.right = target;
        }
        return parent.left;
    }
}
