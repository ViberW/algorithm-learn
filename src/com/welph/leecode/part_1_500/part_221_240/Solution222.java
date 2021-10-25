package com.welph.leecode.part_1_500.part_221_240;

import com.welph.leecode.common.TreeNode;

/**
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，
 * 并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~2h个节点。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,3,4,5,6]
 * 输出：6
 * <p>
 * 示例 2：
 * 输入：root = []
 * 输出：0
 * <p>
 * 示例 3：
 * 输入：root = [1]
 * 输出：1
 * <p>
 * 提示：
 * 树中节点的数目范围是[0, 5 * 104]
 * 0 <= Node.val <= 5 * 104
 * 题目数据保证输入的树是 完全二叉树
 */
public class Solution222 {

    public static void main(String[] args) {
        TreeNode root = TreeNode.createTestData("[1,2,3,4,5,6]");
        System.out.println(countNodes(root));
        TreeNode root1 = TreeNode.createTestData("[1]");
        System.out.println(countNodes(root1));
        TreeNode root2 = TreeNode.createTestData("[1,2,3,4]");
        System.out.println(countNodes(root2));
    }

    public static int countNodes(TreeNode root) {
        //查找到当前存在的位置上
        int r = depth(root, true);
        int l = depth(root, false);
        int count = (int) Math.pow(2, r + 1) - 1;
        TreeNode target = root;
        int stepCount = (int) Math.pow(2, r + 1);
        while (r < l) {
            l--;
            r--;
            int tmp = l;
            l = depth(target.right, false);
            if (r < l) {
                count += stepCount / 2;
                target = target.right;
            } else {
                l = tmp;
                r = depth(target.left, true);
                if (r == l) {
                    count += stepCount / 2;
                }
                target = target.left;
            }
            stepCount = stepCount / 2;
        }
        return count;
    }

    public static int depth(TreeNode root, boolean right) {
        if (root == null) {
            return -1;
        }
        int n = 0;
        TreeNode node = root;
        while ((node = right ? node.right : node.left) != null) {
            n++;
        }
        return n;
    }
}
