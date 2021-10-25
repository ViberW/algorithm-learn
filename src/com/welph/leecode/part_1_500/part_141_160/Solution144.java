package com.welph.leecode.part_1_500.part_141_160;

import com.welph.leecode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 */
public class Solution144 {

    public static void main(String[] args) {
        TreeNode testData = TreeNode.createTestData("[1,null,2,null,null,3]");
        System.out.println(new Solution144().preorderTraversal(testData));
    }

    //当前节点 -> 左子树 -> 右子树
    //前序遍历 ===用链表就可以了
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        preorderTraversal(root, result);
        return result;
    }

    private void preorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preorderTraversal(root.left, result);
        preorderTraversal(root.right, result);
    }

    //todo 还可以参考{@link Solution94}的Morris遍历
}
