package com.welph.leecode.part_1_500.part_61_80;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * <p>
 * 示例:
 * <p>
 * 输入:n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 */
public class Solution77 {

    public static void main(String[] args) {

        System.out.println(combine(4, 2));
        System.out.println(combine2(4, 2));
        generate(5, 3);
    }

    public static List<List<Integer>> combine(int n, int k) {
        int size = k * 4 / 3 + 1;
        return combineItem(1, n, k, size);
    }

    private static List<List<Integer>> combineItem(int i, int n, int k, int size) {
        if (n - i + 1 < k) {
            return null;
        }
        if (k == 0) {
            return new ArrayList<List<Integer>>() {
                {
                    add(new ArrayList<>(size));
                }
            };
        }
        List<List<Integer>> results = new ArrayList<>();
        for (; i <= n; i++) {
            List<List<Integer>> res = combineItem(i + 1, n, k - 1, size);
            if (res == null) {
                break;
            }
            for (List<Integer> rs : res) {
                rs.add(i);
                results.add(rs);
            }
        }
        return results;
    }

    /* 官方题解: 通过二进制标记选择数字位 */
    static List<Integer> temp = new ArrayList<Integer>();
    static List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public static List<List<Integer>> combine2(int n, int k) {
        List<Integer> temp = new ArrayList<Integer>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 初始化
        // 将 temp 中 [0, k - 1] 每个位置 i 设置为 i + 1，即 [0, k - 1] 存 [1, k]
        // 末尾加一位 n + 1 作为哨兵
        for (int i = 1; i <= k; ++i) {
            temp.add(i);
        }
        temp.add(n + 1);

        int j = 0;
        while (j < k) {
            ans.add(new ArrayList<Integer>(temp.subList(0, k)));
            j = 0;
            // 寻找第一个 temp[j] + 1 != temp[j + 1] 的位置 t
            // 我们需要把 [0, t - 1] 区间内的每个位置重置成 [1, t]
            while (j < k && temp.get(j) + 1 == temp.get(j + 1)) {
                temp.set(j, j + 1);
                ++j;
            }
            // j 是第一个 temp[j] + 1 != temp[j + 1] 的位置
            temp.set(j, temp.get(j) + 1);
        }
        return ans;
    }

    /*
     * 上面的方法next(x) 是通过最开始二进制n=4 k=2
     * 0011 不断进化的
     * next的规则:
     * 规则一：x 的最低位为 111，这种情况下，如果末尾由 t 个连续的 1，我们直接将倒数第 t 位的 1 和倒数第 t+1 位的 0 替换，
     * 就可以得到 next(x)。如 0011→0101,0101→0110
     * 规则二：x 的最低位为 0，这种情况下，末尾有 t 个连续的 0，而这 t 个连续的 0 之前有 m 个连续的1，
     * 我们可以将倒数第 t+m位置的 1 和倒数第 t+m+1位的 0 对换，然后把倒数第
     * t+1位到倒数第 t+m−1 位的 111 移动到最低位。如 0110→1001, 10010110→1001
     * ---------------------------------
     * 说明:
     * 规则一 就是不断地调换连续1依次的进位
     * 规则二 就是最高位的1进位, 剩下的1回到初始零位
     */
    // 自己来书写下上述二进制的标记方法
    public static void generate(int n, int k) {
        int[] ans = new int[k + 1];
        for (int i = 0; i < k; i++) {
            ans[i] = i;
        }
        ans[k] = n;
        // 最终的长度为n
        int j = 0;
        while (j < k) {
            System.out.println(Integer.toBinaryString(buildValue(ans)));
            j = 0;
            while (j < k && ans[j] + 1 == ans[j + 1]) {
                ans[j] = j;
                ++j;
            }
            ans[j] = ans[j] + 1;
        }
        /*
         * 00111
         * 01011 规则一
         * 01101 规则一
         * 01110 规则一
         * 10011 规则二
         * 10101 规则一
         * 10110 规则一
         * 11001 规则二
         * 11010 规则一
         * 11100 规则二
         */
    }

    private static int buildValue(int[] ans) {
        int val = 0;
        for (int i = 0; i < ans.length - 1; i++) {
            val |= (1 << ans[i]);
        }
        return val;
    }
}
