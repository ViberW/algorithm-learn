package com.welph.leecode.part_500_1000.part_501_520;

import com.welph.leecode.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 * <p>
 * 示例1：
 * 输入: root = [1,3,2,5,3,null,9]
 * 输出: [1,3,9]
 * <p>
 * 示例2：
 * 输入: root = [1,2,3]
 * 输出: [1,3]
 * <p>
 * 提示：
 * 二叉树的节点个数的范围是 [0,10^4]
 * -2^31 <= Node.val <= 2^31 - 1
 */
public class Solution515 {

    public static void main(String[] args) {
        System.out.println(largestValues(TreeNode.createTestData("[1,3,2,5,3,null,9]")));
        System.out.println(largestValues(TreeNode.createTestData("[1,2,3]")));
    }

    /**
     * 广度优先算法
     */
    public static List<Integer> largestValues(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Integer max = null;
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
                max = max(max, poll.val);
            }
            ret.add(max);
        }
        return ret;
    }

    static int max(Integer val, int v) {
        return val == null || val < v ? v : val;
    }
}
