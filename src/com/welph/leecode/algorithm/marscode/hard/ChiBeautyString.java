package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小M对由字符"c", "h", "i"组成的字符串感兴趣。她定义了字符串的美丽度为该字符串中包含的"chi"子序列的数量。
 * 注意，子序列可以不连续。例如，字符串 "cchhi" 包含4个"chi"子序列。
 *
 * 此外，小M还定义了一个字符串的权值为其所有连续子串的美丽度之和。
 * 例如，字符串 "chii" 的权值为2，因为它包含两个"chi"子序列。
 *
 * 小M想知道长度为n的由"c", "h", "i"构成的所有字符串的权值之和。答案请对 10^9 + 7 取模。
 */
public class ChiBeautyString {

    public static int solution(int n) {
        int mod = 1000000007;
        //标记当前由多少组C  H
        long result = 0;
        for (int i = 0; i < n; i++) {
            //设置为i
            //设置为h
            //设置为c
        }
        System.out.println(result);
        return (int) result;
    }

    public static void main(String[] args) {
        System.out.println(solution(3) == 1);
        System.out.println(solution(4) == 8);
        System.out.println(solution(5) == 40);
    }
}
