package com.welph.leecode.part_121_140;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 * 要求返回这个链表的深拷贝。
 * 我们用一个由n个节点组成的链表来表示输入/输出中的链表。每个节点用一个[val, random_index]表示：
 * val：一个表示Node.val的整数。
 * random_index：随机指针指向的节点索引（范围从0到n-1）；如果不指向任何节点，则为null。
 * <p>
 * 示例 1：
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * <p>
 * 示例 2：
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 * <p>
 * 示例 3：
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 * <p>
 * 示例 4：
 * 输入：head = []
 * 输出：[]
 * 解释：给定的链表为空（空指针），因此返回 null。
 */
public class Solution138 {

    public static void main(String[] args) {
        //[[3,null],[3,0],[3,null]]
    }

    /**
     * {@link Solution133} 类似深拷贝
     */
    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> crossed = new HashMap<>();
        return copyRandomList(head, crossed);
    }

    private static Node copyRandomList(Node node, Map<Node, Node> crossed) {
        Node root = crossed.get(node);
        if (root != null) {
            return root;
        }
        root = new Node(node.val);
        crossed.put(node, root);
        if (node.next != null) {
            root.next = copyRandomList(node.next, crossed);
        }
        if (node.random != null) {
            root.random = copyRandomList(node.random, crossed);
        }
        return root;
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", next=" + next +
                    ", random=" + random +
                    '}';
        }
    }
}
