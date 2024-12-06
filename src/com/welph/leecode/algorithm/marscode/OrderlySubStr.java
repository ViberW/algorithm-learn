package com.welph.leecode.algorithm.marscode;

import java.util.Arrays;

/**
 * 小U有一个字符串 s，他想计算该字符串的所有不同非空子序列的个数。
 * 子序列是通过删除原字符串中的部分字符（也可以不删除），且保持剩余字符的相对顺序形成的新字符串。
 *
 * 你的任务是帮助小U计算 s 的不同非空子序列的总数，并返回对 10^9 + 7 取余的结果。
 *
 * 例如：当 s = "abc" 时，所有不同的非空子序列包括 "a", "b", "c", "ab", "ac", "bc", 和 "abc"，总数为 7。
 */
public class OrderlySubStr {

    public static int solution(String s) {
        int mod = 1000000007;
        // 到达i时, total = total + total +1 - (上一次i出现前时的总数)
        //解读, 不以i为结尾: total. 以i为结尾: total, +1为当前i一个, 减去到(历史i的前一个时的数量)
        long[] counts = new long[26];
        Arrays.fill(counts, -1);
        long total = 0;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            long preCount = counts[index]; //如果之前存在, 则下面的1不需要加. 否则需要
            counts[index] = total;
            total = (2 * total + (preCount < 0 ? 1 : -preCount)) % mod;
        }
        return (int) total;
    }

    public static void main(String[] args) {
        System.out.println(solution("babb"));
        System.out.println(solution("abc") == 7);
        System.out.println(solution("aaa") == 3);
        System.out.println(solution("abcd") == 15);
        System.out.println(solution("abac") == 13);
        System.out.println(solution("babbccab")); //108
    }
}
