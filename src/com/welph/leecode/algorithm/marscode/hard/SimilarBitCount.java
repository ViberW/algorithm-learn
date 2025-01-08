package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小F设计了一种新的校验和方法，用于对二进制位串进行差错控制。
 * 给定一个长度为 n 的二进制位串 S，通过对所有长度为 k 的子串进行异或运算，小F可以计算出该位串的校验和。
 * 现在，小F想知道，有多少个与 S 不同的长度为n 的二进制位串能够产生相同的校验和。
 *
 * 由于结果可能非常大，你需要将结果对 10^9+7 取模。
 */
public class SimilarBitCount {

    public static int solution(int n, int k) {
        //对于n的字符串,能有n-k+1个子串
        //所以最终的校验和有: 1<<k个数

        //相当于是n的多种可能: 1<<n
        //最终结果就是: 1<<n/1<<k  => 1<<(n-k)
        //其他部分就是: 1<<(n-k) -1;
        //问题就回到了快速幂
        int mod = 1000000007;
        long result = 1;
        long base = 2;
        int exp = n - k;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        result--;
        return (int) result;
    }

    public static void main(String[] args) {
        /*
         * 0    1
         * 0    1
         * 所以假设S为其中某一个,那么有且仅有一个相同的
         */
        System.out.println(solution(1, 1) == 0);
        /*
         * 00   01  10  11
         * 0^0  0^1  1^0 1^1
         *  0    1    1   0
         * 所以假设S为其中某一个, 那么有且有一个相同的
         */
        System.out.println(solution(2, 1) == 1);
        System.out.println(solution(2, 2) == 0);
    }
}
