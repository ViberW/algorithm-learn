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
        // 这道题考虑的画图, 假定选择的某处切分, 那原本的长01子串被分割到左右,
        // 但是注意了, 如果下一次再进行翻转, 这个子串又有机会合并, 但不会超过原本.

        //所以最大值为每个起点的最值. 和左右两端(值不一样)时的长度
        int result = 1;
        int curr = 1;
        int first = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                curr++;
                result = Math.max(result, curr);
            } else {
                if (first == 0) {
                    first = curr;
                }
                curr = 1;
            }
        }
        if (s.charAt(0) == s.charAt(s.length() - 1)) {
            return result;
        }
        return result == s.length() ? result : Math.max(result, first + curr);
    }

    public static void main(String[] args) {
        System.out.println(solution("10010") == 5);
        System.out.println(solution("011010") == 4);
        System.out.println(solution("1010101") == 7);
        System.out.println(solution("11001100") == 2);
        System.out.println(solution("111"));
    }

}
