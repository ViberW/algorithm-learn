package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小S得到了一个由几个元素组成的数组,她想求出所有子序列的平均数之和。
 * 由于子序列的数量非常多,因此需要对结果取模10^9+7来避免结果过大。
 *
 * 子序列是从原数组中选择部分元素,保持原数组的顺序形成的新数组。
 *
 * 你需要输出所有子序列的平均数之和对10^9+7取模的值。
 * 可以证明,最终的答案一定是一个有理数, a/b对p取模的意义是在[0,p-1]区间找到一个满足(x⋅b) (mod p)=a。
 */
public class SubArraySumAvg {

    public static long solution(int n, int[] arr) {
        int mod = 1000000007;
        long result = 0;
        //dp[i] = dp[i-1]+ (arr[i]/i);
        //记录当前处节点为末尾, 长度为i的 [0]平均值总和 [1]可能的情况总和
        long[][] dp = new long[n + 1][2];
        dp[0][1] = 1;//长度为0的可能有一种
        for (int i = 1; i <= n; i++) {
            for (int j = i; j > 0; j--) {
                dp[j][0] = (dp[j][0] + dp[j - 1][0] + arr[i - 1] * dp[j - 1][1]) % mod;
                dp[j][1] += dp[j - 1][1];
            }
        }
        for (int i = 1; i <= n; i++) {
            //考虑浮点数的问题, 如例子2
            result = (result + dp[i][0] / i) % mod;
            if (dp[i][0] % i != 0) {
                //x*b= p*k+a ; => x*b = (p*k+a);
                long val = mod + dp[i][0] % i;
                while (val % i != 0) {
                    val += mod;
                }
                result = (result + val / i) % mod;
            }
        }
        return result;
    }

    public static long solution2(int n, int[] arr) {
        int mod = 1000000007;
        long result = 0;
        //dp[i] = dp[i-1]+ (arr[i]/i);
        //记录当前处节点为末尾, 长度为i的 [0]平均值总和 [1]可能的情况总和
        long[][] dp = new long[n + 1][2];
        dp[0][1] = 1;//长度为0的可能有一种
        for (int i = 1; i <= n; i++) {
            for (int j = i; j > 0; j--) {
                dp[j][0] = (dp[j][0] + dp[j - 1][0] + arr[i - 1] * dp[j - 1][1]) % mod;
                dp[j][1] += dp[j - 1][1];
            }
        }
        for (int i = 1; i <= n; i++) {
            //考虑浮点数的问题, 如例子2
            result = (result + dp[i][0] / i) % mod;
            if (dp[i][0] % i != 0) {
                //x*b= a (mod p)
                //通过费马小定理优化 : 如果p是一个质数，而整数a不是p的倍数，则有 a^(p-1)=1 (mod p)
                // x = a/b (mod p)
                //这里需要求得 b^(-1) (mod p) = b^(p-2) (mod p)  至于如何求得b^(p-2) 快速幂
                long val = 1;
                long base = i;
                int exp = mod - 2;  // 根据费马小定理
                while (exp > 0) {
                    if (exp % 2 == 1) {
                        val = (val * base) % mod;
                    }
                    base = (base * base) % mod;
                    exp >>= 1;
                }
                result = (result + (dp[i][0] % i) * val) % mod;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution2(3, new int[]{1, 2, 3}) == 14);
        /*
            [2] [1] [2,1]
            sum = 2+1 + (2+1)/2  = 4+ 1/2
            由于 500000004*2%mod = 1;
            所以结果为500000004 +4 = 500000008
         */
        System.out.println(solution2(2, new int[]{2, 1}) == 500000008);
        System.out.println(solution2(4, new int[]{1, 1, 1, 1}) == 15);
        System.out.println(1000000007 / 2);
    }
}
