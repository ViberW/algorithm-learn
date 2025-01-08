package com.welph.leecode.algorithm.marscode.hard;

import java.util.Arrays;

/**
 * 小M有n 张卡牌，每张卡牌的正反面分别写着不同的数字，正面是ai，背面是bi
 * 小M希望通过选择每张卡牌的一面，使得所有向上的数字之和可以被3整除。你需要告诉小M，
 * 一共有多少种不同的方案可以满足这个条件。由于可能的方案数量过大，结果需要对100000007取模。
 * <p>
 * 例如：如果有3张卡牌，正反面数字分别为 (1,2)，(2,3) 和 (3,2)，你需要找到所有满足这3张卡牌正面或背面朝上的数字之和可以被3整除的组合数。
 */
public class CardSelectSum {
    public static int solution(int n, int[] a, int[] b) {
        int mod = 1000000007;
        int[] pre = new int[3];
        int[] cur = new int[3];
        cur[0] = 1;
        for (int i = 0; i < n; i++) {
            int[] tmp = pre;
            pre = cur;
            cur = tmp;
            Arrays.fill(cur, 0);
            for (int j = 0; j < 3; j++) {
                if (pre[j] > 0) {
                    int k = (j + a[i]) % 3;
                    cur[k] = (cur[k] + pre[j]) % mod;
                }
            }

            for (int j = 0; j < 3; j++) {
                if (pre[j] > 0) {
                    int k = (j + b[i]) % 3;
                    cur[k] = (cur[k] + pre[j]) % mod;
                }
            }
        }
        return cur[0];
    }

    public static void main(String[] args) {
        System.out.println(solution(3, new int[]{1, 2, 3}, new int[]{2, 3, 2}) == 3);
        System.out.println(solution(4, new int[]{3, 1, 2, 4}, new int[]{1, 2, 3, 1}) == 6);
        System.out.println(solution(5, new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 5}) == 32);
    }
}
