package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小R正在研究一种特殊的排列，称为“好排列”。一个排列被称为“好排列”，当且仅当其中所有相邻的两个数的乘积均为偶数。
 * 现在给定一个正整数 n，小R想知道，长度为 n 的好排列共有多少种。由于结果可能非常大，你需要将结果对 10000007 取模后输出。
 */
public class GoodEvenArray {

    public static int solution(int n) {
        int mod = 1000000007;
        int even = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & 1) == 0) {
                even++;
            }
        }
        int odd = n - even;
        long odds = 1; //奇数选择的可能
        for (int i = 1; i <= odd; i++) {
            odds = (odds * i) % mod;
        }
        long evens = 1;
        for (int i = 1; i <= even; i++) {
            evens = (evens * i) % mod; //所有偶数的排序可能
        }
        //在每一种排序中 找到连续的
        if (odd == even) { //C(even+1, even)的可能行
            //C(even+1, odd) 在event+1个空位中找出odd个空位, 如5个找出4个. 5!/(4!*(5-4)!) = 5
            return (int) ((((odds * evens) % mod) * (even + 1)) % mod);
        } else {
            return (int) ((odds * evens) % mod);
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(1));
        System.out.println(solution(2) == 2);
        System.out.println(solution(3) == 2);
        System.out.println(solution(5) == 12);
    }
}
