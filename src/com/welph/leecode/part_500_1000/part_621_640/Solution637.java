package com.welph.leecode.part_500_1000.part_621_640;

import com.welph.leecode.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个非空二叉树的根节点 root , 以数组的形式返回每一层节点的平均值。与实际答案相差 10-5 以内的答案可以被接受。
 * <p>
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[3.00000,14.50000,11.00000]
 * 解释：第 0 层的平均值为 3,第 1 层的平均值为 14.5,第 2 层的平均值为 11 。
 * 因此返回 [3, 14.5, 11] 。
 * <p>
 * 示例 2:
 * 输入：root = [3,9,20,15,7]
 * 输出：[3.00000,14.50000,11.00000]
 * <p>
 * 提示：
 * 树中节点数量在 [1, 104] 范围内
 * -2^31 <= Node.val <= 2^31 - 1
 */
public class Solution637 {

    public static void main(String[] args) {

    }

    /**
     * bfs
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> rest = new ArrayList<>();
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        int size;
        long total;
        TreeNode poll;
        while (!nodes.isEmpty()) {
            size = nodes.size();
            total = 0L;
            for (int i = size; i > 0; i--) {
                poll = nodes.poll();
                total += poll.val;
                if (poll.left != null) {
                    nodes.add(poll.left);
                }
                if (poll.right != null) {
                    nodes.add(poll.right);
                }
            }
            rest.add(total * 1.0 / size);
        }
        return rest;
    }
}
