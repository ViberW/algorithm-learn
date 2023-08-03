package com.welph.leecode.part_1_500.part_101_120;

import com.welph.leecode.common.ListNode;
import com.welph.leecode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * . 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * .
 * . 本题中，一个高度平衡二叉树是指一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1。
 * .
 * . 示例:
 * .
 * . 给定的有序链表： [-10, -3, 0, 5, 9],
 * .
 * . 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 * .
 * .       0
 * .      / \
 * .    -3   9
 * .    /   /
 * .  -10  5
 */
public class Solution109 {


    public static void main(String[] args) {
        ListNode listNode = ListNode.createTestData("[-93,-89,-85,-76,-56,-53,-20,-10,20,28,41,50,66,70,87,88,91,94]");
        TreeNode node = new Solution109().sortedListToBST1(listNode);
        TreeNode.printFront(node);
    }

    private ListNode head;

    /**
     * 有点类似于我那个错误的思路, 但由于它找到了整体的长度,所以一开始就能明确树的结构分布
     */
    public TreeNode sortedListToBST(ListNode head) {
        int size = this.findSize(head);
        this.head = head;
        return convertListToBST(0, size - 1);
    }

    private TreeNode convertListToBST(int l, int r) {
        if (l > r) {
            return null;
        }

        int mid = (l + r) / 2;
        TreeNode left = this.convertListToBST(l, mid - 1);
        TreeNode node = new TreeNode(this.head.val);
        node.left = left;
        this.head = this.head.next;
        node.right = this.convertListToBST(mid + 1, r);
        return node;
    }

    private int findSize(ListNode head) {
        ListNode ptr = head;
        int c = 0;
        while (ptr != null) {
            ptr = ptr.next;
            c += 1;
        }
        return c;
    }

    /**
     * 思路1: 最能理解的就是转成数组 根据{@link Solution108}计算
     * 看空间换时间
     */
    public static TreeNode sortedListToBST1(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return sortedArrayToBST(list, 0, list.size());
    }

    public static TreeNode sortedArrayToBST(List<Integer> list, int l, int r) {
        if (l >= r) {
            return null;
        }
        int middle = (l + r) / 2;
        TreeNode root = new TreeNode(list.get(middle));
        root.left = sortedArrayToBST(list, l, middle);
        root.right = sortedArrayToBST(list, middle + 1, r);
        return root;
    }
    /**
     * 思路3:通过快慢节点 慢节点走1,快节点走2,找到中间节点; 然后递归, 还不如转数组;
     */

    /**
     * <p>
     * <p>
     * 思路2:我的思路:
     * . 每次填充左子树
     * . 左子树填充完后,本身作为左子树节点,当前节点为根节点
     * . 直至到节点结束,此时检查根节点到右子树的高度和最大高度的差值;
     * .  如果大于1 则发生右旋;
     * .       寻找到左子树中的差值的高度右节点, 右旋作为根节点; 本身的右子树,作为原本的父节点的左节点
     * .
     * todo 想法有点错误了, 结果会导致右子树中的左右子树的高度不平衡 XXXX 错误的想法,但保留着;
     * <p>
     * 正确是需要类似sortedListToBST(). 先找到中间节点再做分布;
     */
    public static TreeNode sortedListToBST2(ListNode head) {
        if (head == null) {
            return null;
        }
        TreeNode root = new TreeNode(0);
        int high = 0;
        head = sortedListToBST(root, head, high);
        TreeNode middle;
        for (; head != null; high++) {
            middle = new TreeNode(head.val);
            middle.left = root.right;
            head = head.next;
            head = sortedListToBST(middle, head, high);
            root.right = middle;
        }
        root = root.right;
        root.right = rightCycle(root.right, -1);
        root = rightCycle(root, high);
        return root;
    }

    public static TreeNode rightCycle(TreeNode root, int high) {
        if (root == null) {
            return null;
        }
        TreeNode res;
        if (high == -1) {
            res = root;
            while (res != null) {
                res = res.left;
                high++;
            }
        }
        int rightHigh = 0;
        res = root.right;
        while (res != null) {
            res = res.left;
            rightHigh++;
        }
        if (high - rightHigh > 1) {
            TreeNode l = root.left;
            for (int k = rightHigh; k > 0; k--) {
                l = l.right;
            }
            res = l.right;
            l.right = res.left;
            res.left = root.left;
            root.left = null;
            TreeNode temp = res;
            while (temp.right != null) {
                temp = temp.right;
            }
            temp.right = root;
            return res;
        }
        return root;
    }

    public static ListNode sortedListToBST(TreeNode root, ListNode head, int high) {
        if (high == 0) {
            if (null == head) {
                return null;
            } else {
                root.right = new TreeNode(head.val);
                return head.next;
            }
        }
        if (head == null) {
            return null;
        }
        head = sortedListToBST(root, head, high - 1);
        if (head != null) {
            TreeNode node = new TreeNode(head.val);
            node.left = root.right;
            root.right = node;
            head = head.next;
            head = sortedListToBST(node, head, high - 1);
        }
        return head;
    }
}
