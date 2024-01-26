package com.welph.leecode.part_1_500.part_221_240;

import com.welph.leecode.common.ListNode;

/**
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 * <p>
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class Solution234 {

    public static void main(String[] args) {
        ListNode node = ListNode.createTestData("[1,2,2,1]");
        System.out.println(isPalindrome(node));

        ListNode node1 = ListNode.createTestData("[1,2,3,4,2,1]");
        System.out.println(isPalindrome(node1));

        ListNode node3 = ListNode.createTestData("[1,2]");
        System.out.println(isPalindrome(node3));

        ListNode node2 = ListNode.createTestData(
                "[8,0,7,1,7,7,9,7,5,2,9,1,7,3,7,0,6,5,1,7,7,9,3,8,1,5,7,7,8,4,0,9,3,7,3,4,5,7,4,8,8,5,8,9,8,5,8,8,4,7,5,4,3,7,3,9,0,4,8,7,7,5,1,8,3,9,7,7,1,5,6,0,7,3,7,1,9,2,5,7,9,7,7,1,7,0,8]");
        System.out.println(isPalindrome(node2));
    }

    /**
     * {@link com.welph.leecode.part_1_500.part_1_20.Solution09}
     * {@link com.welph.leecode.part_1_500.part_121_140.Solution125}
     * {@link com.welph.leecode.part_1_500.part_201_220.Solution206}
     */
    public static boolean isPalindrome(ListNode head) {
        // 找到对应的长度
        int size = 0;
        ListNode p = head;
        while (p != null) {
            size++;
            p = p.next;
        }
        if (size < 2) {
            return true;
        }
        // 不断反转, 知道目标长度 反转链表 到中位数
        int reverse = size / 2;
        ListNode parent = new ListNode(0);
        parent.next = head;
        for (int i = reverse; i > 1; i--) {
            // 反转
            p = parent.next;
            parent.next = head.next;
            head.next = head.next.next;
            parent.next.next = p;
        }
        // 再最次比较节点是否相等
        ListNode q = head.next;
        if (size % 2 == 1) {
            q = q.next;
        }
        // 此时因为到了中间, 所以需要变化
        p = parent.next;
        while (q != null) {
            if (p.val != q.val) {
                return false;
            }
            p = p.next;
            q = q.next;
        }
        return true;
    }

    /* 官方题解 */
    private ListNode frontPointer;

    private boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            // 通过递归保存对比节点, 当然使用栈也行(一个是自定义空间, 一个是栈空间)
            if (!recursivelyCheck(currentNode.next)) {
                return false;
            }
            if (currentNode.val != frontPointer.val) {
                return false;
            }
            frontPointer = frontPointer.next;
        }
        return true;
    }

    // 递归
    public boolean isPalindrome2(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }

    // 快慢指针, 相较于我的第一个方法, 少了长度计算等操作
    public boolean isPalindrome3(ListNode head) {
        if (head == null) {
            return true;
        }

        // 找到前半部分链表的尾节点并反转后半部分链表
        ListNode firstHalfEnd = endOfFirstHalf(head);
        //翻转后半段
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 判断是否回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

}
