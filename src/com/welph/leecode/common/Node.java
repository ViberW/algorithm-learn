package com.welph.leecode.common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    private static final String space = "      ";

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }

    public static Node createTestData(String data) {
        if (data.equals("[]")) return null;
        data = data.substring(1, data.length() - 1);
        String[] split = data.split(",");
        int len = split.length;
        Node[] Nodes = new Node[len];
        for (int i = 0; i < len; i++) {
            if (!split[i].equals("null")) {
                Nodes[i] = new Node(Integer.valueOf(split[i]));
            }
        }
        for (int i = 0; i < len; i++) {
            if (Nodes[i] != null) {
                int leftIndex = i * 2 + 1;
                if (leftIndex < len) {
                    Nodes[i].left = Nodes[leftIndex];
                }
                int rightIndex = leftIndex + 1;
                if (rightIndex < len) {
                    Nodes[i].right = Nodes[rightIndex];
                }
            }
        }
        return Nodes[0];
    }

    /**
     * 竖向打印二叉树
     *
     * @param root 二叉树根节点
     */
    public static void print(Node root) {
        print(root, 0);
    }

    private static void print(Node node, int deep) {
        if (node == null) {
            printSpace(deep);
            System.out.println("#");
            return;
        }
        print(node.right, deep + 1);
        printSpace(deep);
        printNode(node.val);
        print(node.left, deep + 1);
    }

    private static void printSpace(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(space);
        }
    }

    private static void printNode(int val) {
        StringBuilder res = new StringBuilder(val + "<");
        int spaceNum = space.length() - res.length();
        for (int i = 0; i < spaceNum; i++) {
            res.append(" ");
        }
        System.out.println(res);
    }

    public static void printFront(Node node) {
        List<Integer> results = new ArrayList<>();
        LinkedList<Node> queue = new LinkedList<>();
        results.add(node.val);
        queue.add(node);
        Node pop;
        while (queue.peek() != null) {
            pop = queue.pop();
            if (null != pop.left) {
                results.add(pop.left.val);
                queue.add(pop.left);
            } else {
                results.add(null);
            }
            if (null != pop.right) {
                results.add(pop.right.val);
                queue.add(pop.right);
            } else {
                results.add(null);
            }
        }
        System.out.println(results);
    }
}
