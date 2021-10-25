package com.welph.leecode.part_1_500.part_21_40;

import com.welph.leecode.common.ListNode;
import com.welph.leecode.common.Time;

import java.util.Arrays;

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
        Time.time(() -> {
            ListNode.print(mergeKLists(new ListNode[]{node1, node2, node3}));
        });
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        //两两合并
        if (lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        if (lists.length > 2) {
            lists = new ListNode[]{
                    mergeKLists(Arrays.copyOfRange(lists, 0, lists.length / 2)),
                    mergeKLists(Arrays.copyOfRange(lists, lists.length / 2, lists.length))
            };
        }
        return merge(lists[0], lists[1]);
    }

    private static ListNode merge(ListNode first, ListNode second) {
        ListNode current = new ListNode(0);
        ListNode cur = current;
        while (null != first && null != second) {
            if (first.val < second.val) {
                cur.next = first;
                first = first.next;
            } else {
                cur.next = second;
                second = second.next;
            }
            cur = cur.next;
        }
        if (null == first) {
            cur.next = second;
        } else {
            cur.next = first;
        }
        return current.next;
    }

}
