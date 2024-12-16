package com.welph.leecode.algorithm.marscode;

import java.util.HashSet;
import java.util.Set;

/**
 * 小G定义了一个字符串的权值为：字符串的长度乘以字符串中不同字母的种类数量。
 * 例如，对于字符串 "abacb"，其权值为 5 × 3 = 15，因为字符串的长度为 5，且包含 3 种不同的字母。
 *
 * 现在，小G有一个字符串，她希望将这个字符串切分成 k 个子串，并希望这些子串的最大权值尽可能小。
 * 你需要帮助小G找到最优的切分方案，使得这 k 个子串中的最大权值最小化。
 */
public class MinSplitWeight {

    public static int solution(String s, int k) {
        //K个子串的最大权值 是所有拆分结果种最小的
        int[][] dp = new int[s.length() + 1][k + 1];
        Set<Character> unique = new HashSet<>();
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                unique.add(s.charAt(j));
                int weight = unique.size() * (i - j);
                int lower = j == 0 ? 0 : 1;
                for (int c = Math.min(k, j + 1); c > lower; c--) {
                    dp[i][c] = Math.min(dp[i][c] == 0 ? Integer.MAX_VALUE : dp[i][c], Math.max(weight, dp[j][c - 1]));
                }
            }
            unique.clear();
        }
        return dp[s.length()][k] == 0 ? 1 : dp[s.length()][k];
    }

    public static void main(String[] args) {
        System.out.println(solution("ababbbb", 2) == 6);
        System.out.println(solution("abcabcabc", 3) == 9);
        System.out.println(solution("aaabbbcccddd", 4) == 3);
        System.out.println(solution("gqsuicqwbbxxlb", 15) == 1);
    }
}
