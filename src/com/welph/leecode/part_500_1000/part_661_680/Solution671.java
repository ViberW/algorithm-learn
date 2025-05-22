package com.welph.leecode.part_500_1000.part_661_680;

import com.welph.leecode.common.TreeNode;

/**
 * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。
 * 如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
 * 更正式地说，即 root.val = min(root.left.val, root.right.val) 总成立。
 * 给出这样的一个二叉树，你需要输出所有节点中的 第二小的值 。
 * 如果第二小的值不存在的话，输出 -1 。
 *
 * 示例 1：
 * 输入：root = [2,2,5,null,null,5,7]
 * 输出：5
 * 解释：最小的值是 2 ，第二小的值是 5 。
 *
 * 示例 2：
 * 输入：root = [2,2,2]
 * 输出：-1
 * 解释：最小的值是 2, 但是不存在第二小的值。
 *
 * 提示：
 * 树中节点数目在范围 [1, 25] 内
 * 1 <= Node.val <= 231 - 1
 * 对于树中每个节点 root.val == min(root.left.val, root.right.val)
 */
public class Solution671 {

    public static void main(String[] args) {
        System.out.println(findSecondMinimumValue(TreeNode.createTestData("[2,2,5,null,null,5,7]")));
        System.out.println(findSecondMinimumValue(TreeNode.createTestData("[1,1,3,1,1,3,4,3,1,1,1,3,8,4,8,3,3,1,6,2,1]")));
    }

    public static int findSecondMinimumValue(TreeNode root) {
        return findSecondMinimumValue(root, root.val);
    }

    public static int findSecondMinimumValue(TreeNode root, int minVal) {
        if (root == null) return -1;
        if (root.val > minVal) return root.val;
        int lm = findSecondMinimumValue(root.left, minVal);
        int rm = findSecondMinimumValue(root.right, minVal);
        if (lm == -1 && rm == -1) {
            return -1;
        } else if (lm == -1) {
            return rm;
        } else if (rm == -1) {
            return lm;
        } else {
            return Math.min(lm, rm);
        }
    }

}
