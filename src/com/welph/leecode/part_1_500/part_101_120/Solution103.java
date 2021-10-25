package com.welph.leecode.part_1_500.part_101_120;

import com.welph.leecode.common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * .给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * .
 * .例如：
 * .给定二叉树 [3,9,20,null,null,15,7],
 * .
 * .    3
 * .   / \
 * .  9  20
 * .    /  \
 * .   15   7
 * .返回锯齿形层次遍历如下：
 * .
 * .[
 * .  [3],
 * .  [20,9],
 * .  [15,7]
 * .]
 */
public class Solution103 {

    public static void main(String[] args) {
        TreeNode testData = TreeNode.createTestData("[3,9,20,null,null,15,7]");
        TreeNode testData2 = TreeNode.createTestData("[1,2,3,4,null,null,5]");
        TreeNode testData3 = TreeNode.createTestData("[]");
        System.out.println(zigzagLevelOrder(testData));
        System.out.println(zigzagLevelOrder(testData2));
        System.out.println(zigzagLevelOrder(testData3));
    }

    /**
     * 不同{@link Solution102} 好像使用递归方式可能不错
     * linkedlist.addfirst()
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root != null) {
            zigzagLevelOrder(Collections.singletonList(root), result, true);
        }
        return result;
    }

    public static void zigzagLevelOrder(List<TreeNode> nodes, List<List<Integer>> result, boolean left) {
        if (nodes.isEmpty()) {
            return;
        }
        List<Integer> values = new ArrayList<>(nodes.size());
        LinkedList<TreeNode> nexts = new LinkedList<>();
        for (TreeNode node : nodes) {
            values.add(node.val);
            if (left) {
                if (node.left != null) {
                    nexts.addFirst(node.left);
                }
                if (node.right != null) {
                    nexts.addFirst(node.right);
                }
            } else {
                if (node.right != null) {
                    nexts.addFirst(node.right);
                }
                if (node.left != null) {
                    nexts.addFirst(node.left);
                }
            }
        }
        result.add(values);
        zigzagLevelOrder(nexts, result, !left);
    }
}
