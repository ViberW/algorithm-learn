package com.welph.leecode.part_21_40;

import com.welph.leecode.common.ListNode;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例:
 * <p>
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 */
public class Solution24 {

    public static void main(String[] args) {
        ListNode listNode = ListNode.createTestData("[1,2,4,5,7,8]");
        ListNode.print(swapPairs(listNode));
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode parent = new ListNode(0);
        parent.next = head;
        ListNode pre = parent;
        while (null != head && null != head.next) {
            pre.next = head.next;
            head.next = pre.next.next;
            pre.next.next = head;
            pre = head;
            head = head.next;
        }
        return parent.next;
    }
}
