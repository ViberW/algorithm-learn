package com.welph.leecode.algorithm.marscode;

/**
 * 小R定义一个数组的“权值”为相邻两数乘积为奇数的对数。给定一个整数 n，表示数组的长度，
 * 即需要求从1到n的所有排列的权值之和。每个排列包含从1到n的每个正整数且仅出现一次。
 * 由于结果可能非常大，答案需要对1000000007取模。
 *
 * 例如：对于数组 [4, 3, 1, 5, 2]，有两对相邻元素的乘积为奇数：3 * 1 = 3 和 1 * 5 = 5，因此权值为 2。
 */
public class MultiOddCount {

    /**
     * 这里对数 是表示乘积为奇数的个数, 有多少对
     *  {@link GoodEvenArray}这里是相邻相乘等于偶数类似
     */
    public static int solution(int n) {
        if (n < 3) {
            return 0;
        }
        int mod = 1000000007;
        int odd = (n + 1) / 2; //奇数的个数
        int even = n - odd;

        //单纯odd和even的考虑顺序的情况下 排序可能数: (odd+even)!/odd!*even! , 即在(odd+even)个空位中抽出odd个空位存储奇数
        // 再来个: 长度为odd的数组, 能够以逗号划分, 有多少中可能呢. 相当于(odd-1)个间隙空位中,是否选择都好, 即 2^(odd-1)次方
        //问题就变成了 odd个值, 划分为x个堆, 每个堆至少一个, 有多少中可能.  {BuildArray} {PutApple} --------忽略这段话,当时的想法

        //dp[i][j][] 到达i时有j个奇数  [0]结尾偶数, [1]奇数  .优化掉第一维的空间
        long[][] dp = new long[odd + 1][2];
        for (int i = 1; i <= n; i++) {
            for (int j = Math.min(odd, i); j >= 0; j--) {
                //偶数
                dp[j][0] = (dp[j][0] + dp[j][1]) % mod;
                //结尾 奇数
                if (j > 0) {
                    long addition = 0;
                    if (j == 2) {
                        addition = 1;
                    } else if (j > 2) {
                        //计算C(j-2,i-2) //在i-2种.选出j-2用于存储奇数的值 : (i-2)的阶乘/((j-2)的阶乘 * ((i-2)-(j-2))的阶乘)
                        long ci = 1;
                        long oi = 1;
                        for (int k = j - 1; k <= i - 2; k++) { //(i-2)!/(j-2)!
                            ci = (k * ci) % mod;
                        }
                        for (int k = 1; k <= i - j; k++) { //(i-j)!
                            oi = (k * oi) % mod;
                        }
                        addition = ci / oi;
                    }
                    dp[j][1] = (dp[j - 1][0] + dp[j - 1][1] + addition) % mod;
                }
            }

        }
        //单顺序下的奇数能分割的可能数
        long singleCount = (dp[odd][0] + dp[odd][1]) % mod; //顺序不变的情况下, 奇数和偶数的组成权值的大小

        long odds = 1; //奇数选择的可能
        for (int i = 1; i <= odd; i++) {
            odds = (odds * i) % mod;
        }
        long evens = 1; //所有偶数的排序可能
        for (int i = 1; i <= even; i++) {
            evens = (evens * i) % mod;
        }
        return (int) ((((odds * evens) % mod) * singleCount) % mod);
    }

    public static void main(String[] args) {
        System.out.println(solution(5) == 144);
        System.out.println(solution(3) == 4);
        System.out.println(solution(6) == 720);
        System.out.println(solution(17));//861390920
    }
}
