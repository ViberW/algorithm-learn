package com.welph.leecode.one;

import com.welph.leecode.common.ListNode;

/**
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * @author: Admin
 * @date: 2019/5/16
 * @Description: {相关描述}
 */
public class Solution21 {

    public static void main(String[] args) {
        ListNode listNode1 = ListNode.createTestData("[1,2,4]");
        ListNode listNode2 = ListNode.createTestData("[1,3,4]");
        ListNode.print(mergeTwoLists(listNode1, listNode2));
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode parent = new ListNode(0);
        ListNode cur = parent;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (null != l1) {
            cur.next = l1;
        }
        if (null != l2) {
            cur.next = l2;
        }
        return parent.next;
    }
}
