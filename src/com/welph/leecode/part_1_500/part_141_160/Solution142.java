package com.welph.leecode.part_1_500.part_141_160;

import com.welph.leecode.common.ListNode;

import java.util.Arrays;
import java.util.List;

/**
 * 给定一个链表，返回链表开始入环的第一个节点。如果链表无环，则返回null。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 * 说明：不允许修改给定的链表。
 * 进阶：
 * 你是否可以使用 O(1) 空间解决此题？
 * <p>
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * <p>
 * 示例2：
 * 输入：head = [1,2], pos = 0
 * 输出：返回索引为 0 的链表节点
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * <p>
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：返回 null
 * 解释：链表中没有环。
 * 提示：
 * 链表中节点的数目范围在范围 [0, 104] 内
 * -105 <= Node.val <= 105
 * pos 的值为 -1 或者链表中的一个有效索引
 */
public class Solution142 {

    public static void main(String[] args) {
        //ListNode node1 = ListNode.createTestData("[3,2,0,-4]");
        List<Integer> integers = Arrays.asList(-21, 10, 17, 8, 4, 26, 5, 35, 33, -7, -16, 27, -12, 6, 29, -12, 5, 9, 20, 14, 14, 2, 13, -24, 21, 23, -21, 5);
        ListNode target = null;
        ListNode parent = new ListNode(0);
        ListNode pre = parent;
        for (int i = 0; i < integers.size(); i++) {
            pre.next = new ListNode(integers.get(i));
            if (i == 24) {
                target = pre.next;
            }
            pre = pre.next;
        }
        pre.next = target;
        System.out.println(new Solution142().detectCycle(parent.next).val);
    }

    public ListNode detectCycle(ListNode head) {
        if (null == head) {
            return null;
        }
        ListNode p = head;
        ListNode p1 = head;

        boolean cycle = false;
        while (p1.next != null && p1.next.next != null) {
            p = p.next;
            p1 = p1.next.next;
            if (p == p1) {
                cycle = true;
                break;
            }
        }
        /**
         * todo 有点思考了
         * 假定目标节点为A 距离head经历节点个数为a
         * 假定相遇目标节点为B 假设B距离a为b个节点, 那么慢路程到达B为 a+b
         * 但是快路程到达B经历 2(a+b) 个节点
         * 所以 慢路程 还需 经历 a+b 个节点又可以回到B,  所以经历 a 个节点又能回到 A
         *
         * 所以从head再出发 与从 B 出发,一定能够相遇且为目标A 节点
         */
        if (cycle) {
            p1 = head;
            while (p1 != p) {
                p = p.next;
                p1 = p1.next;
            }
            return p;
        } else {
            return null;
        }
    }
}
