package com.welph.leecode.part_500_1000.part_641_660;

import com.welph.leecode.common.TreeNode;

import java.util.*;

/**
 * 给你一棵二叉树的根节点 root ，请你构造一个下标从 0 开始、大小为 m x n 的字符串矩阵 res ，
 * 用以表示树的 格式化布局 。构造此格式化布局矩阵需要遵循以下规则：
 * <p>
 * 树的 高度 为 height ，矩阵的行数 m 应该等于 height + 1 。
 * 矩阵的列数 n 应该等于 2height+1 - 1 。
 * 根节点 需要放置在 顶行 的 正中间 ，对应位置为 res[0][(n-1)/2] 。
 * 对于放置在矩阵中的每个节点，设对应位置为 res[r][c] ，
 * 将其左子节点放置在 res[r+1][c-2height-r-1] ，
 * 右子节点放置在 res[r+1][c+2height-r-1] 。
 * 继续这一过程，直到树中的所有节点都妥善放置。
 * 任意空单元格都应该包含空字符串 "" 。
 * 返回构造得到的矩阵 res 。
 * <p>
 * 示例 1：
 * 输入：root = [1,2]
 * 输出：
 * [["","1",""],
 * ["2","",""]]
 * <p>
 * 示例 2：
 * 输入：root = [1,2,3,null,4]
 * 输出：
 * [["","","","1","","",""],
 * ["","2","","","","3",""],
 * ["","","4","","","",""]]
 * <p>
 * 提示：
 * 树中节点数在范围 [1, 210] 内
 * -99 <= Node.val <= 99
 * 树的深度在范围 [1, 10] 内
 */
public class Solution655 {

    public static void main(String[] args) {
        TreeNode root = TreeNode.createTestData("[5,3,6,2,4,null,7]");
        System.out.println(printTree(root));
    }

    public static List<List<String>> printTree(TreeNode root) {
        int height = calculateHeight(root);
        List<List<String>> ret = new ArrayList<>();
        int m = height + 1;
        int n = (1 << (height + 1)) - 1;
        List<String> item;
        for (int i = 0; i < m; i++) {
            item = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                item.add("");
            }
            ret.add(item);
        }
        printTree(root, ret, 0, (n - 1) / 2, height);
        return ret;
    }

    private static void printTree(TreeNode root, List<List<String>> ret, int r, int c, int height) {
        ret.get(r).set(c, Integer.toString(root.val));
        if (root.left != null) {
            printTree(root.left, ret, r + 1, c - (1 << (height - r - 1)), height);
        }
        if (root.right != null) {
            printTree(root.right, ret, r + 1, c + (1 << (height - r - 1)), height);
        }
    }

    private static int calculateHeight(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int height = -1;
        int size;
        TreeNode poll;
        while (!queue.isEmpty()) {
            height++;
            size = queue.size();
            for (int i = 0; i < size; i++) {
                poll = queue.poll();
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
        }
        return height;
    }
}
