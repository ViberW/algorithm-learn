package com.welph.leecode.part_201_220;

import com.welph.leecode.common.ListNode;
import com.welph.leecode.part_81_100.Solution92;

/**
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题
 */
public class Solution206 {

    public static void main(String[] args) {
        ListNode.print(reverseList(ListNode.createTestData("[1,2,3,4,5]")));
    }

    /**
     * {@link Solution92} 相似题目
     */
    public static ListNode reverseList(ListNode head) {
        if (null == head) {
            return head;
        }
        ListNode parent = new ListNode(0);
        parent.next = head;
        ListNode p;
        while (head.next != null) {
            p = parent.next;
            parent.next = head.next;
            head.next = head.next.next;
            parent.next.next = p;
        }
        return parent.next;
    }
}
