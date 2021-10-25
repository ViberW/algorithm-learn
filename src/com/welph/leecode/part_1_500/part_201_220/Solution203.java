package com.welph.leecode.part_1_500.part_201_220;

import com.welph.leecode.common.ListNode;

/**
 * 删除链表中等于给定值 val 的所有节点。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 */
public class Solution203 {

    public static void main(String[] args) {
        ListNode head = ListNode.createTestData("[1,1]");
        ListNode.print(removeElements(head, 1));
    }

    public static ListNode removeElements(ListNode head, int val) {
        ListNode parent = new ListNode(0);
        parent.next = head;
        ListNode p = parent;
        while (p != null && p.next != null) {
            while (p.next != null && p.next.val == val) {
                p.next = p.next.next;
            }
            p = p.next;
        }
        return parent.next;
    }
}
