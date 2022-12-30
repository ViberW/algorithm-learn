package com.welph.leecode.part_500_1000.part_621_640;

import com.welph.leecode.common.TreeNode;

/**
 * 给定一个二叉树的根 root 和两个整数 val 和 depth ，在给定的深度 depth 处添加一个值为 val 的节点行。
 * 注意，根节点 root 位于深度 1 。
 * <p>
 * 加法规则如下:
 * 给定整数 depth，对于深度为 depth - 1 的每个非空树节点 cur ，
 * 创建两个值为 val 的树节点作为 cur 的左子树根和右子树根。
 * cur 原来的左子树应该是新的左子树根的左子树。
 * cur 原来的右子树应该是新的右子树根的右子树。
 * 如果 depth == 1 意味着 depth - 1 根本没有深度，那么创建一个树节点，
 * 值 val 作为整个原始树的新根，而原始树就是新根的左子树。
 * <p>
 * 示例 1:
 * 输入: root = [4,2,6,3,1,5], val = 1, depth = 2
 * 输出: [4,1,1,2,null,null,6,3,1,5]
 * <p>
 * 示例 2:
 * 输入: root = [4,2,null,3,1], val = 1, depth = 3
 * 输出:  [4,2,null,1,1,3,null,null,1]
 * <p>
 * 提示:
 * 节点数在 [1, 104] 范围内
 * 树的深度在 [1, 104]范围内
 * -100 <= Node.val <= 100
 * -10^5 <= val <= 10^5
 * 1 <= depth <= the depth of tree + 1
 */
public class Solution623 {

    public static void main(String[] args) {
    }

    public static TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }
        return addOneRow(root, val, depth - 1, 1);
    }

    public static TreeNode addOneRow(TreeNode root, int val, int depth, int current) {
        if (root == null) {
            return root;
        }
        if (current == depth) {
            //新增节点
            TreeNode node = new TreeNode(val);
            node.left = root.left;
            root.left = node;

            node = new TreeNode(val);
            node.right = root.right;
            root.right = node;
            return root;
        }
        addOneRow(root.left, val, depth, current + 1);
        addOneRow(root.right, val, depth, current + 1);
        return root;
    }
}
