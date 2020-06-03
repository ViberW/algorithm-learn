package com.welph.leecode.part_101_120;

import com.welph.leecode.common.TreeNode;

/**
 * . 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * .
 * . 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * .
 * . 示例:
 * .
 * . 给定有序数组: [-10,-3,0,5,9],
 * .
 * . 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 * .
 * .       0
 * .      / \
 * .    -3   9
 * .    /   /
 * .  -10  5
 */
public class Solution108 {

    public static void main(String[] args) {
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode node = sortedArrayToBST(nums);
        TreeNode.printFront(node);
    }

    /**
     * 有序数组为中序,那个在中间节点前后面的分别处理就好了.
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length);
    }

    public static TreeNode sortedArrayToBST(int[] nums, int l, int r) {
        if (l >= r) {
            return null;
        }
        int middle = (l + r) / 2;
        TreeNode root = new TreeNode(nums[middle]);
        root.left = sortedArrayToBST(nums, l, middle);
        root.right = sortedArrayToBST(nums, middle + 1, r);
        return root;
    }

    /**
     * 选择中间位置的左边或者右边,皆是可以的; 保证数量不超过两倍即可;
     */
}
