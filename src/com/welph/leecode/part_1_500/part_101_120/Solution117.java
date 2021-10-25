package com.welph.leecode.part_1_500.part_101_120;

import com.welph.leecode.common.Node;

/**
 * .给定一个二叉树
 * .
 * .struct Node {
 * .  int val;
 * .  Node *left;
 * .  Node *right;
 * .  Node *next;
 * .}
 * .填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * .
 * .初始状态下，所有 next 指针都被设置为 NULL。
 * .
 * .进阶：
 * .
 * .你只能使用常量级额外空间。
 * .使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 */
public class Solution117 {

    public static void main(String[] args) {
        Node root = Node.createTestData("[1,2,3,4,5,null,7]");
        Node newNode = connect(root);
        System.out.println(1);
    }

    /**
     * 这里不是{@link Solution116} 仅仅是个普通的二叉树
     *
     * @param root
     * @return
     */
    public static Node connect(Node root) {
        Node cur = root;
        Node pre;
        Node first;
        while (cur != null) {
            Node head = cur;
            pre = null;
            first = null;
            while (head != null) {
                if (head.left != null) {
                    if (pre == null) {
                        first = head.left;
                    } else {
                        pre.next = head.left;
                    }
                    pre = head.left;
                }
                if (head.right != null) {
                    if (pre == null) {
                        first = head.right;
                    } else {
                        pre.next = head.right;
                    }
                    pre = head.right;
                }
                head = head.next;
            }
            cur = first;
        }
        return root;
    }
}
