package com.welph.leecode.part_1_500.part_21_40;

import com.welph.leecode.common.ListNode;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 示例 :
 * 给定这个链表：1->2->3->4->5
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * <p>
 * 说明 :
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */
public class Solution25 {

    public static void main(String[] args) {
        ListNode listNode = ListNode.createTestData("[1,2,4,5,7,8]");
        ListNode.print(reverseKGroup(listNode, 4));
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        //反转k个节点，并设置pre
        ListNode parent = new ListNode(0);
        int i;
        parent.next = head;
        ListNode pre = parent;
        ListNode next = head;
        ListNode first = head;
        lable:
        while (k > 1) {
            //此处应该有个先行判断是否足够长度
            for (i = 0; i < k; i++) {
                if (next == null) {
                    break lable;
                }
                next = next.next;
            }
            //反转k个节点,每次交换两个，之后当作一个，需要保存当前的第一个node 和最后一个node
            for (i = 2; i <= k; i++) {
                pre.next = head.next;
                head.next = pre.next.next;
                pre.next.next = first;
                first = pre.next;
            }
            pre = head;
            first = head = head.next;

        }
        return parent.next;
    }

}
