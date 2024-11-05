package com.welph.leecode.algorithm.marscode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 小S在码头租用货船，有 Q 种不同类型的货船可供选择。每种货船有固定的数量 m[i]、租赁成本 v[i] 和最大载货量 w[i]。
 * 小S希望在预算 V 元内，租用能够承载最大总货物的货船组合。每种货船的具体信息包括数量、租赁价格和载货量。
 * 小S需要你帮忙计算在给定预算下，她能租用的货船的最大总载货量是多少。
 * <p>
 * Q: 货船的种类数量。
 * V: 李华可用的总预算（单位：元）。
 * ships: 一个列表，其中每个元素是一个元组 [m[i], v[i], w[i]]，分别表示第 i 种货船的数量、租赁价格和每艘货船的最大载货量。
 */
public class BoatPackageSelect {
    //01背包
    public static int solution(int Q, int V, List<List<Integer>> ships) {
        int[] f = new int[V + 1];
        for (int i = 0; i < Q; ++i) {
            List<Integer> integers = ships.get(i);
            Integer im = integers.get(0);
            Integer iv = integers.get(1);
            Integer iw = integers.get(2);
            for (int j = V; j >= iv; --j) {
                for (int k = im; k >= 0; --k) { // 展开成01背包问题并处理,也是倒序
                    if (j >= k * iv) {
                        f[j] = Math.max(f[j], f[j - k * iv] + k * iw);
                    }
                }
            }
        }
        return f[V];
    }

    public static void main(String[] args) {
        // You can add more test cases here
        List<List<Integer>> ships = new ArrayList<>();
        ships.add(Arrays.asList(2, 3, 2));
        ships.add(Arrays.asList(3, 2, 10));

        List<List<Integer>> ships2 = new ArrayList<>();
        ships2.add(Arrays.asList(30, 141, 47));
        ships2.add(Arrays.asList(9, 258, 12));
        ships2.add(Arrays.asList(81, 149, 13));
        ships2.add(Arrays.asList(91, 236, 6));
        ships2.add(Arrays.asList(27, 163, 74));
        ships2.add(Arrays.asList(34, 13, 58));
        ships2.add(Arrays.asList(61, 162, 1));
        ships2.add(Arrays.asList(80, 238, 29));
        ships2.add(Arrays.asList(36, 264, 28));
        ships2.add(Arrays.asList(36, 250, 2));
        ships2.add(Arrays.asList(70, 214, 31));
        ships2.add(Arrays.asList(39, 116, 39));
        ships2.add(Arrays.asList(83, 287, 4));
        ships2.add(Arrays.asList(61, 269, 94));
        ships2.add(Arrays.asList(23, 187, 46));
        ships2.add(Arrays.asList(78, 33, 29));
        ships2.add(Arrays.asList(46, 151, 2));
        ships2.add(Arrays.asList(71, 249, 1));
        ships2.add(Arrays.asList(67, 76, 85));
        ships2.add(Arrays.asList(72, 239, 17));
        ships2.add(Arrays.asList(61, 256, 49));
        ships2.add(Arrays.asList(48, 216, 73));
        ships2.add(Arrays.asList(39, 49, 74));

        System.out.println(solution(2, 10, ships) == 32);
        System.out.println(solution(23, 400, ships2) == 1740);
    }
}
