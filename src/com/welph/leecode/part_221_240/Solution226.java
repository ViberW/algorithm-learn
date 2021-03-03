package com.welph.leecode.part_221_240;

import com.welph.leecode.common.TreeNode;

/**
 * 翻转一棵二叉树。
 * 示例：
 * .输入：
 * .
 * .     4
 * .   /   \
 * .  2     7
 * . / \   / \
 * .1   3 6   9
 * .输出：
 * .     4
 * .   /   \
 * .  7     2
 * . / \   / \
 * .9   6 3   1
 * 备注:
 * 这个问题是受到 Max Howell 的 原问题 启发的 ：
 * <p>
 * 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
 */
public class Solution226 {

    public static void main(String[] args) {
        TreeNode.print(invertTree(TreeNode.createTestData("[4,2,7,1,3,6,9]")));
    }

    public static TreeNode invertTree(TreeNode root) {
        if (root != null) {
            TreeNode tmp = root.right;
            root.right = root.left;
            root.left = tmp;
            invertTree(root.left);
            invertTree(root.right);
        }
        return root;
    }
}
