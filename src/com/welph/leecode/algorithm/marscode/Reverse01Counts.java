package com.welph.leecode.algorithm.marscode;

/**
 * 小C拿到了一个长度为n的二进制串。他发现通过一些操作可以得到一个很有趣的效果。
 * 每次操作是将这个字符串按顺序分为两部分，分别将两部分各自翻转后再按原顺序拼接。
 * 小C想知道，通过进行任意次数的操作后，能够得到的最长的连续交替01子串的长度是多少。
 *
 * 例如：当前的二进制串是 01001，可以先将其分为 010 和 01 两部分，
 * 分别翻转得到 010 和 10，按原顺序拼接后得到 01010，
 * 此时最长的连续交替子串是 01010，长度为 5。
 */
public class Reverse01Counts {

    public static int solution(String s) {

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(solution("10010") == 5);
        System.out.println(solution("011010") == 4);
        System.out.println(solution("1010101") == 7);
        System.out.println(solution("11001100") == 2);
        System.out.println(solution("111") == 1);
    }

}
