package com.welph.leecode.part_1_500.part_101_120;

import com.welph.leecode.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * . 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * .
 * .
 * .
 * . 示例：
 * . 二叉树：[3,9,20,null,null,15,7],
 * .
 * .     3
 * .    / \
 * .   9  20
 * .     /  \
 * .    15   7
 * . 返回其层次遍历结果：
 * .
 * . [
 * .   [3],
 * .   [9,20],
 * .   [15,7]
 * . ]
 */
public class Solution102 {

    public static void main(String[] args) {
        TreeNode testData = TreeNode.createTestData("[3,9,20,null,null,15,7]");
        System.out.println(levelOrder(testData));
    }

    /**
     * 遍历感觉太消耗了;需要维护的有点多
     * 迭代可能好些
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int expect = 1;
        int next = 0;
        List<Integer> current = new ArrayList<>(expect * 4 / 3 + 2);
        result.add(current);
        while (!queue.isEmpty()) {
            if (expect == 0) {
                if (next == 0) {
                    break;
                }
                expect = next;
                next = 0;
                current = new ArrayList<>(expect * 4 / 3 + 2);
                result.add(current);
            }
            TreeNode pop = queue.pop();
            if (pop.left != null) {
                queue.add(pop.left);
                next++;
            }
            if (pop.right != null) {
                queue.add(pop.right);
                next++;
            }
            current.add(pop.val);
            expect--;
        }
        return result;
    }

}
