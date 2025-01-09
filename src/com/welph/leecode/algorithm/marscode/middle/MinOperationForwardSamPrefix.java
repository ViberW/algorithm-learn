package com.welph.leecode.algorithm.marscode.middle;

/**
 * 小U和小R有两个字符串，分别是S和T，现在小U需要通过对 S进行若干次操作，使其变成T的一个前缀。
 * 操作可以是修改S的某一个字符，或者删除S末尾的字符。
 *
 * 现在你需要帮助小U计算出，最少需要多少次操作才能让S变成T的前缀。
 */
public class MinOperationForwardSamPrefix {

    public static int solution(String S, String T) {
        int size = Math.min(S.length(), T.length());
        int result = S.length() - size;
        for (int i = 0; i < size; i++) {
            if (S.charAt(i) != T.charAt(i)) {
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution("aba", "abb") == 1);
        System.out.println(solution("abcd", "efg") == 4);
        System.out.println(solution("xyz", "xy") == 1);
        System.out.println(solution("hello", "helloworld") == 0);
        System.out.println(solution("same", "same") == 0);
    }
}
