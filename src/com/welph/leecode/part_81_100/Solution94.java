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

    /**
     * 莫里斯遍历(Morris)
     * 我们使用一种新的数据结构：线索二叉树。方法如下：
     * Step 1: 将当前节点current初始化为根节点
     * Step 2: While current不为空，
     * 若current没有左子节点
     * .   a. 将current添加到输出
     * .   b. 进入右子树，亦即, current = current.right
     * 否则
     * .   a. 在current的左子树中，令current成为最右侧节点的右子节点
     * .   b. 进入左子树，亦即，current = current.left
     * <p>
     * 下面这种遍历会破会树的结构, 若是需要恢复,则需要.加一层判断, 父节点,与当前的节点是否一致;
     */
    public static List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode curr = root;
        TreeNode pre;
        while (curr != null) {
            if (curr.left == null) {
                res.add(curr.val);
                curr = curr.right; // move to next right node
            } else { // has a left subtree
                pre = curr.left;
                while (pre.right != null) { // find rightmost
                    pre = pre.right;
                }
                pre.right = curr; // put cur after the pre node
                TreeNode temp = curr; // store cur node
                curr = curr.left; // move cur to the top of the new tree
                temp.left = null; // original cur left be null, avoid infinite loops
            }
        }
        return res;
    }
}
