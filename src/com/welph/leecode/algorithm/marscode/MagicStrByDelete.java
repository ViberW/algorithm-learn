package com.welph.leecode.algorithm.marscode;

import java.util.Arrays;

/**
 * 小M有一个字符串，他希望通过删除最少的字符将其转化为一个“神奇字符串”。所谓“神奇字符串”满足以下两个条件：
 *
 * 它的长度是3的倍数。
 * 对于任意的下标i，其中i是3的倍数（即i = 3x），都必须满足S[i] = S[i+1] = S[i+2]。
 * 例如，给定字符串aabca，我们可以通过删除两个字符，将其变为aaa，这是一个符合条件的“神奇字符串”。
 */
public class MagicStrByDelete {

    public static int solution(String S) {
        //使用DP 并且用26位数组记录每个字符的情况
        int n = S.length();
        int[] pre = new int[n];
        int[] chars = new int[26];
        Arrays.fill(chars, -1);
        for (int i = 0; i < n; i++) {
            int v = S.charAt(i) - 'a';
            if (chars[v] == -1) {
                pre[i] = i;
            } else {
                pre[i] = chars[v];
            }
            chars[v] = i;
        }
        int[] dp = new int[n + 1];// 表示到达i,删除最少量的
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + 1; //默认删除
            //尝试往前找一组数值
            int j = i - 1;
            int count = 1;
            while (count < 3 && j >= 0 && j != pre[j]) {
                j = pre[j];
                count++;
            }
            if (count == 3) {
                dp[i] = Math.min(dp[j] + i - j - 3, dp[i]);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(solution("aabca") == 2);
        System.out.println(solution("abcdef") == 6);
        System.out.println(solution("aaabbbccc") == 0);
    }
}
