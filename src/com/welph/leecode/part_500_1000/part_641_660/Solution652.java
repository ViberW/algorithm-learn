package com.welph.leecode.part_500_1000.part_641_660;

import com.welph.leecode.common.TreeNode;

import java.util.*;

/**
 * 给你一棵二叉树的根节点 root ，返回所有 重复的子树 。
 * <p>
 * 对于同一类的重复子树，你只需要返回其中任意 一棵 的根结点即可。
 * 如果两棵树具有 相同的结构 和 相同的结点值 ，则认为二者是 重复 的。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,3,4,null,2,4,null,null,4]
 * 输出：[[2,4],[4]]
 * <p>
 * 示例 2：
 * 输入：root = [2,1,1]
 * 输出：[[1]]
 * <p>
 * 示例 3：
 * 输入：root = [2,2,2,3,null,3,null]
 * 输出：[[2,3],[3]]
 * <p>
 * 提示：
 * 树中的结点数在 [1, 5000] 范围内。
 * -200 <= Node.val <= 200
 */
public class Solution652 {

    public static void main(String[] args) {
        TreeNode testData = TreeNode.createTestData("[2,1,11,11,null,1,null]");
        List<TreeNode> trees = new Solution652().findDuplicateSubtrees(testData);
        for (TreeNode tree : trees) {
            TreeNode.print(tree);
        }
    }

    /**
     * 进行中序遍历?
     * {@link com.welph.leecode.part_500_1000.part_601_620.Solution606}
     * 这样就只需要判断 当期那string 是否有一样的
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, TreeNode> map = new HashMap<>();
        Set<TreeNode> nodes = new HashSet<>();
        findDuplicateSubtreesDepth(root, map, nodes);
        return new ArrayList<>(nodes);
    }

    private String findDuplicateSubtreesDepth(TreeNode root, Map<String, TreeNode> map,
                                              Set<TreeNode> nodes) {
        if (root == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder().append(root.val)
                .append(",").append(findDuplicateSubtreesDepth(root.left, map, nodes))
                .append(",").append(findDuplicateSubtreesDepth(root.right, map, nodes));
        String s = builder.toString();
        if (map.containsKey(s)) {
            nodes.add(map.get(s));
        } else {
            map.put(s, root);
        }
        return s;
    }
}
