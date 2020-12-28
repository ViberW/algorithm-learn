package com.welph.leecode.part_161_180;

import com.welph.leecode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
            //根据最小的root压栈
            queue = new LinkedList<Integer>();
            //这里就需要使用 [中序遍历] morris遍历
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

}
