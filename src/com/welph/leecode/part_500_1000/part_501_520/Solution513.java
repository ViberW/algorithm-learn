package com.welph.leecode.part_500_1000.part_501_520;

import com.welph.leecode.common.TreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 * 假设二叉树中至少有一个节点。
 * <p>
 * 示例 1:
 * 输入: root = [2,1,3]
 * 输出: 1
 * <p>
 * 示例 2:
 * 输入: [1,2,3,4,null,5,6,null,null,7]
 * 输出: 7
 * <p>
 * 提示:
 * 二叉树的节点个数的范围是 [1,104]
 * -2^31 <= Node.val <= 2^31 - 1
 */
public class Solution513 {

    public static void main(String[] args) {
        TreeNode root = TreeNode.createTestData("[2,null,3]");
        System.out.println(findBottomLeftValue(root));
    }

    /**
     * 这里找的是最底层的, 还要最左边.
     * 广度优先搜索
     */
    public static int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int ret = root.val;
        int len;
        while (!queue.isEmpty()) {
            len = queue.size();
            ret = queue.peek().val; // 不如下面的方法
            for (int i = 0; i < len; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
        }
        return ret;
    }

    /* 官方题解 */

    // 广度
    public static int findBottomLeftValue2(TreeNode root) {
        int ret = 0;
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode p = queue.poll();
            if (p.right != null) { // 先放right
                queue.offer(p.right);
            }
            if (p.left != null) {// 再放left
                queue.offer(p.left);
            }
            ret = p.val; // 只需要取最后一个值作为最底层最左.
        }
        return ret;
    }

}
