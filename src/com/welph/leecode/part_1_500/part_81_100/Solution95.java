package com.welph.leecode.part_1_500.part_81_100;

import com.welph.leecode.common.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个整数 n，生成所有由 1 ...n 为节点所组成的二叉搜索树。
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出:
 * [
 * [1,null,3,2],
 * [3,2,null,1],
 * [3,1,null,null,2],
 * [2,1,3],
 * [1,null,2,null,3]
 * ]
 * 解释:
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 * <p>
 * 1 3 3 2 1
 * \ / / / \ \
 * 3 2 1 1 3 2
 * / / \ \
 * 2 1 2 3
 */
public class Solution95 {

    public static void main(String[] args) {
        List<TreeNode> treeNodes = generateTrees(3);
        for (TreeNode treeNode : treeNodes) {
            TreeNode.printFront(treeNode);
            System.out.println("========");
        }
    }

    /**
     * 动态规划 执行时间 99% 但由于java不停的创建node 空间占用高 15%
     */
    public static List<TreeNode> generateTrees(int n) {
        /**
         * 思路:
         * i=k 所有能够支持的二叉树;
         * 都能通过k-1得到;
         * 假设k-1有arr[] = [[x1,x2,x3],[y1,y2,y3]]
         * 那么k,有arr[0]遍历插入 当前节点值k,直到右子树没有值
         * 继续下一个.
         *
         */
        LinkedList<TreeNode> results = new LinkedList<>();
        if (n < 1) {
            return results;
        }
        results.add(new TreeNode(1));
        int size;
        TreeNode peek;
        TreeNode origin;
        TreeNode tmp;
        TreeNode temp;
        for (int i = 2; i <= n; i++) {
            size = results.size();
            for (int k = 0; k < size; k++) {
                TreeNode node = new TreeNode(i);
                node.left = results.peek();
                results.add(node);

                int cycle = 0;
                do {
                    peek = results.peek();
                    origin = new TreeNode(peek.val);
                    origin.left = peek.left;

                    tmp = origin;
                    for (int m = 0; m < cycle; m++) {
                        peek = peek.right;
                        temp = new TreeNode(peek.val);
                        temp.left = peek.left;
                        tmp.right = temp;
                        tmp = temp;
                    }

                    if (peek.right != null) {
                        tmp.right = new TreeNode(i);
                        tmp.right.left = peek.right;
                        results.add(origin);
                    } else {
                        tmp.right = new TreeNode(i);
                        results.add(origin);
                        break;
                    }
                    cycle++;
                } while (true);
                results.pop();
            }
        }
        return results;
    }

    /*
     * 官方题解- 回溯法
     * generateTrees(start, end) 可以被拆分(start, i-1)左子树和(i+1, end)右子树以及i的根节点
     */
    public List<TreeNode> generateTrees2(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<TreeNode>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }

        // 枚举可行根节点
        for (int i = start; i <= end; i++) {
            // 获得所有可行的左子树集合
            List<TreeNode> leftTrees = generateTrees(start, i - 1);

            // 获得所有可行的右子树集合
            List<TreeNode> rightTrees = generateTrees(i + 1, end);

            // 从左子树集合中选出一棵左子树，从右子树集合中选出一棵右子树，拼接到根节点上
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode currTree = new TreeNode(i);
                    currTree.left = left;
                    currTree.right = right;
                    allTrees.add(currTree);
                }
            }
        }
        return allTrees;
    }
}
