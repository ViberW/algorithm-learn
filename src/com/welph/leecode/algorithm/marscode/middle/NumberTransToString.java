package com.welph.leecode.algorithm.marscode.middle;

/**
 * 小M获得了一个任务，需要将数字翻译成字符串。翻译规则是：0对应"a"，1对应"b"，依此类推直到25对应"z"。
 * 一个数字可能有多种翻译方法。小M需要一个程序来计算一个数字有多少种不同的翻译方法。
 *
 * 例如：数字12258可以翻译成 "bccfi", "bwfi", "bczi", "mcfi" 和 "mzi"，共5种方式。
 */
public class NumberTransToString {

    public static int solution(int num) {
        String s = String.valueOf(num);
        int length = s.length();
        int[] dp = new int[length + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= length; i++) {
            dp[i] = dp[i - 1];
            if (s.charAt(i - 2) == '1') {
                dp[i] += dp[i - 2];
            } else if (s.charAt(i - 2) == '2' && s.charAt(i - 1) < '6') {
                dp[i] += dp[i - 2];
            }
        }
        return dp[length];
    }

    public static void main(String[] args) {
        // You can add more test cases here
        System.out.println(solution(12258) == 5);
        System.out.println(solution(1400112) == 6);
        System.out.println(solution(2110101) == 10);
    }
}
