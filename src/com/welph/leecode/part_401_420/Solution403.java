package com.welph.leecode.part_401_420;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。
 * 青蛙可以跳上石子，但是不可以跳入水中。
 * 给你石子的位置列表 stones（用单元格序号 升序 表示），请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。
 * 开始时，青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格 1 跳至单元格 2 ）。
 * 如果青蛙上一步跳跃了k个单位，那么它接下来的跳跃距离只能选择为k - 1、k或k + 1 个单位。另请注意，青蛙只能向前方（终点的方向）跳跃。
 * <p>
 * 示例 1：
 * 输入：stones = [0,1,3,5,6,8,12,17]
 * 输出：true
 * 解释：青蛙可以成功过河，按照如下方案跳跃：跳 1 个单位到第 2 块石子, 然后跳 2 个单位到第 3 块石子, 接着 跳 2 个单位到第 4 块石子, 然后跳 3 个单位到第 6 块石子, 跳 4 个单位到第 7 块石子, 最后，跳 5 个单位到第 8 个石子（即最后一块石子）。
 * <p>
 * 示例 2：
 * 输入：stones = [0,1,2,3,4,8,9,11]
 * 输出：false
 * 解释：这是因为第 5 和第 6 个石子之间的间距太大，没有可选的方案供青蛙跳跃过去。
 * <p>
 * 提示：
 * 2 <= stones.length <= 2000
 * 0 <= stones[i] <= 2^31 - 1
 * stones[0] == 0
 */
public class Solution403 {

    public static void main(String[] args) {
        System.out.println(canCross1(new int[]{0, 1, 3, 5, 6, 8, 12, 17}));
        System.out.println(canCross1(new int[]{0, 1, 2, 3, 4, 8, 9, 11}));
    }

    /**
     * 保存step的数据
     */
    public static boolean canCross1(int[] stones) {
        int len = stones.length;
        int val;
        Node head = new Node(0, 0);
        Node tail = new Node(0, 0);
        head.next = new Node(1, 1);
        head.next.pre = head;
        head.next.next = tail;
        tail.pre = head.next;
        int max = 1;
        Node node;
        int target = stones[len - 1];
        for (int i = 1; i < len; i++) {
            val = stones[i];
            int size = max;
            node = head.next;
            while (size-- > 0) {
                if (node.t < val) {
                    remove(node);
                    max--;
                } else if (node.t == val) {
                    if (val + node.k == target
                            || val + node.k - 1 == target
                            || val + node.k + 1 == target) {
                        return true;
                    }
                    //todo 需要去除重复的k和位置
                    add(new Node(val + node.k, node.k), tail);
                    add(new Node(val + node.k + 1, node.k + 1), tail);
                    if (node.k > 1) {
                        add(new Node(val + node.k - 1, node.k - 1), tail);
                        max++;
                    }
                    remove(node);
                    max++;
                }
                node = node.next;
            }
        }
        return false;
    }

    private static void add(Node node, Node tail) {
        tail.pre.next = node;
        node.pre = tail.pre;
        tail.pre = node;
        node.next = tail;
    }

    private static void remove(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    static class Node {
        int t;
        int k;
        Node pre;
        Node next;

        public Node(int t, int k) {
            this.t = t;
            this.k = k;
        }
    }

    /**
     * 不同于 {@link com.welph.leecode.part_41_60.Solution45}
     * 这里只能-使用动态规划, 因为跳跃长度无法满足所有位置 --只能向前跳 step>0
     * dp[i][k] == 表示到达i成功最后一次需要k步
     * <p>
     * -------------------------耗时有点长
     * 思考: 是不是可以按照在i之前的所有可能的step统计, 并按照step对i查看是否存在对应的位置呢
     */
    public static boolean canCross(int[] stones) {
        int len = stones.length;
        Set<Integer>[] dp = new Set[len];
        dp[0] = Collections.singleton(0);
        int val;
        int k;
        Set<Integer> set;
        for (int i = 1; i < len; i++) {
            val = stones[i];
            for (int j = 0; j < i; j++) {
                k = val - stones[j];
                if (null != (set = dp[j])) {
                    if (set.contains(k) || set.contains(k - 1) || set.contains(k + 1)) {
                        getSet(dp, i).add(k);
                    }
                }
            }
        }
        return dp[len - 1] != null;
    }

    private static Set<Integer> getSet(Set<Integer>[] dp, int i) {
        Set<Integer> set = dp[i];
        if (set == null) {
            set = new HashSet<>();
            dp[i] = set;
        }
        return set;
    }
}
