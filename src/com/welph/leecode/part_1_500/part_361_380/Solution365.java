package com.welph.leecode.part_1_500.part_361_380;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
 * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
 * 你允许：
 * 装满任意一个水壶
 * 清空任意一个水壶
 * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
 * <p>
 * 示例 1: (From the famous "Die Hard" example)
 * 输入: x = 3, y = 5, z = 4
 * 输出: True
 * <p>
 * 示例 2:
 * 输入: x = 2, y = 6, z = 5
 * 输出: False
 */
public class Solution365 {

    public static void main(String[] args) {
        System.out.println(canMeasureWater1(3, 5, 4));
        System.out.println(canMeasureWater1(2, 6, 5));
    }

    /**
     * BFS 广度优先搜索,将水壶x，y的相关操作分解为6个状态，分别为
     * 1.给x装满水
     * 2.给y装满水
     * 3.清空x的水
     * 4.清空y的水
     * 5.x向y倒水，直到x空或者y满
     * 6.y向x倒水，直到y空或者x满
     * -------------
     * 通过不断的搜索，如果x，y中的水满足
     * >>> x == z || y == z || x + y == z
     * 返回true，否则继续搜索直到所有情况都搜索完毕 -- todo 深度优先搜索
     * 会超时,一旦jug1Capacity和jug2Capacity相差不大且值比较大
     */
    public static boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        Set<Pair> crossed = new HashSet<>();
        return canMeasureWater(jug1Capacity, jug2Capacity, targetCapacity, crossed, 0, 0);
    }

    public static boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity,
            Set<Pair> crossed, int x, int y) {
        if (x == targetCapacity || y == targetCapacity || x + y == targetCapacity) {
            return true;
        }
        Pair pair = new Pair(x, y);
        if (crossed.contains(pair)) {
            return false;
        }
        crossed.add(pair);
        // 给x装满水
        if (canMeasureWater(jug1Capacity, jug2Capacity, targetCapacity, crossed, jug1Capacity, y)) {
            return true;
        }
        // 给y装满水
        if (canMeasureWater(jug1Capacity, jug2Capacity, targetCapacity, crossed, x, jug2Capacity)) {
            return true;
        }
        // 清空x的水
        if (canMeasureWater(jug1Capacity, jug2Capacity, targetCapacity, crossed, 0, y)) {
            return true;
        }
        // 清空y的水
        if (canMeasureWater(jug1Capacity, jug2Capacity, targetCapacity, crossed, x, 0)) {
            return true;
        }
        // x向y倒水，直到x空或者y满
        if (canMeasureWater(jug1Capacity, jug2Capacity, targetCapacity, crossed,
                Math.max(0, x + y - jug2Capacity),
                Math.min(x + y, jug2Capacity))) {
            return true;
        }
        // y向x倒水，直到y空或者x满
        if (canMeasureWater(jug1Capacity, jug2Capacity, targetCapacity, crossed,
                Math.min(x + y, jug1Capacity),
                Math.max(0, x + y - jug1Capacity))) {
            return true;
        }
        return false;
    }

    // 效率高一点 广度优先算法
    public static boolean canMeasureWater1(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        Set<Pair> crossed = new HashSet<>();
        Queue<Pair> pairs = new LinkedList<>();
        pairs.add(new Pair(0, 0));
        Pair p;
        int x;
        int y;
        while (!pairs.isEmpty()) {
            p = pairs.poll();
            x = p.j1;
            y = p.j2;
            if (x == targetCapacity || y == targetCapacity || x + y == targetCapacity) {
                return true;
            }
            p = new Pair(jug1Capacity, y);
            if (!crossed.contains(p)) {
                pairs.add(p);
                crossed.add(p);
            }
            p = new Pair(x, jug2Capacity);
            if (!crossed.contains(p)) {
                pairs.add(p);
                crossed.add(p);
            }
            p = new Pair(0, y);
            if (!crossed.contains(p)) {
                pairs.add(p);
                crossed.add(p);
            }
            p = new Pair(x, 0);
            if (!crossed.contains(p)) {
                pairs.add(p);
                crossed.add(p);
            }
            p = new Pair((x + y) <= jug2Capacity ? 0 : x + y - jug2Capacity,
                    Math.min(x + y, jug2Capacity));
            if (!crossed.contains(p)) {
                pairs.add(p);
                crossed.add(p);
            }
            p = new Pair(Math.min(x + y, jug1Capacity),
                    (x + y) <= jug1Capacity ? 0 : x + y - jug1Capacity);
            if (!crossed.contains(p)) {
                pairs.add(p);
                crossed.add(p);
            }
        }
        return false;
    }

    static class Pair {
        int j1;
        int j2;

        public Pair(int j1, int j2) {
            this.j1 = j1;
            this.j2 = j2;
        }

        @Override
        public int hashCode() {
            return j1 + j2;
        }

        @Override
        public boolean equals(Object obj) {
            Pair p = (Pair) obj;
            return p.j1 == j1 && p.j2 == j2;
        }
    }

    /* 官方题解 */
    // 思路和上面一样的 反正就不能使用深度搜索, 会有超时问题. 资源消耗也高
    public boolean canMeasureWater2(int x, int y, int z) {
        Deque<int[]> stack = new LinkedList<int[]>();
        stack.push(new int[] { 0, 0 });
        Set<Long> seen = new HashSet<Long>();
        while (!stack.isEmpty()) {
            if (seen.contains(hash(stack.peek()))) {
                stack.pop();
                continue;
            }
            seen.add(hash(stack.peek()));

            int[] state = stack.pop();
            int remain_x = state[0], remain_y = state[1];
            if (remain_x == z || remain_y == z || remain_x + remain_y == z) {
                return true;
            }
            // 把 X 壶灌满。
            stack.push(new int[] { x, remain_y });
            // 把 Y 壶灌满。
            stack.push(new int[] { remain_x, y });
            // 把 X 壶倒空。
            stack.push(new int[] { 0, remain_y });
            // 把 Y 壶倒空。
            stack.push(new int[] { remain_x, 0 });
            // 把 X 壶的水灌进 Y 壶，直至灌满或倒空。
            stack.push(new int[] { remain_x - Math.min(remain_x, y - remain_y),
                    remain_y + Math.min(remain_x, y - remain_y) });
            // 把 Y 壶的水灌进 X 壶，直至灌满或倒空。
            stack.push(new int[] { remain_x + Math.min(remain_y, x - remain_x),
                    remain_y - Math.min(remain_y, x - remain_x) });
        }
        return false;
    }

    public long hash(int[] state) {
        return (long) state[0] * 1000001 + state[1];
    }

    /*
     * 基于数学的思路
     * <a herf="https://leetcode.cn/problems/water-and-jug-problem/solutions/161010/shui-hu-wen-ti-by-leetcode-solution/">
     * 
     * 题目的最终解: ax+by=z
     */
    public boolean canMeasureWater4(int x, int y, int z) {
        if (x + y < z) {
            return false;
        }
        if (x == 0 || y == 0) {
            return z == 0 || x + y == z;
        }
        return z % gcd(x, y) == 0;
    }

    //@link ExpandEuclidean_8
    public int gcd(int x, int y) {
        int remainder = x % y;
        while (remainder != 0) {
            x = y;
            y = remainder;
            remainder = x % y;
        }
        return y;
    }

}
