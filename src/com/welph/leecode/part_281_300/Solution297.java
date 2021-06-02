package com.welph.leecode.part_281_300;

import com.welph.leecode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
 * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。
 * 你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 * <p>
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：root = [1]
 * 输出：[1]
 * <p>
 * 示例 4：
 * 输入：root = [1,2]
 * 输出：[1,2]
 * <p>
 * 提示：
 * 树中结点数在范围 [0, 104] 内
 * -1000 <= Node.val <= 1000
 */
public class Solution297 {

    public static void main(String[] args) {
        TreeNode root = TreeNode.createTestData("[1,2]");
        Codec codec = new Codec();
        String serialize = codec.serialize(root);
        System.out.println(serialize);
        TreeNode deserialize = codec.deserialize(serialize);
        TreeNode.print(deserialize);
    }

    static class Codec {

        public String serialize(TreeNode root) {
            if (null == root) {
                return "[]";
            }
            StringBuilder sb = new StringBuilder("[");
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            TreeNode pop;
            int endIndex = 0;
            do {
                pop = queue.pop();
                if (pop == null) {
                    sb.append("null").append(",");
                } else {
                    sb.append(pop.val).append(",");
                    queue.add(pop.left);
                    queue.add(pop.right);
                    endIndex = sb.length() - 1;
                }
            } while (!queue.isEmpty());
            return sb.substring(0, endIndex) + "]";
        }

        public TreeNode deserialize(String data) {
            if (null == data || data.length() <= 2) {
                return null;
            }
            String substring = data.substring(1, data.length() - 1);
            String[] split = substring.split(",");
            Queue<TreeNode> queue = new LinkedList<>();
            TreeNode root = new TreeNode(Integer.valueOf(split[0]));
            queue.add(root);
            String s;
            for (int i = 1; i < split.length; ) {
                TreeNode n = queue.poll();
                s = split[i++];
                if (!"null".equals(s)) {
                    n.left = new TreeNode(Integer.valueOf(s));
                    queue.add(n.left);
                }
                if (i < split.length) {
                    s = split[i++];
                    if (!"null".equals(s)) {
                        n.right = new TreeNode(Integer.valueOf(s));
                        queue.add(n.right);
                    }
                }
            }
            return root;
        }
    }
}
