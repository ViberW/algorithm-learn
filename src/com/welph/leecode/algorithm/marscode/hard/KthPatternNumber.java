package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小U有一个特殊的“模板串”，这个串由数字字符和 '?' 组成。她可以通过将 '?' 替换成数字字符，来构造出多个正整数。
 * 不过有一个重要的限制条件：匹配出来的正整数不能有前导零。
 *
 * 现在，小U想知道，按照字典序排列后，第 k 小的匹配数是多少？如果没有满足条件的第 k 小数，则返回 -1。
 */
public class KthPatternNumber {

    public static String solution(String s, int k) {
        //考虑不考虑先导0, 则如果第一位为? 则默认从0开始 且第一位不为? 也不能为0
        if (s.charAt(0) == '0') {
            return "-1";
        }
        k--;
        StringBuilder builder = new StringBuilder(s);
        for (int i = s.length() - 1; i >= 0; i--) {
            if (builder.charAt(i) == '?') {
                builder.setCharAt(i, (char) ((k % 10) + '0'));
                k /= 10;
            }
        }
        if (k != 0) {
            return "-1";
        }
        if (s.charAt(0) == '?') {
            if (builder.charAt(0) == '9') {
                return "-1";
            }
            builder.setCharAt(0, (char) (builder.charAt(0) + 1));
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(solution("??1", 1).equals("101"));
        System.out.println(solution("2??", 3).equals("202"));
        System.out.println(solution("000???", 1).equals("-1"));
        System.out.println(solution("93", 6).equals("-1"));
        System.out.println(solution("145050?600", 11).equals("-1"));
    }
}
