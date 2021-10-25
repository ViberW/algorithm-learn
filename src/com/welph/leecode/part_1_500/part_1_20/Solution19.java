package com.welph.leecode.part_1_500.part_1_20;

import com.welph.leecode.common.ListNode;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * <p>
 * 说明：给定的 n 保证是有效的。
 * <p>
 * 进阶：你能尝试使用一趟扫描实现吗？
 *
 * @author: Admin
 * @date: 2019/5/16
 * @Description: {相关描述}
 */
public class Solution19 {

    public static void main(String[] args) {
        ListNode listNode = ListNode.createTestData("[1,2,3,4,5]");
        ListNode.print(removeNthFromEnd(listNode, 2));
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        //创建个父节点
        ListNode parent = new ListNode(0);
        parent.next = head;
        ListNode del = head;
        ListNode delPre = parent;
        int i = 0;
        while (head != null) {
            if (i >= n) {
                delPre = del;
                del = del.next;
            }
            i++;
            head = head.next;
        }
        delPre.next = del.next;
        return parent.next;
    }
}
