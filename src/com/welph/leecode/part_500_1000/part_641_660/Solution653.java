package com.welph.leecode.part_500_1000.part_641_660;

import com.welph.leecode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉搜索树 root 和一个目标结果 k，如
 * 果二叉搜索树中存在两个元素且它们的和等于给定的目标结果  则返回 true。
 * <p>
 * 示例 1：
 * 输入: root = [5,3,6,2,4,null,7], k = 9
 * 输出: true
 * <p>
 * 示例 2：
 * 输入: root = [5,3,6,2,4,null,7], k = 28
 * 输出: false
 * <p>
 * 提示:
 * 二叉树的节点个数的范围是  [1, 10^4].
 * -10^4 <= Node.val <= 10^4
 * 题目数据保证，输入的 root 是一棵 有效 的二叉搜索树
 * -10^5 <= k <= 10^5
 */
public class Solution653 {

    public static void main(String[] args) {
        TreeNode root = TreeNode.createTestData("[5,3,6,2,4,null,7]");
        System.out.println(findTarget(root, 9));
        root = TreeNode.createTestData("[2,1,3]");
        System.out.println(findTarget(root, 3));
    }

    /**
     * 后序遍历, 则是个升序的.  相当于从升序当中 找出target值
     * {@link com.welph.leecode.part_1_500.part_161_180.Solution167}
     * {@link com.welph.leecode.part_1_500.part_81_100.Solution94}
     */
    public static boolean findTarget(TreeNode root, int k) {
        //尝试使用morris遍历处理
        List<Integer> res = new ArrayList<>();
        TreeNode curr = root;
        TreeNode pre;
        while (curr != null) {
            if (curr.left == null) {
                //对于 curr
                res.add(curr.val);
                curr = curr.right;
            } else {
                pre = curr.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = curr;
                TreeNode tmp = curr;
                curr = curr.left;
                tmp.left = null;
            }
        }
        int l = 0;
        int r = res.size() - 1;
        int val;
        while (l < r) {
            val = res.get(l) + res.get(r);
            if (val == k) {
                return true;
            } else if (val > k) {
                r--;
            } else {
                l++;
            }
        }
        return false;
    }

}
