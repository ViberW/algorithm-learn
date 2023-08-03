package com.welph.leecode.part_1_500.part_101_120;

import com.welph.leecode.common.TreeNode;

import java.util.LinkedList;

/**
 * .给定一个二叉树，检查它是否是镜像对称的。
 * .
 * .例如，二叉树[1,2,2,3,4,4,3] 是对称的。
 * .
 * .    1
 * .   / \
 * .  2   2
 * . / \ / \
 * .3  4 4  3
 * .
 * .
 * .但是下面这个[1,2,2,null,3,null,3] 则不是镜像对称的:
 * .
 * .    1
 * .   / \
 * .  2   2
 * .   \   \
 * .   3    3
 * .
 * .进阶：
 * .你可以运用递归和迭代两种方法解决这个问题吗？
 */
public class Solution101 {

    public static void main(String[] args) {
        TreeNode testData = TreeNode.createTestData("[1,2,2,3,4,4,3]");
        TreeNode testData2 = TreeNode.createTestData("[1,2,2,null,3,null,3]");
        System.out.println(isSymmetric(testData));
        System.out.println(isSymmetric(testData2));
        System.out.println(isSymmetric2(testData));
        System.out.println(isSymmetric2(testData2));
    }

    /**
     * 递归:
     * //类似于{@link com.welph.leecode.part_1_500.part_81_100.Solution100} 中的两个节点是否一致;
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    public static boolean isSymmetric(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == q;
        }
        return p.val == q.val && isSymmetric(p.left, q.right) && isSymmetric(p.right, q.left);
    }

    /**
     * 迭代
     */
    public static boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }
        LinkedList<TreeNode> left = new LinkedList<>();
        left.add(root.left);
        LinkedList<TreeNode> right = new LinkedList<>();
        right.add(root.right);

        while (!left.isEmpty() && !right.isEmpty()) {
            TreeNode l = left.pop();
            TreeNode r = right.pop();
            if (l == null || r == null) {
                return l == r;
            }
            if (l.val != r.val) {
                return false;
            }
            left.add(l.left);
            left.add(l.right);
            right.add(r.right);
            right.add(r.left);
        }
        return left.isEmpty() && right.isEmpty();
    }
}
