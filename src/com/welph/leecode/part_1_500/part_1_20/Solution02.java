package com.welph.leecode.part_1_500.part_1_20;

import com.welph.leecode.common.ListNode;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * @author: Admin
 * @date: 2019/5/5
 * @Description: {相关描述}
 */
public class Solution02 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(3);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(2);

        ListNode l2 = new ListNode(4);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(5);

        ListNode listNode = addTwoNumbers(l1, l2);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //思路: 下一级代表当前级, 保存进位 用于之后的处理
        int value;
        int up = 0;
        ListNode listNode = new ListNode(0);
        ListNode child = listNode;
        while (l1 != null || l2 != null) {
            value = up + (null == l1 ? 0 : l1.val) + (null == l2 ? 0 : l2.val);
            up = value / 10;
            child.next = new ListNode(value % 10);
            child = child.next;
            l1 = (null == l1 ? null : l1.next);
            l2 = (null == l2 ? null : l2.next);
        }
        if (up > 0) {
            child.next = new ListNode(up);
        }
        return listNode.next;
    }
}
