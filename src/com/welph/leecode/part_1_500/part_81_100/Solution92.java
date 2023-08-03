package com.welph.leecode.part_1_500.part_81_100;

import com.welph.leecode.common.ListNode;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * <p>
 * 说明:
 * 1 ≤m≤n≤ 链表长度。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 */
public class Solution92 {

    public static void main(String[] args) {
        ListNode listNode1 = ListNode.createTestData("[1]");
        ListNode.print(reverseBetween(listNode1, 1,1));
    }

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode parent = new ListNode(0);
        parent.next = head;
        ListNode begin = parent;
        ListNode near = head;
        while (n > 0) {
            if (m > 0) {
                if (m != 1) {
                    begin = begin.next;
                    near = near.next;
                }
                m--;
            } else {
                near.next = head.next;
                head.next = begin.next;
                begin.next = head;
            }
            head = near.next;
            n--;
        }
        return parent.next;
    }
}
