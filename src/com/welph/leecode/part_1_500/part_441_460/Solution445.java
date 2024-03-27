package com.welph.leecode.part_1_500.part_441_460;

import com.welph.leecode.common.ListNode;

import java.util.Stack;

/**
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 * 示例1：
 * 输入：l1 = [7,2,4,3], l2 = [5,6,4]
 * 输出：[7,8,0,7]
 * <p>
 * 示例2：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[8,0,7]
 * <p>
 * 示例3：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * <p>
 * 提示：
 * 链表的长度范围为 [1, 100]
 * 0 <= node.val <= 9
 * 输入数据保证链表代表的数字无前导 0
 * 进阶：如果输入链表不能修改该如何处理？换句话说，不能对列表中的节点进行翻转。
 */
public class Solution445 {

    public static void main(String[] args) {
        ListNode l1 = ListNode.createTestData("[7,2,4,3]");
        ListNode l2 = ListNode.createTestData("[5,6,4]");
        ListNode.print(addTwoNumbers(l1, l2));
    }

    /**
     * {@link com.welph.leecode.part_1_500.part_1_20.Solution02}
     * 这里是: 高位存储
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 使用栈保存上一个节点
        Stack<Integer> s1 = new Stack<>();
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        Stack<Integer> s2 = new Stack<>();
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        int reduce = 0;
        ListNode node = null;
        ListNode cur;
        while (!s1.isEmpty() || !s2.isEmpty() || reduce > 0) {
            int v = (s2.isEmpty() ? 0 : s2.pop()) + (s1.isEmpty() ? 0 : s1.pop()) + reduce;
            cur = new ListNode(v % 10);
            cur.next = node;
            node = cur;
            reduce = v / 10;
        }
        return node;
    }

    // 排名前面的做法, 反转+{@link Solution02} 就能快速解决了
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode new1 = reverse(l1);
        ListNode new2 = reverse(l2);

        ListNode head = null;

        int temp = 0;
        while (new1 != null || new2 != null || temp != 0) {

            int a = new1 == null ? 0 : new1.val;
            int b = new2 == null ? 0 : new2.val;

            int sum = a + b + temp;
            temp = sum / 10;

            ListNode node = new ListNode(sum % 10);
            node.next = head;
            head = node;
            new1 = new1 == null ? null : new1.next;
            new2 = new2 == null ? null : new2.next;

        }
        return head;

    }

    ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }

        return pre;
    }
}
