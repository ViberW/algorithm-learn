package com.welph.leecode.part_1_500.part_81_100;

import com.welph.leecode.common.TreeNode;

import java.util.*;

/**
 * 二叉搜索树中的两个节点被错误地交换。
 * <p>
 * 请在不改变其结构的情况下，恢复这棵树。
 * <p>
 * 示例 1:
 * <p>
 * .输入: [1,3,null,null,2]
 * .
 * .   1
 * .  /
 * . 3
 * .  \
 * .   2
 * .
 * .输出: [3,1,null,null,2]
 * .
 * .   3
 * .  /
 * . 1
 * .  \
 * .   2
 * .示例 2:
 * .
 * .输入: [3,1,4,null,null,2]
 * .
 * .  3
 * . / \
 * .1   4
 * .   /
 * .  2
 * .
 * .输出: [2,1,4,null,null,3]
 * .
 * .  2
 * . / \
 * .1   4
 * .   /
 * .  3
 * 进阶:
 * <p>
 * 使用 O(n) 空间复杂度的解法很容易实现。
 * 你能想出一个只使用常数空间的解决方案吗？
 */
public class Solution99 {

    public static void main(String[] args) {
        TreeNode testData = TreeNode.createTestData("[1,3,null,null,2]");
        recoverTree(testData);
        TreeNode.printFront(testData);
    }

    /**
     * O(n)空间的方法应该就是中序遍历,添加到list中保存起来.一旦发现有问题,则交换两个节点
     * 深度优先搜索?
     * <p>
     * 看了题解的.emmm
     */
    public static void recoverTree(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode x = null, y = null, pred = null;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.removeLast();
            if (pred != null && root.val < pred.val) {
                y = root;
                //第一次找到了后 保存两者之间的数据 x =pred y = root
                if (x == null) x = pred;
                    //第二次找到后,则 y = current-root x =若存在,则不处理.说明,是交换,最开始那两个点就好了
                else break;
            }
            pred = root;
            root = root.right;
        }

        swap(x, y);
    }

    public static void swap(TreeNode a, TreeNode b) {
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }

    /**
     * 还可以使用{@link Solution94}的Morris遍历
     */
}
