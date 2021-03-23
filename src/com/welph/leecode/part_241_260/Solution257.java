package com.welph.leecode.part_241_260;

import com.welph.leecode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * 输入:
 * <p>
 * .   1
 * . /   \
 * .2     3
 * . \
 * .  5
 * <p>
 * 输出: ["1->2->5", "1->3"]
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 */
public class Solution257 {

    public static void main(String[] args) {
        TreeNode node = TreeNode.createTestData("[1,2,3,null,5,null,null]");
        System.out.println(binaryTreePaths(node));
    }

    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        binaryTreePaths(root, "", res);
        return res;
    }

    private static void binaryTreePaths(TreeNode node, String s, List<String> res) {
        s += node.val;
        if (node.right == null && node.left == null) {
            res.add(s);
        } else {
            s += "->";
            if (node.right != null) {
                binaryTreePaths(node.right, s, res);
            }
            if (node.left != null) {
                binaryTreePaths(node.left, s, res);
            }
        }
    }
}
