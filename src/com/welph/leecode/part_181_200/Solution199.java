package com.welph.leecode.part_181_200;

import com.welph.leecode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 * .
 * .    1            <---
 * .  /   \
 * . 2     3         <---
 * .  \     \
 * .   5     4       <---
 */
public class Solution199 {

    public static void main(String[] args) {
        TreeNode root = TreeNode.createTestData("[1,2,3,null,5,null,4]");
        System.out.println(rightSideView(root));
    }

    //要考虑到不满足层的数据信息.
    //最好理解的是广度优先搜索   始终取当前层的最后一个节点做为数据
    //这里使用深度优先搜索, 每次以一个作为根节点.
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            rightSideView(root, result, 0);
        }
        return result;
    }

    private static void rightSideView(TreeNode root, List<Integer> result, int depth) {
        if (result.size() <= depth) {
            result.add(root.val);
        } else {
            result.set(depth, root.val);
        }
        if (root.left != null) {
            rightSideView(root.left, result, depth + 1);
        }
        if (root.right != null) {
            rightSideView(root.right, result, depth + 1);
        }
    }
}
