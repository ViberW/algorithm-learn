package com.welph.leecode.part_1_500.part_141_160;

import com.welph.leecode.common.ListNode;

/**
 * 对链表进行插入排序。
 * > 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * > 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * > 重复直到所有输入数据插入完为止。
 */
public class Solution147 {
    public static void main(String[] args) {
        ListNode node = ListNode.createTestData("[4,2,3,1]");
        ListNode node1 = insertionSortList(node);
        ListNode.print(node1);
    }

    /**
     * 插入排序, 以某个作为基准点, 不断比较并移动
     * <p>
     * 时间慢 19% 空间64%
     * 不像数组的排序 能够倒叙, 这里好像只能顺序,要么借助额外空间去保存...
     */
    public static ListNode insertionSortList(ListNode head) {
        ListNode parent = new ListNode(0);
        parent.next = head;
        ListNode p = parent;
        ListNode cyc;
        Label: while (p.next != null) {
            cyc = parent;// 每次重新进行插入判断
            while (cyc.next != p.next) {
                if (cyc.next.val > p.next.val) {
                    ListNode tmp = p.next;
                    p.next = tmp.next;
                    tmp.next = cyc.next;
                    cyc.next = tmp;
                    continue Label;
                }
                cyc = cyc.next;
            }
            p = p.next;
        }
        return parent.next;
    }

    /* 官方题解 */
    // 官方多了lastSorted 尽量去减少可能不必要的遍历插入
    public ListNode insertionSortList1(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode lastSorted = head, curr = head.next;
        while (curr != null) {
            if (lastSorted.val <= curr.val) {
                lastSorted = lastSorted.next;
            } else {
                ListNode prev = dummyHead;
                while (prev.next.val <= curr.val) {
                    prev = prev.next;
                }
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            curr = lastSorted.next;
        }
        return dummyHead.next;
    }

}
