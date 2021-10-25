package com.welph.leecode.part_1_500.part_81_100;

import com.welph.leecode.common.ListNode;

/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 * <p>
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 */
public class Solution83 {

    public static void main(String[] args) {
        ListNode listNode = ListNode.createTestData("[1,1,2,3,3]");
        ListNode.print(deleteDuplicates(listNode));
    }

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode parent = new ListNode(0);
        ListNode current = parent;
        int preVal = null == head ? 0 : (head.val + 1);
        while (head != null) {
            if (preVal != head.val) {
                current.next = head;
                current = current.next;
            }
            preVal = head.val;
            head = head.next;
        }
        current.next = null;
        return parent.next;
    }
}
