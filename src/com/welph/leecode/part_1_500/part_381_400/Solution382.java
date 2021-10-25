package com.welph.leecode.part_1_500.part_381_400;

import com.welph.leecode.common.ListNode;

import java.util.Random;

/**
 * 给定一个单链表，随机选择链表的一个节点，并返回相应的节点值。保证每个节点被选的概率一样。
 * 进阶:
 * 如果链表十分大且长度未知，如何解决这个问题？你能否使用常数级空间复杂度实现？
 * <p>
 * 示例:
 * // 初始化一个单链表 [1,2,3].
 * ListNode head = new ListNode(1);
 * head.next = new ListNode(2);
 * head.next.next = new ListNode(3);
 * Solution solution = new Solution(head);
 * <p>
 * // getRandom()方法应随机返回1,2,3中的一个，保证每个元素被返回的概率相等。solution.getRandom();
 */
public class Solution382 {

    public static void main(String[] args) {
        Solution obj = new Solution(ListNode.createTestData("[1,2,3]"));
        int param_1 = obj.getRandom();
    }

    /**
     * 链表可能很大, 要求常数级空间
     * ------------
     * [水塘抽样] 了解了算法的原理  -- 本质上成为答案的可能都是一样的 666!
     */
    static class Solution {
        static Random random = new Random();

        ListNode head;

        public Solution(ListNode head) {
            this.head = head;
        }

        public int getRandom() {
            ListNode node = head;
            int res = node.val;
            int i;
            int len = 1;
            while (node != null) {
                i = random.nextInt(len);
                if (i == 0) {
                    res = node.val;
                }
                len++;
                node = node.next;
            }
            return res;
        }
    }
}
