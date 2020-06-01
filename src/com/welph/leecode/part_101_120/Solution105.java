package com.welph.leecode.part_101_120;

import com.welph.leecode.common.TreeNode;

/**
 * . 根据一棵树的前序遍历与中序遍历构造二叉树。
 * .
 * . 注意:
 * . 你可以假设树中没有重复的元素。
 * .
 * . 例如，给出
 * .
 * . 前序遍历 preorder = [3,9,20,15,7]
 * . 中序遍历 inorder = [9,3,15,20,7]
 * . 返回如下的二叉树：
 * .
 * .     3
 * .    / \
 * .   9  20
 * .     /  \
 * .    15   7
 */
public class Solution105 {

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode node = buildTree(preorder, inorder);
        TreeNode.printFront(node);
    }

    /**
     * 前序遍历  -- 每一段的第一个节点为父节点
     * 中序遍历  -- 区分了左子树|右子树;
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, 0, preorder.length, 0, inorder.length);
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder,
                                     int pl, int pr,
                                     int il, int ir) {
        if (pl >= pr || il >= ir) {
            return null;
        }
        int val = preorder[pl];
        TreeNode node = new TreeNode(val);
        int i = il;
        for (; i < ir; i++) {
            if (inorder[i] == val) {
                break;
            }
        }
        node.left = buildTree(preorder, inorder, pl + 1, pl + 1 + (i - il), il, i);
        node.right = buildTree(preorder, inorder, pl + 1 + (i - il), pr, i + 1, ir);
        return node;
    }
}
