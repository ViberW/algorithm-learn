package com.welph.leecode.part_321_340;

import com.welph.leecode.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
 * 这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。
 * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 * <p>
 * .示例 1:
 * .输入: [3,2,3,null,3,null,1]
 * .
 * .     3
 * .    / \
 * .   2   3
 * .    \   \
 * .     3   1
 * .
 * .输出: 7
 * .解释:小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * .
 * .示例 2:
 * .输入: [3,4,5,1,3,null,1]
 * .
 * .    3
 * .    / \
 * .   4   5
 * .  / \   \
 * . 1   3   1
 * .输出: 9
 * .解释:小偷一晚能够盗取的最高金额= 4 + 5 = 9.
 */
public class Solution337 {

    public static void main(String[] args) {
        TreeNode root = TreeNode.createTestData("[3,2,3,null,3,null,1]");
        System.out.println(rob(root));
        TreeNode root1 = TreeNode.createTestData("[3,4,5,1,3,null,1]");
        System.out.println(rob1(root1));
        TreeNode root2 = TreeNode.createTestData("[79,99,77,null,null,null,69,null,60,53,null,73,11,null,null,null,62,27,62,null,null,98,50,null,null,90,48,82,null,null,null,55,64,null,null,73,56,6,47,null,93,null,null,75,44,30,82,null,null,null,null,null,null,57,36,89,42,null,null,76,10,null,null,null,null,null,32,4,18,null,null,1,7,null,null,42,64,null,null,39,76,null,null,6,null,66,8,96,91,38,38,null,null,null,null,74,42,null,null,null,10,40,5,null,null,null,null,28,8,24,47,null,null,null,17,36,50,19,63,33,89,null,null,null,null,null,null,null,null,94,72,null,null,79,25,null,null,51,null,70,84,43,null,64,35,null,null,null,null,40,78,null,null,35,42,98,96,null,null,82,26,null,null,null,null,48,91,null,null,35,93,86,42,null,null,null,null,0,61,null,null,67,null,53,48,null,null,82,30,null,97,null,null,null,1,null,null]");
        System.out.println(rob1(root2));
    }

    /**
     * {@link com.welph.leecode.part_181_200.Solution198}
     * {@link com.welph.leecode.part_201_220.Solution213}
     * ------------------------
     * 当前节点打劫的问题 -先使用深度优先搜索  --todo 超时 意料之中
     */
    public static int rob(TreeNode root) {
        if (null == root) {
            return 0;
        }
        return Math.max(rob(root, true), rob(root, false));
    }

    //父节点是否打劫
    static int rob(TreeNode root, boolean rob) {
        if (null == root) {
            return 0;
        }
        if (rob) {
            return rob(root.left, false) + rob(root.right, false) + root.val;
        } else {
            return Math.max(rob(root.left, false), rob(root.left, true))
                    + Math.max(rob(root.right, false), rob(root.right, true));
        }
    }

    /**
     * 上面的转化为动态规划? -- 这里充其量算是个缓存.  todo 官方给出的动态规划第一版差不多, 但官方第二种思路是后序遍历.节省了hash表的空间
     */
    static int rob1(TreeNode root) {
        //上面的那么些动态规划的数据保存下来
        if (null == root) {
            return 0;
        }
        Map<TreeNode, Integer> positive = new HashMap<>();
        Map<TreeNode, Integer> negative = new HashMap<>();
        return Math.max(rob1(root, true, positive, negative), rob1(root, false, positive, negative));
    }

    static int rob1(TreeNode root, boolean rob,
                    Map<TreeNode, Integer> positive, Map<TreeNode, Integer> negative) {
        if (null == root) {
            return 0;
        }
        Integer r;
        if (rob) {
            r = positive.get(root);
            if (null != r) {
                return r;
            }
        } else {
            r = negative.get(root);
            if (null != r) {
                return r;
            }
        }
        if (rob) {
            r = rob1(root.left, false, positive, negative) + rob1(root.right, false, positive, negative) + root.val;
        } else {
            r = Math.max(rob1(root.left, false, positive, negative), rob1(root.left, true, positive, negative))
                    + Math.max(rob1(root.right, false, positive, negative), rob1(root.right, true, positive, negative));
        }
        if (rob) {
            positive.put(root, r);
        } else {
            negative.put(root, r);
        }
        return r;
    }

}
