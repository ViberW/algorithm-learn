package com.welph.leecode.algorithm.marscode.hard;

import java.util.*;

/**
 * 小S在学习素数因子的分解，她希望在[1,n]的范围内，找到一些连续的自然数，
 * 这些数的乘积最多包含k个不同的素因子。你的任务是帮助小S找到可以取的连续自然数的最大长度。
 * <p>
 * 连续的自然数指的是不能重复且相邻数的差为1的一组正整数，例如 [2, 3, 4, 5] 和 [5, 6, 7] 都是连续的取数。
 */
public class NumMultipleLonger {

    /**
     * {@link com.welph.leecode.algorithm.thinking.PrimeSieve_17}
     */
    public static int solution(int n, int k) {
        Set<Integer>[] primes = new Set[n + 1];
        for (int i = 2; i <= n; i++) {
            primes[i] = new HashSet<>();
        }
        for (int i = 2; i <= n; i++) {
            if (primes[i].isEmpty()) {
                primes[i].add(i);
                //向后遍历,逐渐到达n
                for (int j = i + i; j <= n; j += i) {
                    primes[j].add(i);
                }
            }
        }
        int l = 2;
        Map<Integer, Integer> factorCount = new HashMap<>();
        int ret = 1;
        for (int r = 2; r <= n; r++) {
            for (Integer prime : primes[r]) {
                factorCount.put(prime, factorCount.getOrDefault(prime, 0) + 1);
            }
            while (factorCount.size() > k) {
                for (Integer prime : primes[l]) {
                    Integer i = factorCount.get(prime);
                    if (i == 1) {
                        factorCount.remove(prime);
                    } else {
                        factorCount.put(prime, i - 1);
                    }
                }
                l++;
            }
            ret = Math.max(ret, r - l + (l == 2 ? 2 : 1));
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(solution(10, 3) == 6);
        System.out.println(solution(20, 5) == 12);
        System.out.println(solution(100, 4) == 10);
        System.out.println(solution(1, 7) == 1);
    }
}
