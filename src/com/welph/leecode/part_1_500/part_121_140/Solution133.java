package com.welph.leecode.part_1_500.part_121_140;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@linkplain <a>https://leetcode-cn.com/problems/clone-graph/</a>}
 * <p>
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 */
public class Solution133 {

    public static void main(String[] args) {
        Node node = new Node(1);
        Node newNode = cloneGraph(node);
        System.out.println(newNode);
    }

    //深度优先搜索
    public static Node cloneGraph(Node node) {
        //考虑是否有处理过, 处理过就直接赋予指针
        if (node == null) {
            return null;
        }
        Map<Integer, Node> crossed = new HashMap<>();
        return cloneGraph(node, crossed);
    }

    static Node cloneGraph(Node node, Map<Integer, Node> crossed) {
        Node root = crossed.get(node.val);
        if (root != null) {
            return root;
        }
        root = new Node(node.val);
        crossed.put(root.val, root);
        List<Node> neighbors = new ArrayList<>();
        root.neighbors = neighbors;
        if (null != node.neighbors && !node.neighbors.isEmpty()) {
            for (Node neighbor : node.neighbors) {
                neighbors.add(cloneGraph(neighbor, crossed));
            }
        }
        return root;
    }


    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", neighbors=" + neighbors +
                    '}';
        }
    }
}
