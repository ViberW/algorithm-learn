package com.welph.leecode.part_500_1000.part_621_640;

/**
 * 给出两个整数 n 和 k，找出所有包含从 1 到 n 的数字，且恰好拥有 k 个逆序对的不同的数组的个数。
 * 逆序对的定义如下：对于数组的第i个和第 j个元素，如果满i < j且 a[i] > a[j]，则其为一个逆序对；否则不是。
 * 由于答案可能很大，只需要返回 答案 mod(10^9 + 7) 的值。
 * <p>
 * 示例 1:
 * 输入: n = 3, k = 0
 * 输出: 1
 * 解释:
 * 只有数组 [1,2,3] 包含了从1到3的整数并且正好拥有 0 个逆序对。
 * <p>
 * 示例 2:
 * 输入: n = 3, k = 1
 * 输出: 2
 * 解释:
 * 数组 [1,3,2] 和 [2,1,3] 都有 1 个逆序对。
 * <p>
 * 说明:
 * n 的范围是 [1, 1000] 并且 k 的范围是 [0, 1000]
 */
public class Solution629 {

    public static void main(String[] args) {
        System.out.println(kInversePairs(3, 0));
        System.out.println(kInversePairs(3, 1));
        System.out.println(kInversePairs(2, 2));
        System.out.println(kInversePairs(3, 3));
        System.out.println(kInversePairs(10, 2));
        System.out.println(kInversePairs(1000, 1000));
    }

    /**
     * {@link com.welph.leecode.algorithm.divide.ReversePairCount}
     * 动态规划
     * dp[i][k] = for(0~k-1) dp[i-1][j]  + dp[i-1][k]
     * 时间16%, 空间64% 古德
     */
    public static int kInversePairs(int n, int k) {
        int mod = (int) (Math.pow(10, 9) + 7);
        int[][] dp = new int[n + 1][k + 1];
        dp[1][0] = 1;
        for (int i = 2; i <= n; i++) {
            for (int m = k; m >= 0; m--) {
                //若dp[i - 1][m] =x个逆序对, 则还需要 j<=m+i-1个必须对, 则该位置是固定的
                //最少m个逆序对, 最多m+i-1个逆序对
                for (int j = Math.min(k, m + i - 1); j >= m; j--) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][m]) % mod;
                }
            }
        }
        return dp[n][k];
    }

    //官方题解, 相较于我的少了空间和时间的  因为它将当前次和上次的结合在一起了
    public int kInversePairs2(int n, int k) {
        final int MOD = 1000000007;
        int[][] f = new int[2][k + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= k; ++j) {
                int cur = i & 1, prev = cur ^ 1;
                //当前的j-1次的可能 减去 上一次的j-1的可能  
                //为什么减去? 因为上一次是后面加上f[prev][j-1]的可能, 这个可能是无法动弹的
                f[cur][j] = (j - 1 >= 0 ? f[cur][j - 1] : 0) - (j - i >= 0 ? f[prev][j - i] : 0) + f[prev][j];
                if (f[cur][j] >= MOD) {
                    f[cur][j] -= MOD;
                } else if (f[cur][j] < 0) {
                    f[cur][j] += MOD;
                }
            }
        }
        return f[n & 1][k];
    }
}
