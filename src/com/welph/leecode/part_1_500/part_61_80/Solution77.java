package com.welph.leecode.part_1_500.part_61_80;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * <p>
 * 示例:
 * <p>
 * 输入: n = 4, k = 2
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
            return new ArrayList<List<Integer>>() {{
                add(new ArrayList<>(size));
            }};
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
}
