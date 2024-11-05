package com.welph.leecode.algorithm.marscode;

/**
 * 小R正在研究一种特殊的排列，称为“好排列”。一个排列被称为“好排列”，当且仅当其中所有相邻的两个数的乘积均为偶数。
 * 现在给定一个正整数 n，小R想知道，长度为 n 的好排列共有多少种。由于结果可能非常大，你需要将结果对 10000007 取模后输出。
 */
public class GoodEvenArray {

    public static int solution(int n) {
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 2;
        }
        int mod = 1000000007;
        int even = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & 1) == 0) {
                even++;
            }
        }
        int odd = n - even;
        //odd至少需要 odd-1个偶数
        if (even < odd - 1) {
            return 0;
        }
        int delta = even - odd + 1;
        int odds = 1; //奇数选择的可能
        int selectEvens = 1; //保证乘积偶数的排序选择
        int deltaEvens = 1;
        for (int i = 1; i <= odd; i++) {
            odds = (odds * i) % mod;
            if (i == odd - 1) {
                selectEvens = odds;
            }
            if (i == delta) {
                deltaEvens = odds;
            }
        }
        int enens = selectEvens;
        for (int i = odd - 1; i <= even; i++) {
            enens = (enens * i) % mod;
            if (i == delta) {
                deltaEvens = enens;
            }
        }
        //从evens! /((even - (odd-1))! *  (odd-1)!)
        //从even中选择odd-1的可能
        int cEvens = (enens / ((deltaEvens * selectEvens) % mod)) % mod;
        int remainEvens = 1;
        for (int i = 0, k = 2 * odd; i < delta; i++, k++) {
            remainEvens = (remainEvens * k) % mod;
        }
        return (((odds * cEvens) % mod) * remainEvens) % mod;
    }

    public static void main(String[] args) {
        System.out.println(solution(2) == 2);
        System.out.println(solution(3) == 2);
        System.out.println(solution(5) == 12);
    }
}
