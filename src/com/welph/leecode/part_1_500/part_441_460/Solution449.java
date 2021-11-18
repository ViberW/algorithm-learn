package com.welph.leecode.part_1_500.part_441_460;

import com.welph.leecode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。
 * 设计一个算法来序列化和反序列化 二叉搜索树 。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，
 * 并且可以将该字符串反序列化为最初的二叉搜索树。
 * 编码的字符串应尽可能紧凑。
 * <p>
 * 示例 1：
 * 输入：root = [2,1,3]
 * 输出：[2,1,3]
 * <p>
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 * <p>
 * 提示：
 * 树中节点数范围是 [0, 104]
 * 0 <= Node.val <= 104
 * 题目数据 保证 输入的树是一棵二叉搜索树。
 * <p>
 * 注意：不要使用类成员/全局/静态变量来存储状态。 你的序列化和反序列化算法应该是无状态的。
 */
public class Solution449 {

    public static void main(String[] args) {
        // Your Codec object will be instantiated and called as such:
        Codec ser = new Codec();
        Codec deser = new Codec();
        String tree = ser.serialize(TreeNode.createTestData("[1,2]"));
        TreeNode ans = deser.deserialize(tree);
        TreeNode.print(ans);
    }

    public static class Codec {

        //广度优先遍历
        public String serialize(TreeNode root) {
            if (root == null) {
                return null;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            StringBuilder builder = new StringBuilder();
            builder.append(root.val).append(",");
            while (!queue.isEmpty()) {
                int size = queue.size();
                while (size-- > 0) {
                    TreeNode poll = queue.poll();
                    if (poll.left != null) {
                        builder.append(poll.left.val).append(",");
                        queue.add(poll.left);
                    } else {
                        builder.append("null").append(",");
                    }
                    if (poll.right != null) {
                        builder.append(poll.right.val).append(",");
                        queue.add(poll.right);
                    } else {
                        builder.append("null").append(",");
                    }
                }
            }
            return builder.substring(0, builder.length() - 1);
        }

        //反序列化
        public TreeNode deserialize(String data) {
            if (null == data || data.length() == 0) {
                return null;
            }
            String[] arr = data.split(",");
            //反向填充
            Queue<TreeNode> queue = new LinkedList<>();
            TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
            queue.add(root);
            int index = 1;
            String s;
            while (index < arr.length) {
                int size = queue.size();
                while (size-- > 0) {
                    TreeNode poll = queue.poll();
                    if (index < arr.length) {
                        s = arr[index++];
                        if (!"null".equals(s)) {
                            poll.left = new TreeNode(Integer.parseInt(s));
                            queue.add(poll.left);
                        }
                    } else {
                        break;
                    }
                    if (index < arr.length) {
                        s = arr[index++];
                        if (!"null".equals(s)) {
                            poll.right = new TreeNode(Integer.parseInt(s));
                            queue.add(poll.right);
                        }
                    } else {
                        break;
                    }
                }
            }
            return root;
        }
    }
}
