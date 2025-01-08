package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小Y有一个数字串，她希望通过分隔这个字符串来获得一些子串，每个子串代表一个数字。
 * 她的目标是最大化能获得的是 3 的倍数的数字的数量。
 * 分隔后的数字串不能包含前导零（但数字 0 本身是允许的），因为 0 也被视为 3 的倍数。
 *
 * 例如，对于数字串 1123，可以将其分割为 [1, 12, 3]，
 * 其中 12 和 3 是 3 的倍数，因此小Y最多可以获得 2 个是 3 的倍数的数字。
 */
public class MaxThreePowers {

    public static int solution(String n) {
        //比较明确的问题, 动态规划
        //一个整数能被 3 整除的充要条件是 它的各位数字之和能被 3 整除
        //abc => 100a+10b+c => 99a+9b+(a+b+c)
        int[] preSum = new int[n.length() + 1];
        int[] dp = new int[n.length() + 1];
        int max = 0;
        for (int i = 1; i <= n.length(); i++) {
            preSum[i] = preSum[i - 1] + (n.charAt(i - 1) - '0');
            dp[i] = max; //因为下面不一定会走到判断中, 所以需要调整i当前的最大值
            for (int j = i - 1; j >= 0; j--) {
                if ((preSum[i] - preSum[j]) % 3 == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(solution("1123") == 2);
        System.out.println(solution("300") == 3);
        System.out.println(solution("987654321") == 6);
        System.out.println(solution("1133211222111332"));//7
    }
}
