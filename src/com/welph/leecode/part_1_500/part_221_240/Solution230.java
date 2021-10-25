package com.welph.leecode.part_1_500.part_221_240;

import com.welph.leecode.common.TreeNode;

/**
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第k个最小元素（从 1 开始计数）。
 * <p>
 * 示例 1：
 * 输入：root = [3,1,4,null,2], k = 1
 * 输出：1
 * <p>
 * 示例 2：
 * 输入：root = [5,3,6,2,4,null,null,1], k = 3
 * 输出：3
 * <p>
 * 提示：
 * 树中的节点数为 n 。
 * 1 <= k <= n <= 104
 * 0 <= Node.val <= 104
 */
public class Solution230 {

    public static void main(String[] args) {
        System.out.println(kthSmallest(TreeNode.createTestData("[3,1,4,null,2,null,null]"), 1));
    }

    /**
     * Morris 搜索 {@link com.welph.leecode.part_1_500.part_81_100.Solution94}
     */
    public static int kthSmallest(TreeNode root, int k) {
        int count = 0;
        TreeNode curr = root;
        TreeNode pre;
        while (curr != null) {
            if (curr.left == null) {
                count++;
                if (count == k) {
                    return curr.val;
                }
                curr = curr.right;
            } else {
                pre = curr.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = curr;
                TreeNode temp = curr;
                curr = curr.left;
                temp.left = null;
            }
        }
        return 0;
    }
}
