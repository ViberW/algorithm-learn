package com.welph.leecode.part_500_1000.part_521_540;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 假设有从 1 到 n 的 n 个整数。用这些整数构造一个数组 perm（下标从 1 开始），
 * 只要满足下述条件 之一 ，该数组就是一个 优美的排列 ：
 * <p>
 * perm[i] 能够被 i 整除
 * i 能够被 perm[i] 整除
 * 给你一个整数 n ，返回可以构造的 优美排列 的 数量 。
 * <p>
 * <p>
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 * 解释：
 * 第 1 个优美的排列是 [1,2]：
 * - perm[1] = 1 能被 i = 1 整除
 * - perm[2] = 2 能被 i = 2 整除
 * 第 2 个优美的排列是 [2,1]:
 * - perm[1] = 2 能被 i = 1 整除
 * - i = 2 能被 perm[2] = 1 整除
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * <p>
 * 提示：
 * 1 <= n <= 15
 */
public class Solution526 {

    public static void main(String[] args) {
        System.out.println(countArrangement(2));
        System.out.println(countArrangement(1));
        System.out.println(countArrangement(3));
        System.out.println(countArrangement(10));
        System.out.println(countArrangement(15));
        System.out.println(countArrangement(7));
    }

    /**
     * perm[i] 能够被 i 整除
     * i 能够被 perm[i] 整除
     * -----------------------
     * prem[i]  1 i/k  k*i;
     * 因为长度不大于15 则使用int(二进制) 保持数据是否使用
     */
    public static int countArrangement(int n) {
        int press = 0;
        Map<Pair, Integer> map = new HashMap<>();
        return countArrangement(1, n, map, press);
    }

    private static int countArrangement(int i, int n, Map<Pair, Integer> map, int press) {
        if (i > n) {
            return 1;
        }
        Pair pair = new Pair(i, press);
        if (map.containsKey(pair)) {
            return map.get(pair);
        }
        //选择1
        int c = 0;
        int tmp;
        //选择 k *i
        int k = 1;
        while (k * i <= n) {
            if ((press & (1 << ((i * k) - 1))) == 0) {
                tmp = press;
                c += countArrangement(i + 1, n, map, press | (1 << ((i * k) - 1)));
                press = tmp;
            }
            k++;
        }
        //选择 i/k
        k = 2;
        while (i / k > 0) {
            if (i % k == 0) {
                if ((press & ((1 << ((i / k) - 1)))) == 0) {
                    tmp = press;
                    c += countArrangement(i + 1, n, map, press | (1 << ((i / k) - 1)));
                    press = tmp;
                }
            }
            k++;
        }
        map.put(pair, c);
        return c;
    }

    static class Pair {
        int i;
        int press;

        public Pair(int i, int press) {
            this.i = i;
            this.press = press;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return i == pair.i &&
                    press == pair.press;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, press);
        }
    }
}
