package com.welph.leecode.algorithm.marscode.hard;

/**
 * 在一个充满色彩的艺术画廊里，小艾拥有 n 幅长度不一的画作，每幅画的长度都是从 1 到 n 的自然数。
 * 小艾的目标是将这些画作以特定的顺序摆放，并确保从入口处可以清晰地恰好看到 k 幅画作。
 * 判断某幅画是否可见的标准是：它左侧的画作中没有比它更高的遮挡物。
 *
 * 小艾想要计算出所有可能的摆放方式，以满足上述可见性条件。
 * 由于结果可能会非常庞大，需要将最终的答案对 10^9 + 7 进行取模后输出。
 *
 * 约束条件：
 * 时间限制：3s
 * 数据范围为 1 ≤ k ≤ n≤ 3000
 */
public class LookTheScenery {

    public static int solution(int n, int k) {
        int mod = 1000000007;
//        long[][] dp = new long[n + 1][k + 1];
//        dp[0][0] = 1;
        long[] dp = new long[k + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= k; j++) {
//                //dp[i - 1][j - 1]  第i幅画放到最后
//                //dp[i - 1][j] * (i - 1)  第1,2,...(i-1)幅画放到最后 ,前面是i-1数量的画作j可见,
//                //不用考虑x幅画怎么放, 肯定是最后, 因为放到其他位置会出现其他情况的重复
//                // dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j] * (i - 1)) % mod;
//                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j] * (i - 1)) % mod;
//            }
            for (int j = k; j > 0; j--) {
                dp[j] = (dp[j - 1] + dp[j] * (i - 1)) % mod;
            }
            dp[0] = 0;
        }
        return (int) dp[k];
    }

    public static int solution1(int n, int k) { //超时了 ,可以通过费马小定理获取分母的值, 但肯定还是慢
        int mod = 1000000007;
        //[x][i]  c(i-1, x)
        long[][] dp = new long[n + 1][k + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            long multi = 1;
            for (int j = i; j > 0; j--) {
                //(j-1)  i (i-j) 前面为C(i-1, j-1) 后面为(i-j)!=>(i-1)!/(j-1)!
                for (int a = j; a < i; a++) {
                    multi = (multi * a) % mod;
                }
                for (int c = 1; c <= k; c++) {
                    if (dp[j - 1][c - 1] > 0) {
                        dp[i][c] = (dp[i][c] + dp[j - 1][c - 1] * multi) % mod; //放到j+1的位置, 前面有c个,则后面顺序无所谓
                    }
                }
                multi = (multi * (j - 1)) % mod;
            }
        }
        return (int) dp[n][k];
    }

    public static void main(String[] args) {
        System.out.println(solution(4, 2) == 11);
        System.out.println(solution(6, 3) == 225);
        System.out.println(solution(7, 4) == 735);
        System.out.println(solution(9, 5) == 22449);
        System.out.println(solution(2238, 125));//188168091
    }
}
