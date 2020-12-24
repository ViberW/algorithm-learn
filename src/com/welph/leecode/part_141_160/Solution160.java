package com.welph.leecode.part_141_160;

import com.welph.leecode.common.ListNode;

/**
 * 编写一个程序，找到两个单链表相交的起始节点。
 * <p>
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存
 */
public class Solution160 {

    //注意, 这里的题目是Node的内存指针地址相同, 并非值相同就可以了.
    public static void main(String[] args) {
        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        ListNode headB = new ListNode(5);
        headB.next = new ListNode(0);
        headB.next.next = new ListNode(1);

        //这样才是真正的结果, 比较地址
        ListNode common = new ListNode(8);
        common.next = new ListNode(4);
        common.next.next = new ListNode(5);

        headA.next.next = common;
        headB.next.next.next = common;

        ListNode.print(getIntersectionNode(headA, headB));
    }

    //---todo 着重思考相同步数到相交点
    //两次遍历 第一次消除两个链表的长度不一
    //第二次, 起始点会从相交点的前X位置开始比较;
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode p = headA;
        ListNode q = headB;
        while (p != q) {
            p = p == null ? headB : p.next;
            q = q == null ? headA : q.next;
        }
        return p;
    }
}
