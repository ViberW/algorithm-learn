package com.welph.leecode.part_1_500.part_81_100;

import com.welph.leecode.common.ListNode;

/**
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中没有重复出现的数字。
 * <p>
 * 示例1:
 * <p>
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例2:
 * <p>
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 */
public class Solution82 {

    public static void main(String[] args) {
        ListNode listNode = ListNode.createTestData("[1,1,1,2,3]");
        ListNode.print(deleteDuplicates(listNode));
    }

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode special = new ListNode(0);
        ListNode parent = special;
        ListNode pre = new ListNode(null == head ? 0 : (head.val + 1));
        while (head != null) {
            if (head.val != pre.val) {
                special = null == special.next ? special : special.next;
                special.next = head;
            } else {
                special.next = null;
            }
            pre = head;
            head = head.next;
        }
        return parent.next;
    }
}
