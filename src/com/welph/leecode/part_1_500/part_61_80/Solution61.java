package com.welph.leecode.part_1_500.part_61_80;

import com.welph.leecode.common.ListNode;

/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例 2:
 * <p>
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 * <p>
 */
public class Solution61 {

    public static void main(String[] args) {
        ListNode listNode = ListNode.createTestData("[]");
        ListNode.print(rotateRight(listNode, 9));
    }

    public static ListNode rotateRight(ListNode head, int k) {
        //取模，k
        //将倒数第k个位置节点的node ,parentNode.next = null.  endNode.next = last.next
        //有个问题是这个节点的长度，保留第k个节点的数据
        if (null == head || k == 0) {
            return head;
        }
        ListNode virtual = new ListNode(0);
        virtual.next = head;
        ListNode preNode = virtual;
        ListNode exec = head;
        ListNode end = null;
        int len = 0;
        while (exec != null) {
            len++;
            if (len > k) {
                preNode = preNode.next;
            }
            end = exec;
            exec = exec.next;
        }
        if (preNode == virtual) {
            k = len == 0 ? 0 : k % len;
            if (k == 0) {
                return head;
            }
            len = 0;
            exec = head;
            while (exec != null) {
                len++;
                if (len > k) {
                    preNode = preNode.next;
                }
                exec = exec.next;
            }
        }
        virtual.next = preNode.next;
        preNode.next = null;
        end.next = head;
        return virtual.next;
    }

}
