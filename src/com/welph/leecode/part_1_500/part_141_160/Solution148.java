package com.welph.leecode.part_1_500.part_141_160;

import com.welph.leecode.common.ListNode;

/**
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * <p>
 * 你可以在 O(n log n) 时间复杂度和 [常数级] 空间复杂度下，对链表进行排序吗？
 * <p>
 * 这里使用了快排的思想
 */
public class Solution148 {

    public static void main(String[] args) {
        ListNode node = ListNode.createTestData("[4,2,1,3,7,9,11,0,4,110,2,1,3,7,9,11,0,4,2,1,3,7,9,11,0,4,2,1,3,7,9,11,0,]");
        ListNode node1 = sortList(node);
        ListNode.print(node1);
    }

    //题解思路是归并排序.
    public static ListNode sortList1(ListNode head) {
        //通过fast 和slow 进行选择 node链表的中间节点. good!!!
        return sortList1(head, null);
    }

    static ListNode sortList1(ListNode head, ListNode tail) {
        if (head == null) {
            return null;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode l = sortList1(head, slow);
        ListNode r = sortList1(slow, tail);
        return merge(l, r);
    }

    //合并 相当于是两个有序链表的合并.  参照 {@link Solution21}
    private static ListNode merge(ListNode l, ListNode r) {
        ListNode parent = new ListNode(0);
        ListNode cur = parent;
        while (l != null && r != null) {
            if (l.val < r.val) {
                cur.next = l;
                l = l.next;
            } else {
                cur.next = r;
                r = r.next;
            }
            cur = cur.next;
        }
        if (null != l) {
            cur.next = l;
        }
        if (null != r) {
            cur.next = r;
        }
        return parent.next;
    }

    //利用快排成功 理论上O(nlongn)时间复杂度和常数级空间复杂度
    // 但是超时了...  按理说应该是没什么问题的
    public static ListNode sortList(ListNode head) {
        ListNode parent = new ListNode(0);
        parent.next = head;
        sortList(parent, null);
        return parent.next;
    }

    static void sortList(ListNode head, ListNode tail) {
        ListNode partition = partition(head, tail);
        if (partition != null) {
            sortList(head, partition);
            sortList(partition, tail);
        }
    }

    static ListNode partition(ListNode head, ListNode tail) {
        if (head.next == tail) {
            return null;
        }
        int val = head.next.val;
        ListNode target = head;
        ListNode pre = head.next;
        while (pre.next != tail) {
            if (pre.next.val < val) {
                swap(target, pre);
                target = target.next;
            } else {
                pre = pre.next;
            }
        }
        return target.next;
    }

    private static void swap(ListNode target, ListNode pre) {
        ListNode tmp = pre.next;
        pre.next = tmp.next;
        tmp.next = target.next;
        target.next = tmp;
    }
}
