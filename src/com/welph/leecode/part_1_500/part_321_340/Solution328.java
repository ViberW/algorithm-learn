package com.welph.leecode.part_1_500.part_321_340;

import com.welph.leecode.common.ListNode;

/**
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。
 * 请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * <p>
 * 请尝试使用[原地算法]完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 * <p>
 * 示例 1:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * <p>
 * 示例 2:
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 * <p>
 * 说明:
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 */
public class Solution328 {

    public static void main(String[] args) {
        ListNode node = ListNode.createTestData("[2,1,3,5,6,4,7]");
        ListNode.print(oddEvenList(node));
    }

    // 节点位置的奇偶性
    public static ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return head;
        }
        int i = 0;
        ListNode parent = new ListNode(0);
        parent.next = head;
        ListNode odd = head;
        while (head.next != null) {
            if (i % 2 == 1) {
                ListNode tmp = odd.next;
                odd.next = head.next;
                head.next = head.next.next;
                odd.next.next = tmp;
                odd = odd.next;
            } else {
                head = head.next;
            }
            i++;
        }
        return parent.next;
    }

    /* 官方题解 */

    //更加简洁
    public ListNode oddEvenList2(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode evenHead = head.next;
        ListNode odd = head, even = evenHead;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

}
