package com.welph.leecode.part_1_500.part_141_160;

import com.welph.leecode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的后序遍历
 */
public class Solution145 {

    public static void main(String[] args) {
        TreeNode testData = TreeNode.createTestData("[1,null,2,null,null,3]");
        System.out.println(new Solution145().postorderTraversal(testData));
    }

    //左子树 -> 右子树 -> 当前节点
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        postorderTraversal(root, result);
        return result;
    }

    private void postorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        postorderTraversal(root.left, result);
        postorderTraversal(root.right, result);
        result.add(root.val);
    }

    //todo 还可以参考{@link Solution94}的Morris遍历
}
