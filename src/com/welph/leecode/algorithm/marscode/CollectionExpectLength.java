package com.welph.leecode.algorithm.marscode;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

/**
 * 小R有 n 个集合，她想通过随机选择两个集合，并计算它们的并集，来求出这个并集大小的期望值。
 * 每个集合中的元素都是唯一的且互不相同。她需要计算出随机选择两个集合并集大小的期望值，并且要求结果保留两位小数。
 *
 * 保证输入至少有两个集合。
 */
public class CollectionExpectLength {

    /**
     * 求取期望的长度
     * 如n = 3,st = [[1, 4], [2, 5], [3, 6, 7]]
     * 三种可能并集长度为 4, 5, 5
     * 期望值 = 4*1/3+5*1/3+5*1/3 = 4.67
     */
    public static String solution(int n, int[][] st) {
        // write code here
        int allLength = 0;
        int perhaps = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int val : st[i]) {
                set.add(val);
            }
            for (int j = i + 1; j < n; j++) {
                perhaps++;
                allLength += set.size();
                for (int val : st[j]) {
                    if (!set.contains(val)) {
                        allLength++;
                    }
                }
            }
            set.clear();
        }
        return perhaps == 0 ? "0" :
                BigDecimal.valueOf(allLength)
                        .divide(BigDecimal.valueOf(perhaps), 2, RoundingMode.HALF_UP).toString();
    }

    public static void main(String[] args) {
        System.out.println(solution(2, new int[][]{{1, 2}, {1, 3, 5}}).equals("4.00"));
        System.out.println(solution(3, new int[][]{{1, 4}, {2, 5}, {3, 6, 7}}).equals("4.67"));
        System.out.println(solution(2, new int[][]{{10, 20, 30}, {10, 30, 50, 70}}).equals("5.00"));
    }
}
