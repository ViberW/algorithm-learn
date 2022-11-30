package com.welph.leecode.part_500_1000.part_541_560;

import com.welph.leecode.common.TreeNode;

/**
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 * <p>
 * 示例 :
 * 给定二叉树
 * |         1
 * |        / \
 * |       2   3
 * |      / \
 * |     4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 * <p>
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 */
public class Solution543 {

    public static void main(String[] args) {
        System.out.println(diameterOfBinaryTree(TreeNode.createTestData("[4,2,1,3,null,null]")));
    }

    static int max = 0;

    //要么是左右子树, 要么是父左右子树 树的最大值
    public static int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        diameter(root);
        return max;
    }

    public static int diameter(TreeNode root) {
        int l = 0;
        int r = 0;
        if (root.left != null) {
            l = diameter(root.left);
        }
        if (root.right != null) {
            r = diameter(root.right);
        }
        max = Math.max(max, l + r);
        return Math.max(l, r) + 1;
    }
}
