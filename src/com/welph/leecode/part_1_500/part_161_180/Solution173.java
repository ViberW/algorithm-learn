package com.welph.leecode.part_1_500.part_161_180;

import com.welph.leecode.common.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 * 调用 next() 将返回二叉搜索树中的下一个最小的数
 */
public class Solution173 {

    public static void main(String[] args) {
        TreeNode root = TreeNode.createTestData("[7,3,15,null,null,9,20]");
        BSTIterator obj = new BSTIterator(root);
        System.out.println(obj.next());
        System.out.println(obj.next());
        System.out.println(obj.next());
        System.out.println(obj.next());
        System.out.println(obj.hasNext());
    }

    static class BSTIterator {

        LinkedList<Integer> queue;

        public BSTIterator(TreeNode root) {
            // 根据最小的root压栈
            queue = new LinkedList<Integer>();
            // 这里就需要使用 [中序遍历] morris遍历
            TreeNode curr = root;
            TreeNode pre;
            while (curr != null) {
                if (curr.left != null) {
                    pre = curr.left;
                    while (pre.right != null) {
                        pre = pre.right;
                    }
                    pre.right = curr;
                    TreeNode tmp = curr;

                    curr = curr.left;
                    tmp.left = null;
                } else {
                    queue.add(curr.val);
                    curr = curr.right;
                }
            }
        }

        public int next() {
            return queue.pop();
        }

        public boolean hasNext() {
            return !queue.isEmpty();
        }
    }

    /* 官方题解 */

    class BSTIterator1 {
        private TreeNode cur;
        private Deque<TreeNode> stack;

        public BSTIterator1(TreeNode root) {
            cur = root;
            stack = new LinkedList<TreeNode>();
        }

        public int next() {
            while (cur != null) {//使用stack存储,这里的处理方法也是类似上面的mirros遍历了
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            int ret = cur.val;
            cur = cur.right;
            return ret;
        }

        public boolean hasNext() {
            return cur != null || !stack.isEmpty();
        }
    }

}
