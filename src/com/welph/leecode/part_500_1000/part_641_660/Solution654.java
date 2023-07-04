package com.welph.leecode.part_500_1000.part_641_660;

import com.welph.leecode.common.TreeNode;

import java.util.Arrays;
import java.util.Stack;

/**
 * 给定一个不重复的整数数组 nums 。 最大二叉树 可以用下面的算法从 nums 递归地构建:
 * <p>
 * 创建一个根节点，其值为 nums 中的最大值。
 * 递归地在最大值 左边 的 子数组前缀上 构建左子树。
 * 递归地在最大值 右边 的 子数组后缀上 构建右子树。
 * 返回 nums 构建的 最大二叉树 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,2,1,6,0,5]
 * 输出：[6,3,5,null,2,0,null,null,1]
 * 解释：递归调用如下所示：
 * - [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
 * - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
 * - 空数组，无子节点。
 * - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
 * - 空数组，无子节点。
 * - 只有一个元素，所以子节点是一个值为 1 的节点。
 * - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
 * - 只有一个元素，所以子节点是一个值为 0 的节点。
 * - 空数组，无子节点。
 * <p>
 * 示例 2：
 * 输入：nums = [3,2,1]
 * 输出：[3,null,2,null,1]
 * <p>
 * 提示：
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 * nums 中的所有整数 [互不相同]
 */
public class Solution654 {

    public static void main(String[] args) {
        TreeNode.print(constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5}));
//        TreeNode.print(constructMaximumBinaryTree(new int[]{3, 2, 1}));
    }

    /**
     * 分治, 唯一的是怎么快速找到一段的最大值呢
     * -------------这里速度太慢了, 需要优化怎么快速知道一段的最大值
     */
    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums, 0, nums.length - 1);
    }

    private static TreeNode constructMaximumBinaryTree(int[] nums, int l, int r) {
        //找到最大值的index
        int index = l;
        for (int i = l; i <= r; i++) {
            if (nums[i] > nums[index]) {
                index = i;
            }
        }
        TreeNode node = new TreeNode(nums[index]);
        if (index > l) {
            node.left = constructMaximumBinaryTree(nums, l, index - 1);
        }
        if (index < r) {
            node.right = constructMaximumBinaryTree(nums, index + 1, r);
        }
        return node;
    }

    /**
     * todo 参照题解 有个比较的[单调栈]的实用例子
     */
    public static TreeNode constructMaximumBinaryTree2(int[] nums) {
        Stack<Integer> stack = new Stack<>();//单调减
        int n = nums.length;
        int[] right = new int[n];
        int[] left = new int[n];
        Arrays.fill(right, -1);
        Arrays.fill(left, -1);
        TreeNode[] tree = new TreeNode[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new TreeNode(nums[i]);
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                right[stack.pop()] = i; //比pop大的右边的第一个值
            }
            if (!stack.isEmpty()) {
                left[i] = stack.peek(); //比i大的左边的第一个值
            }
            stack.push(i);//单调减
        }
        //i节点 左节点为左边第一个比它大的值  右节点为右侧第一个比它大的值
        TreeNode root = null;
        for (int i = 0; i < n; i++) {
            if (right[i] == -1 && left[i] == -1) {//最大值肯定两者都没有
                root = tree[i];
            } else if (right[i] == -1 || (left[i] != -1 && nums[left[i]] < nums[right[i]])) {
                //右边没有比它大的  或者 左边有比i大的值   那么 left[i] .right = i
                tree[left[i]].right = tree[i];
            } else {
                tree[right[i]].left = tree[i];
            }
        }
        return root;
    }

}
