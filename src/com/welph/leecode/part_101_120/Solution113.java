package com.welph.leecode.part_101_120;

import com.welph.leecode.common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * .给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * .<p>
 * .说明: 叶子节点是指没有子节点的节点。
 * .<p>
 * .示例:
 * .给定如下二叉树，以及目标和 sum = 22，
 * .<p>
 * .              5
 * .             / \
 * .            4   8
 * .           /   / \
 * .          11  13  4
 * .         /  \    / \
 * .        7    2  5   1
 * .返回:
 * .<p>
 * .[
 * .   [5,4,11,2],
 * .   [5,8,4,5]
 * .]
 */
public class Solution113 {

    public static void main(String[] args) {
        TreeNode root = TreeNode.createTestData("[5,4,8,11,null,13,4,7,2,null,null,null,null,5,1]");
        System.out.println(pathSum(root, 22));
    }

    /**
     * 从{@link Solution112} 就大致能够得到这题的解法
     */
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> lists = hasPathSum(root, 0, sum);
        return lists == null ? Collections.emptyList() : lists;
    }

    public static List<List<Integer>> hasPathSum(TreeNode root, int curr, int sum) {
        curr = curr + root.val;
        List<List<Integer>> lists = null;
        List<List<Integer>> res;
        if (root.left != null && (res = hasPathSum(root.left, curr, sum)) != null) {
            res.forEach(integers -> ((LinkedList) integers).addFirst(root.val));
            lists = res;
        }
        if (root.right != null && (res = hasPathSum(root.right, curr, sum)) != null) {
            res.forEach(integers -> ((LinkedList) integers).addFirst(root.val));
            if (null == lists) {
                lists = res;
            } else {
                lists.addAll(res);
            }
        }
        if (root.left == null && root.right == null && curr == sum) {
            return new ArrayList<List<Integer>>() {{
                add(new LinkedList<Integer>() {{
                    add(root.val);
                }});
            }};
        }
        return lists;
    }
}
