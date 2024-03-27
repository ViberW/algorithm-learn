package com.welph.leecode.part_1_500.part_321_340;

import java.util.Stack;

/**
 * 序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。
 * 如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
 * <p>
 * * _9_
 * * / \
 * * 3 2
 * * / \ / \
 * * 4 1 # 6
 * * / \ / \ / \
 * * # # # # # #
 * 例如，上面的二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。
 * <p>
 * 给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
 * 每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
 * 你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如 "1,,3" 。
 * <p>
 * 示例 1:
 * 输入: "9,3,4,#,#,1,#,#,2,#,6,#,#"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: "1,#"
 * 输出: false
 * <p>
 * 示例 3:
 * 输入: "9,#,#,1"
 * 输出: false
 */
public class Solution331 {

    public static void main(String[] args) {
        System.out.println(isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
        System.out.println(isValidSerialization("1,#"));
        System.out.println(isValidSerialization("9,#,#,1"));
        System.out.println(isValidSerialization("#,7,6,9,#,#,#"));
    }

    // 最左节点, 一定是个# ,若是左右匹配#,# 则将父节点设置为#;
    public static boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");
        if (nodes.length % 2 != 1) {
            return false;
        }
        Stack<String> stack = new Stack<>();
        for (String node : nodes) {
            if (node.equals("#")) {
                while (!stack.isEmpty() && node.equals(stack.peek())) {
                    // 将父节点设置为#
                    stack.pop();
                    if (stack.isEmpty()) {
                        return false;
                    }
                    stack.pop();
                }
                stack.push(node);
            } else {
                stack.push(node);
            }
        }
        return stack.size() == 1 && stack.peek().equals("#");
    }

    /* 官方题解 */

    // 计数法 (当然官方也有用栈的方法)
    public boolean isValidSerialization2(String preorder) {
        int n = preorder.length();
        int i = 0;
        int slots = 1;
        while (i < n) {
            if (slots == 0) {
                return false;
            }
            if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#') {
                slots--;
                i++;
            } else {
                // 读一个数字
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                slots++; // slots = slots - 1 + 2
            }
        }
        return slots == 0;
    }

}
