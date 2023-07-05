package com.welph.leecode.part_500_1000.part_661_680;

import com.welph.leecode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你一棵二叉树的根节点 root ，返回树的 最大宽度 。
 * 树的 最大宽度 是所有层中最大的 宽度 。
 * 每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。
 * 将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。
 * 题目数据保证答案将会在  32 位 带符号整数范围内。
 * <p>
 * 示例 1：
 * 输入：root = [1,3,2,5,3,null,9]
 * 输出：4
 * 解释：最大宽度出现在树的第 3 层，宽度为 4 (5,3,null,9) 。
 * <p>
 * 示例 2：
 * 输入：root = [1,3,2,5,null,null,9,6,null,7]
 * 输出：7
 * 解释：最大宽度出现在树的第 4 层，宽度为 7 (6,null,null,null,null,null,7) 。
 * <p>
 * 示例 3：
 * 输入：root = [1,3,2,5]
 * 输出：2
 * 解释：最大宽度出现在树的第 2 层，宽度为 2 (3,2) 。
 * <p>
 * 提示：
 * 树中节点的数目范围是 [1, 3000]
 * -100 <= Node.val <= 100
 */
public class Solution662 {

    public static void main(String[] args) {
        TreeNode root = TreeNode.createTestData("[1,3,2,5,3,null,9]");
        System.out.println(widthOfBinaryTree(root));
    }

    //这里使用
    public static int widthOfBinaryTree(TreeNode root) {
        Queue<Node> nodes = new LinkedList<>();
        int max = 0;
        int size;
        Node poll;
        int width = 0;
        nodes.add(new Node(root, 1));
        while (!nodes.isEmpty()) {
            size = nodes.size() - 1;
            for (int i = 0; i <= size; i++) {
                poll = nodes.poll();
                if (null != poll.node.left) {
                    nodes.add(new Node(poll.node.left, poll.i * 2));
                }
                if (null != poll.node.right) {
                    nodes.add(new Node(poll.node.right, poll.i * 2 + 1));
                }
                if (i == 0) {
                    width = poll.i;
                }
                if (i == size) {
                    max = Math.max(poll.i - width + 1, max);
                }
            }
        }
        return max;
    }

    static class Node {
        TreeNode node;
        int i;

        public Node(TreeNode node, int i) {
            this.node = node;
            this.i = i;
        }
    }
}
