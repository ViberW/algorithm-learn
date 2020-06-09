package com.welph.leecode.algorithm.tree;

import com.welph.leecode.common.TreeNode;

/**
 * 二叉查找树
 */
public class BinarySearchTree {

    //基于链表实现
    private TreeNode root;

    public TreeNode find(int val) {
        TreeNode node = root;

        while (node != null) {
            if (node.val > val) {
                node = node.left;
            } else if (node.val < val) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    private void insert(int val) {
        if (root == null) {
            root = new TreeNode(val);
            return;
        }
        TreeNode node = root;
        do {
            if (node.val > val) {
                if (node.left == null) {
                    node.left = new TreeNode(val);
                    return;
                }
                node = node.left;
            } else {
                if (node.right == null) {
                    node.right = new TreeNode(val);
                    return;
                }
                node = node.right;

            }
        } while (true);
    }

    public void delete(int val) {
        TreeNode node = root;
        TreeNode parent = null;
        while (node != null) {
            if (node.val > val) {
                parent = node;
                node = node.left;
            } else if (node.val < val) {
                parent = node;
                node = node.right;
            } else {
                break;
            }
        }
        if (node == null) {
            return;
        }
        if (parent != null) {
            //若没有左右节点,则直接删除
            //若有一个子节点,则将其替换
            //若存在左右子节点,则找到右节点的最小节点,替换
            TreeNode temp = null;
            if (node.right != null) {
                TreeNode target = node.right;
                while (target.left != null) {
                    target = target.left;
                }
                temp = target;
            }
            if (temp == null && node.left != null) {
                temp = node.left;
            }
            if (parent.val > val) {
                parent.left = temp;
            } else {
                parent.right = temp;
            }
        } else {
            root = null;
        }
    }
}
