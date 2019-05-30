package com.welph.leecode.part_21_40;

import com.welph.leecode.common.ListNode;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * 示例:
 * 输入:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 * @author: Admin
 * @date: 2019/5/17
 * @Description: {相关描述}
 */
public class Solution23 {

    public static void main(String[] args) {
        ListNode node1 = ListNode.createTestData("[1,4,5]");
        ListNode node2 = ListNode.createTestData("[1,3,4]");
        ListNode node3 = ListNode.createTestData("[2,6]");
        ListNode.print(mergeKLists(new ListNode[]{node1, node2, node3}));
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        //两两合并
        
        return null;
    }
}
