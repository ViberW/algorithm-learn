package com.welph.leecode.algorithm.thinking;

import java.util.ArrayList;
import java.util.List;

/**
 * 素数筛 {@link com.welph.leecode.part_1_500.part_201_220.Solution204}
 * 埃氏筛时间复杂度: O(nloglogn)
 * 线性筛时间复杂度: O(n) 但会利用到空间
 */
public class PrimeSieve_17 {

    public static void main(String[] args) {
        System.out.println(countPrimes(100));
    }

    //线性筛{欧拉筛}
    public static int countPrimes(int n) {
        int[] isPrime = new int[n + 1];
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime[i] == 0) {
                primes.add(i);
                for (int p : primes) {
                    if (p * i > n) //超过了就不处理了
                        break;
                    isPrime[p * i] = 1;
                    if (i % p == 0) //若是当前值能够整除该质数, 说明是最小的质数, 后面的就不用处理了
                        break;
                }
            }
        }
        return primes.size();
    }
}
