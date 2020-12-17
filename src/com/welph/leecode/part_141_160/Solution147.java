package com.welph.leecode.part_141_160;

import com.welph.leecode.common.ListNode;

/**
 * 对链表进行插入排序。
 * >  插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * >  每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * >  重复直到所有输入数据插入完为止。
 */
public class Solution147 {
    public static void main(String[] args) {
        ListNode node = ListNode.createTestData("[4,2,3,1]");
        ListNode node1 = insertionSortList(node);
        ListNode.print(node1);
    }

    /**
     * 插入排序, 以某个作为基准点, 不断比较并移动
     * <p> 时间慢 19% 空间64%
     * 不像数组的排序 能够倒叙, 这里好像只能顺序,要么借助额外空间去保存...
     */
    public static ListNode insertionSortList(ListNode head) {
        ListNode parent = new ListNode(0);
        parent.next = head;
        ListNode p = parent;
        ListNode cyc;
        Label:
        while (p.next != null) {
            cyc = parent;
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
}
