package com.welph.leecode.part_1_500.part_281_300;

import com.welph.leecode.common.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
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
            for (int i = 1; i < split.length;) {
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

    /* 官方题解 */
    public class Codec2 {
        public String serialize(TreeNode root) {
            return rserialize(root, "");
        }

        public TreeNode deserialize(String data) {
            String[] dataArray = data.split(",");
            List<String> dataList = new LinkedList<String>(Arrays.asList(dataArray));
            return rdeserialize(dataList);
        }

        public String rserialize(TreeNode root, String str) {
            if (root == null) {
                str += "None,";
            } else {
                str += String.valueOf(root.val) + ",";
                str = rserialize(root.left, str);
                str = rserialize(root.right, str);
            }
            return str;
        }

        public TreeNode rdeserialize(List<String> dataList) {
            if (dataList.get(0).equals("None")) {
                dataList.remove(0);
                return null;
            }

            TreeNode root = new TreeNode(Integer.valueOf(dataList.get(0)));
            dataList.remove(0);
            root.left = rdeserialize(dataList);
            root.right = rdeserialize(dataList);

            return root;
        }
    }

    // 括号表示编码 + 递归下降解码
    public class Codec3 {
        public String serialize(TreeNode root) {
            if (root == null) {
                return "X";
            }
            String left = "(" + serialize(root.left) + ")";
            String right = "(" + serialize(root.right) + ")";
            return left + root.val + right;
        }

        public TreeNode deserialize(String data) {
            int[] ptr = { 0 };
            return parse(data, ptr);
        }

        public TreeNode parse(String data, int[] ptr) {
            if (data.charAt(ptr[0]) == 'X') {
                ++ptr[0];
                return null;
            }
            TreeNode cur = new TreeNode(0);
            cur.left = parseSubtree(data, ptr);
            cur.val = parseInt(data, ptr);
            cur.right = parseSubtree(data, ptr);
            return cur;
        }

        public TreeNode parseSubtree(String data, int[] ptr) {
            ++ptr[0]; // 跳过左括号
            TreeNode subtree = parse(data, ptr);
            ++ptr[0]; // 跳过右括号
            return subtree;
        }

        public int parseInt(String data, int[] ptr) {
            int x = 0, sgn = 1;
            if (!Character.isDigit(data.charAt(ptr[0]))) {
                sgn = -1;
                ++ptr[0];
            }
            while (Character.isDigit(data.charAt(ptr[0]))) {
                x = x * 10 + data.charAt(ptr[0]++) - '0';
            }
            return x * sgn;
        }
    }

}
