package com.welph.leecode.part_1_500.part_81_100;

import com.welph.leecode.common.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 * <p>
 * 输入:
 * .    2
 * .   / \
 * .  1   3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:
 * .    5
 * .   / \
 * .  1   4
 * .     / \
 * .    3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 */
public class Solution98 {

    public static void main(String[] args) {
        System.out.println(isValidBST(TreeNode.createTestData("[2,1,3]")));
        System.out.println(isValidBST(TreeNode.createTestData("[10,5,15,null,null,6,20]")));
        //[3,1,5,0,2,4,6,null,null,null,3]
        System.out.println(isValidBST(TreeNode.createTestData("[3,1,5,0,2,4,6,null,null,null,3]")));
    }

    /**
     * 根据{@link Solution94}题目来的  还是看下面的方法3吧||||||
     *
     * @param root
     * @return
     */
    public static boolean isValidBST(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> exists = new HashSet<>();
        queue.addLast(root);
        TreeNode peek;
        double lastValue = -Double.MAX_VALUE;
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
                if (peek.val <= lastValue) {
                    return false;
                }
                lastValue = peek.val;
                queue.pop();
            }
        }
        return true;
    }

    /**
     * 思考: 首先能想到的就是
     * 深度搜索 -- 递归  --上下层级是有关联的.
     * <p>
     * 左子树的值 一定在于根节点的范围之间.
     * 右子树 根节点.
     * <p>
     */
    public static boolean isValidBST2(TreeNode root) {
        return helper(root, null, null);
    }

    public static boolean helper(TreeNode node, Integer lower, Integer upper) {
        if (node == null) return true;

        int val = node.val;
        if (lower != null && val <= lower) return false;
        if (upper != null && val >= upper) return false;

        if (!helper(node.right, val, upper)) return false;
        if (!helper(node.left, lower, val)) return false;
        return true;
    }

    /**
     * 其实和{@link Solution94}类似 中序遍历后是否递增.
     */
    public boolean isValidBST3(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;
        }
        return true;
    }

}
