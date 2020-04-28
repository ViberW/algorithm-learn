package com.welph.leecode.part_81_100;

import com.welph.leecode.common.TreeNode;

import java.util.*;

/**
 * 给定一个二叉树，返回它的中序 遍历。  左-中-右
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]   --tips 这里面leetcode写的有点问题 应该按照 数组树的写: [1,null,2,null,null,3]
 * ***1
 * **** \
 * **** 2
 * **** /
 * ***3
 * <p>
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class Solution94 {

    public static void main(String[] args) {
        TreeNode node = TreeNode.createTestData("[1,null,2,null,null,3]");
        System.out.println(inorderTraversal(node));
        System.out.println(inorderTraversal2(node));
    }

    /**
     * 那就先来递归吧 hahaha
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traversal(root, result);
        return result;
    }

    private static void traversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        traversal(root.left, result);
        result.add(root.val);
        traversal(root.right, result);
    }

    /**
     * 迭代算法处理 减少入栈的资源消耗和处理
     * <p>
     * -- 不太好保存线程的数据信息  --
     * 好像执行时间长了  -_-.....
     */
    public static List<Integer> inorderTraversal2(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> exists = new HashSet<>();
        queue.addLast(root);
        TreeNode peek;
        while ((peek = queue.peek()) != null) {
            if (!exists.contains(peek)) {
                if (null != peek.right) {
                    queue.pop();
                    queue.addFirst(peek.right);
                    queue.addFirst(peek);
                }
                if (null != peek.left) {
                    queue.addFirst(peek.left);
                }
                exists.add(peek);
            } else {
                result.add(peek.val);
                queue.pop();
            }
        }
        return result;
    }
}
