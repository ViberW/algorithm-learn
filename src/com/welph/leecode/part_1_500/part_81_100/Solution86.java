package com.welph.leecode.part_1_500.part_81_100;

import com.welph.leecode.common.ListNode;

/**
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 * <p>
 * 你应当保留两个分区中每个节点的初始相对位置。
 * <p>
 * 示例:
 * <p>
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 * <p>
 */
public class Solution86 {

    public static void main(String[] args) {
        ListNode listNode = ListNode.createTestData("[1,4,3,2,5,2]");
        ListNode.print(partition(listNode, 3));
    }

    public static ListNode partition(ListNode head, int x) {
        ListNode parent = new ListNode(0);
        ListNode current = null;
        ListNode min = parent;
        ListNode temp;
        while (head != null) {
            if (head.val >= x) {
                if (current == null) {
                    current = min;
                }
                current.next = head;
                current = current.next;
                head = head.next;
            } else {
                temp = min.next;
                min.next = head;
                head = head.next;
                min = min.next;
                min.next = temp;
            }
        }
        if (current != null) {
            current.next = null;
        }
        return parent.next;
    }
}
