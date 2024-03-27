package com.welph.leecode.part_500_1000.part_501_520;

import com.welph.leecode.common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。
 * 如果树中有不止一个众数，可以按 任意顺序 返回。
 * 假定 BST 满足如下定义：
 * <p>
 * 结点左子树中所含节点的值 小于等于 当前节点的值
 * 结点右子树中所含节点的值 大于等于 当前节点的值
 * 左子树和右子树都是二叉搜索树
 * <p>
 * 示例 1：
 * 输入：root = [1,null,2,2]
 * 输出：[2]
 * <p>
 * 示例 2：
 * 输入：root = [0]
 * 输出：[0]
 * <p>
 * 提示：
 * 树中节点的数目在范围 [1, 10^4] 内
 * -10^5 <= Node.val <= 10^5
 * <p>
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 */
public class Solution501 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findMode(TreeNode.createTestData("[1,null,2,null,null,2]"))));
    }

    /**
     * {@link com.welph.leecode.part_1_500.part_81_100.Solution94} Morris遍历
     */
    public static int[] findMode(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode curr = root;
        TreeNode pre;
        int maxCount = 1;
        int count = 0;
        int laxValue = Integer.MIN_VALUE;
        while (curr != null) {
            if (curr.left == null) {
                if (laxValue == curr.val) {
                    count++;
                } else {
                    if (count > maxCount) {
                        result.clear();
                        result.add(laxValue);
                        maxCount = count;
                    } else if (count == maxCount) {
                        result.add(laxValue);
                    }
                    laxValue = curr.val;
                    count = 1;
                }
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
        if (count > maxCount) {
            result.clear();
            result.add(laxValue);
        } else if (count == maxCount) {
            result.add(laxValue);
        }
        return result.stream().mapToInt(value -> value).toArray();
    }

    /* 官方题解 */

    int base, count, maxCount;
    List<Integer> answer = new ArrayList<Integer>();

    // Morris遍历
    public int[] findMode2(TreeNode root) {
        TreeNode cur = root, pre = null;
        while (cur != null) {
            if (cur.left == null) {
                update(cur.val);
                cur = cur.right;
                continue;
            }
            pre = cur.left;
            while (pre.right != null && pre.right != cur) {
                pre = pre.right;
            }
            if (pre.right == null) {
                pre.right = cur;
                cur = cur.left;
            } else {
                pre.right = null;
                update(cur.val);
                cur = cur.right;
            }
        }
        int[] mode = new int[answer.size()];
        for (int i = 0; i < answer.size(); ++i) {
            mode[i] = answer.get(i);
        }
        return mode;
    }

    public void update(int x) {
        if (x == base) {
            ++count;
        } else {
            count = 1;
            base = x;
        }
        if (count == maxCount) {
            answer.add(base);
        }
        if (count > maxCount) {
            maxCount = count;
            answer.clear();
            answer.add(base);
        }
    }

}
