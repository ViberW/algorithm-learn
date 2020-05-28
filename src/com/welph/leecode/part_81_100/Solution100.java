package com.welph.leecode.part_81_100;

import com.welph.leecode.common.TreeNode;

import java.util.LinkedList;

/**
 * .给定两个二叉树，编写一个函数来检验它们是否相同。
 * .
 * .如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * .
 * .示例 1:
 * .
 * .输入:       1         1
 * .          / \       / \
 * .         2   3     2   3
 * .
 * .        [1,2,3],   [1,2,3]
 * .
 * .输出: true
 * .示例 2:
 * .
 * .输入:      1          1
 * .          /           \
 * .         2             2
 * .
 * .        [1,2],     [1,null,2]
 * .
 * .输出: false
 * .示例 3:
 * .
 * .输入:       1         1
 * .          / \       / \
 * .         2   1     1   2
 * .
 * .        [1,2,1],   [1,1,2]
 * <p>
 * 输出: false
 */
public class Solution100 {

    public static void main(String[] args) {
        TreeNode p = TreeNode.createTestData("[1,2,1]");
        TreeNode q = TreeNode.createTestData("[1,1,2]");
        System.out.println(isSameTree(p, q));
    }

    /**
     * 递归就好了 时间较快, 内存消耗多, 应该在于栈帧的分配;
     * <p>
     * 当然是用一个{@link LinkedList} 作为节点队列也可以,若是节点的高度很大,建议使用linkedlist
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == q;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

}
