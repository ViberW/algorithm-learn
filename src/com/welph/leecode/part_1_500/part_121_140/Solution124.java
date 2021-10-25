package com.welph.leecode.part_1_500.part_121_140;

import com.welph.leecode.common.TreeNode;

/**
 * 给定一个非空二叉树，返回其最大路径和。
 * 本题中，路径被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列
 * 该路径至少包含一个节点，且不一定经过根节点。
 * 示例 1：
 * .输入：[1,2,3]
 * .       1
 * .      / \
 * .     2   3
 * .输出：6
 * .示例2：
 * .输入：[-10,9,20,null,null,15,7]
 * .   -10
 * .   / \
 * .  9  20
 * .    /  \
 * .   15   7
 * 输出: 42
 */
public class Solution124 {

    public static void main(String[] args) {
        TreeNode root = TreeNode.createTestData("[2,-1,-2]");
        System.out.println(maxPathSum(root));
    }

    /**
     * 思考: 从某个点出发, 到它的父路径时,仅仅需要比较,从父路径出发的最大值.
     * 一切以左子树出发
     * 1. 到根节点的最大值(单边)
     * 2. 当前节点的最大值
     * //每次往上, 比较当前的 最大值/ 两个子树的相加最大值.
     * // 每次往上,保留当前节点最大值.(深度优先搜索)
     *
     * @param root
     * @return
     */
    public static int maxPathSum(TreeNode root) {
        int[] max = new int[]{Integer.MIN_VALUE};
        maxLeafPathSum(root, max);
        return max[0];
    }

    public static int maxLeafPathSum(TreeNode node, int[] max) {
        int l = 0;
        if (null != node.left) {
            l = maxLeafPathSum(node.left, max);
        }
        int r = 0;
        if (null != node.right) {
            r = maxLeafPathSum(node.right, max);
        }
        int clr;
        if (r > 0 && l > 0) {
            clr = node.val + l + r;
            if (clr > max[0]) {
                max[0] = clr;
            }
            return node.val + Math.max(l, r);
        } else {
            clr = node.val + (r < 0 && l < 0 ? 0 : Math.max(l, r));
            if (clr > max[0]) {
                max[0] = clr;
            }
            return clr;
        }
    }
}
