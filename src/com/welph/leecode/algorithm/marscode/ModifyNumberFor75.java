package com.welph.leecode.algorithm.marscode;

import java.util.Arrays;

/**
 * 小C拿到了一个正整数，她准备恰好修改其中 k 位，使得该正整数变为 75 的倍数。
 *
 * 你需要帮助她求出有多少种可能的修改方案。修改后的数仍然是一个正整数，
 *
 * 且不允许出现前导零。结果请对 10^9+7 取模。
 */
public class ModifyNumberFor75 {

    public static int solution(String s, int k) {
        int mod = 1000000007;
        //由于75等于5*5*3
        //1. 为了满足3的倍数, 需要各个位相加mod3=0
        //2. 由于5*5 结尾一定是00 或 25 或 50 或 75 这三个值 余值分别为0, 1, 2, 0
        if (s.length() < 2) {
            return 0;
        }
        long[][] dp = new long[k + 1][3]; //截止到当前处,使用操作i次,结尾余数的个数
        int c0 = s.charAt(s.length() - 1) - '0';
        int c1 = s.charAt(s.length() - 2) - '0';
        for (int i = s.length() == 2 ? 75 : 0; i < 100; i += 25) {
            boolean b1 = (i / 10) == c1;
            boolean b0 = (i % 10) == c0;
            if (b1 && b0) {
                dp[0][i % 3]++;
            } else if (b1 || b0) {
                if (k > 0) {
                    dp[1][i % 3]++;
                }
            } else {
                if (k > 1) {
                    dp[2][i % 3]++;
                }
            }
        }

        boolean firstZero = s.length() > 2 && s.charAt(0) == '0'; //考虑字符串首位为0
        //第一个字符时. 不能选择0; 所以只有3种, 接下来都是4种. 下面复位为4
        int[] range = {4, 3, 3};
        for (int i = 0; i < s.length() - 2; i++) {
            int curr = (s.charAt(i) - '0') % 3;
            range[curr]--;
            if (i == 0 && s.charAt(i) != '0') {
                range[0]--; //首位不能变为0
            }
            for (int j = Math.min(k, i + 3); j >= 0; j--) {
                //不改变
                if (!firstZero) {
                    long way0 = dp[j][0];
                    long way1 = dp[j][1];
                    long way2 = dp[j][2];
                    dp[j][curr] = way0;
                    dp[j][(curr + 1) % 3] = way1;
                    dp[j][(curr + 2) % 3] = way2;
                } else {
                    dp[j][0] = dp[j][1] = dp[j][2] = 0; //第一位必须要变化, 则这里需要设置位0
                }
                //改变
                if (j > 0) {
                    for (int h = 0; h < 3; h++) {
                        dp[j][h] = (dp[j][h] + range[h] * dp[j - 1][0]
                                + range[(h + 2) % 3] * dp[j - 1][1]
                                + range[(h + 1) % 3] * dp[j - 1][2]) % mod;
                    }
                }
            }
            range[0] = 4;
            range[1] = range[2] = 3;
            firstZero = false;
        }
        return (int) dp[k][0]; //恰好K次修改
    }

    public static void main(String[] args) {
        System.out.println(solution("75", 1));
        System.out.println(solution("3554", 2) == 7);
        System.out.println(solution("6005", 1) == 2);
        System.out.println(solution("911154", 3) == 187);
        System.out.println(solution("454444534544544", 6)); //6039402
        System.out.println(solution("44455333", 3)); //73
        System.out.println(solution("05000660560050", 8)); //635400329
        System.out.println(solution("45", 2));
    }

}
