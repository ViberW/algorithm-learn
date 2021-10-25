package com.welph.leecode.part_1_500.part_101_120;

import com.welph.leecode.common.TreeNode;

/**
 * . 根据一棵树的中序遍历与后序遍历构造二叉树。
 * .
 * . 注意:
 * . 你可以假设树中没有重复的元素。
 * .
 * . 例如，给出
 * .
 * . 中序遍历 inorder = [9,3,15,20,7]
 * . 后序遍历 postorder = [9,15,7,20,3]
 * . 返回如下的二叉树：
 * .
 * .     3
 * .    / \
 * .   9  20
 * .     /  \
 * .    15   7
 */
public class Solution106 {

    public static void main(String[] args) {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        TreeNode node = buildTree(inorder, postorder);
        TreeNode.printFront(node);
    }

    /**
     * 中序遍历
     * 后序遍历
     * .    后序遍历的每一段的最后一个节点即为根节点
     * .    区分了左子树|右子树;
     */
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, postorder, 0, inorder.length, 0, postorder.length);
    }

    public static TreeNode buildTree(int[] inorder, int[] postorder,
                                     int il, int ir,
                                     int pl, int pr) {
        if (pl >= pr || il >= ir) {
            return null;
        }
        int value = postorder[pr - 1];
        TreeNode node = new TreeNode(value);

        int i = il;
        for (; i < ir; i++) {
            if (inorder[i] == value) {
                break;
            }
        }
        System.out.println(i);
        node.left = buildTree(inorder, postorder, il, i, pl, pl + i - il);
        node.right = buildTree(inorder, postorder, i + 1, ir, pl + i - il, pr - 1);
        return node;
    }

}
