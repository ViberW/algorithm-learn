package com.welph.leecode.algorithm.marscode.middle;

import java.util.HashSet;
import java.util.Set;

/**
 * 小M拿到一个数组,她可以进行多次操作,每次操作可以选择两个元素ai和aj,
 * 并选择ai的个因子x,然后将ai变为ai/x,并将aj变为aj * x。
 *
 * 她的目标是通过有限次操作,使得数组中的每个元素最多只包含一种素因子。
 * 素因子的定义是:若æ能被素数p整除,那么p是æ的一个素因子。例如,12的素因子有2和3。
 * 你的任务是判断是否有可能通过有限次操作,使数组中的每个元素最多只包含一种素因子。
 *
 * 如果可以,输出"Yes",否则输出"No"。
 */
public class ArrayChangeToMetaFactor {
    public static String solution(int n, int[] a) {
        //即想办法将相同的质数放到统一地方去,  就意味着需要质数个数小于等于n
        Set<Integer> factor = new HashSet<>();
        for (int i = 0; i < n; i++) {
            metaFactor(factor, a[i]);
        }
        return factor.size() <= n ? "Yes" : "No";
    }

    private static void metaFactor(Set<Integer> factor, int v) {
        int i = 2;
        while (i * i <= v) {
            if (v % i == 0) {
                while (v % i == 0) {
                    v /= i;
                }
                factor.add(i);
            }
            i++;
        }
        if (v > 1) {
            factor.add(v);
        }
    }

    public static void main(String[] args) {
        //[2,2,3,4] 或 [1,2,3,4] 本身
//        System.out.println(solution(4, new int[]{1, 2, 3, 4}).equals("Yes"));
//        System.out.println(solution(2, new int[]{10, 12}).equals("No"));
//        //[2,81,5]
//        System.out.println(solution(3, new int[]{6, 9, 15}).equals("Yes"));
        System.out.println(solution(1, new int[]{11}));
    }
}
