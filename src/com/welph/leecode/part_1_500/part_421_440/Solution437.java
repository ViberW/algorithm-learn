package com.welph.leecode.part_1_500.part_421_440;

import java.util.HashMap;
import java.util.Map;

import com.welph.leecode.common.TreeNode;

/**
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * <p>
 * 示例 1：
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示。
 * <p>
 * 示例 2：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 * <p>
 * 提示:
 * 二叉树的节点个数的范围是 [0,1000]
 * -10^9 <= Node.val <= 10^9
 * -1000 <= targetSum <= 1000
 */
public class Solution437 {

    /*
     * {@link Solution112}
     * {@link Solution113}
     */
    public static void main(String[] args) {
        TreeNode root = TreeNode.createTestData("[10,5,-3,3,2,null,11,3,-2,null,1]");
        System.out.println(pathSum(root, 8));
    }

    /**
     * 返回的结果:
     * 1. 包含当前节点时的数量
     * 2. 不包含当前节点的数量
     */
    public static int pathSum(TreeNode root, int targetSum) {
        Node head = new Node(0);
        return pathSumAllow(root, targetSum, head, head);
    }

    public static int pathSumAllow(TreeNode root, int targetSum, Node head, Node tail) {
        if (root == null) {
            return 0;
        }
        int ret = 0;
        // 每增加一个深度 就增加一个node记录数值
        tail.next = new Node(0);
        Node cur = head.next;
        while (cur != null) {
            cur.val += root.val; // 相当于是前缀和了, 相当于是包含root节点的往上不断找符合的前缀和
            if (cur.val == targetSum) {
                ret++; // 因为val是存在负值. 所以还要继续遍历
            }
            cur = cur.next;
        }
        Node tmp = tail;
        tail = tail.next;
        ret += pathSumAllow(root.right, targetSum, head, tail);
        ret += pathSumAllow(root.left, targetSum, head, tail);
        tmp.next = null;
        cur = head.next;
        while (cur != null) {
            cur.val -= root.val;
            cur = cur.next;
        }
        return ret;
    }

    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    /* 官方题解 */
    // 深度优先搜索 比上面更简洁
    public int pathSum1(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        // 以当前节点为最高节点
        int ret = rootSum(root, targetSum);
        ret += pathSum1(root.left, targetSum);
        ret += pathSum1(root.right, targetSum);
        return ret;
    }

    public int rootSum(TreeNode root, int targetSum) {
        int ret = 0;

        if (root == null) {
            return 0;
        }
        int val = root.val;
        if (val == targetSum) {
            ret++;
        }

        ret += rootSum(root.left, targetSum - val);
        ret += rootSum(root.right, targetSum - val);
        return ret;
    }

    // 前缀和
    public int pathSum2(TreeNode root, int targetSum) {
        Map<Long, Integer> prefix = new HashMap<Long, Integer>();
        prefix.put(0L, 1);
        return dfs(root, prefix, 0, targetSum);
    }

    public int dfs(TreeNode root, Map<Long, Integer> prefix, long curr, int targetSum) {
        if (root == null) {
            return 0;
        }

        int ret = 0;
        curr += root.val;
        // root -> ... -> pi -> ... -> pi+1 -> node
        // 若存在前缀和pi为 curr - targetSum, 那么pi+1->...->node总和为 targetSum
        // 因为 [root->node] = [root->pi] + [pi+1->node]
        // 对应 curr = (curr - targetSum) + targetSum
        ret = prefix.getOrDefault(curr - targetSum, 0); // 关键点, 牛的
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);
        ret += dfs(root.left, prefix, curr, targetSum);
        ret += dfs(root.right, prefix, curr, targetSum);
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);

        return ret;
    }

}
