package com.welph.leecode.part_1_500.part_141_160;

import com.welph.leecode.common.ListNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个单链表L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 示例1:
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * <p>
 * 示例 2:
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 */
public class Solution143 {

    public static void main(String[] args) {
        ListNode node = ListNode.createTestData("[1,2,3,4,5]");
        new Solution143().reorderList(node);
        ListNode.print(node);
    }

    public void reorderList(ListNode head) {
        int len = 0;
        ListNode p = head;
        while (p != null) {
            p = p.next;
            len++;
        }
        int size = len / 2;
        List<ListNode> pres = new ArrayList<>(size);
        int dx = 2 * size - (len % 2 == 1 ? 0 : 1);
        p = head;
        int x = 0;
        while (x < len) {
            System.out.println(p.val);
            if (x < size) {
                pres.add(p);
                p = p.next;
            } else if (x > size) {
                ListNode tmp = p.next;

                ListNode node = pres.get(dx - x);
                p.next = node.next;
                node.next = p;

                p = tmp;
            } else {
                // 说明不要有next
                ListNode tmp = p.next;
                p.next = null;
                p = tmp;
            }
            x++;
        }
    }

    /* 官方题解 */
    public void reorderList2(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> list = new ArrayList<ListNode>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }

}
