package com.welph.leecode.part_101_120;

import com.welph.leecode.common.TreeNode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * .给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * <p>
 * .例如：
 * .给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * .    3
 * .   / \
 * .  9  20
 * .    /  \
 * .   15   7
 * .返回其自底向上的层次遍历为：
 * <p>
 * .[
 * .  [15,7],
 * .  [9,20],
 * .  [3]
 * .]
 */
public class Solution107 {

    public static void main(String[] args) {
        TreeNode testData = TreeNode.createTestData("[3,9,20,null,null,15,7]");
        System.out.println(levelOrderBottom(testData));
    }

    /**
     * 前序遍历的变种, 使用linkedlist接收
     */
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> results = new LinkedList<>();
        if (null != root) {
            LinkedList<TreeNode> queue = new LinkedList<>();
            results.add(Collections.singletonList(root.val));
            queue.add(root);
            int size = 1;
            List<Integer> curr = new LinkedList<>();
            TreeNode pop;
            do {
                pop = queue.pop();
                if (null != pop.left) {
                    curr.add(pop.left.val);
                    queue.add(pop.left);
                }
                if (null != pop.right) {
                    curr.add(pop.right.val);
                    queue.add(pop.right);
                }
                size--;
                if (size == 0) {
                    if (curr.isEmpty()) {
                        break;
                    }
                    results.addFirst(curr);
                    size = results.peek().size();
                    curr = new LinkedList<>();
                }
            } while (size != 0);
        }
        return results;
    }
}
