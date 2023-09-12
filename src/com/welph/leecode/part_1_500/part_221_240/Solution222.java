package com.welph.leecode.part_1_500.part_221_240;

import com.welph.leecode.common.TreeNode;

/**
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，
 * 并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~2h个节点。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,3,4,5,6]
 * 输出：6
 * <p>
 * 示例 2：
 * 输入：root = []
 * 输出：0
 * <p>
 * 示例 3：
 * 输入：root = [1]
 * 输出：1
 * <p>
 * 提示：
 * 树中节点的数目范围是[0, 5 * 104]
 * 0 <= Node.val <= 5 * 104
 * 题目数据保证输入的树是 完全二叉树
 */
public class Solution222 {

    public static void main(String[] args) {
        TreeNode root = TreeNode.createTestData("[1,2,3,4,5,6]");
        System.out.println(countNodes(root));
        TreeNode root1 = TreeNode.createTestData("[1]");
        System.out.println(countNodes(root1));
        TreeNode root2 = TreeNode.createTestData("[1,2,3,4]");
        System.out.println(countNodes(root2));
    }

    public static int countNodes(TreeNode root) {
        //查找到当前存在的位置上
        int r = depth(root, true); //找到最右边
        int l = depth(root, false);//找到最左边
        int count = (int) Math.pow(2, r + 1) - 1; //最右边肯定最短, 统计数量
        TreeNode target = root;
        int stepCount = (int) Math.pow(2, r + 1); //这里是最底下一层满员的总数
        while (r < l) {
            l--;
            r--;
            int tmp = l;
            l = depth(target.right, false); //二分查询右节点是否长度小,
            if (r < l) { //若是根节点的右节点本身形成了高度差
                count += stepCount / 2;  //加上最底层的左半部分
                target = target.right; //当前节点变为右节点
            } else {
                //由于在左边
                l = tmp;
                r = depth(target.left, true);
                if (r == l) {//此时是正好相等的.
                    count += stepCount / 2;
                }
                target = target.left;//若r<l.则说明左半部分
            }
            stepCount = stepCount / 2;
        }
        return count;
    }

    public static int depth(TreeNode root, boolean right) {
        if (root == null) {
            return -1;
        }
        int n = 0;
        TreeNode node = root;
        while ((node = right ? node.right : node.left) != null) {
            n++;
        }
        return n;
    }
}
