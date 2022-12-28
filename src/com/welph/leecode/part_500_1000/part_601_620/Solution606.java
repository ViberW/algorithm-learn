package com.welph.leecode.part_500_1000.part_601_620;

import com.welph.leecode.common.TreeNode;

/**
 * 给你二叉树的根节点 root ，请你采用前序遍历的方式，将二叉树转化为一个由括号和整数组成的字符串，返回构造出的字符串。
 * 空节点使用一对空括号对 "()" 表示，转化后需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,3,4]
 * 输出："1(2(4))(3)"
 * 解释：初步转化后得到 "1(2(4)())(3()())" ，但省略所有不必要的空括号对后，字符串应该是"1(2(4))(3)" 。
 * <p>
 * 示例 2：
 * 输入：root = [1,2,3,null,4]
 * 输出："1(2()(4))(3)"
 * 解释：和第一个示例类似，但是无法省略第一个空括号对，否则会破坏输入与输出一一映射的关系。
 * <p>
 * 提示：
 * 树中节点的数目范围是 [1, 10^4]
 * -1000 <= Node.val <= 1000
 */
public class Solution606 {

    public static void main(String[] args) {
        System.out.println(tree2str(TreeNode.createTestData("[1,2,3,4,null,null,null]")));
    }

    public static String tree2str(TreeNode root) {
        String result = "";
        if (root == null) {
            return result;
        }
        result += root.val;
        if (root.left != null && root.right != null) {
            result += "(" + tree2str(root.left) + ")";
            result += "(" + tree2str(root.right) + ")";
        } else if (root.left != null) {
            result += "(" + tree2str(root.left) + ")";
        } else if (root.right != null) {
            result += "()(" + tree2str(root.right) + ")";
        }
        return result;
    }
}
