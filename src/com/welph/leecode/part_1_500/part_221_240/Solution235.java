package com.welph.leecode.part_1_500.part_221_240;

import com.welph.leecode.common.TreeNode;

/**
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，
 * 最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * 例如，给定如下二叉搜索树: root =[6,2,8,0,4,7,9,null,null,3,5]
 * <p>
 * 示例 1:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 * <p>
 * 示例 2:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 * <p>
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 */
public class Solution235 {

    public static void main(String[] args) {
        TreeNode root = TreeNode.createTestData("[6,2,8,0,4,7,9,null,null,3,5]");

        TreeNode p = findTarget(root, 2);
        TreeNode q = findTarget(root, 4);
        TreeNode.print(lowestCommonAncestor(root, p, q));

        p = findTarget(root, 2);
        q = findTarget(root, 8);
        TreeNode.print(lowestCommonAncestor(root, p, q));
    }

    // 找到对应的p q 一旦发现某个节点分割, 就说明是目标了
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > q.val) {
            TreeNode tmp = p;
            p = q;
            q = tmp;
        }
        while (root.val < p.val || root.val > q.val) {
            root = root.val < p.val ? root.right : root.left;
        }
        return root;
    }

    private static TreeNode findTarget(TreeNode root, int val) {
        while (root != null && root.val != val) {
            if (root.val < val) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return root;
    }

    /* 官方题解 */
    // 我的方法的精简, 其实不用比较p和q的大小
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ancestor = root;
        while (true) {
            if (p.val < ancestor.val && q.val < ancestor.val) {
                ancestor = ancestor.left;
            } else if (p.val > ancestor.val && q.val > ancestor.val) {
                ancestor = ancestor.right;
            } else {
                break;
            }
        }
        return ancestor;
    }

}
