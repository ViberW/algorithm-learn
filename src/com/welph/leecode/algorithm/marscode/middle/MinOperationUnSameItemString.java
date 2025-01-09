package com.welph.leecode.algorithm.marscode.middle;

/**
 * 小U得到一个只包含小写字母的字符串 S。
 * 她可以执行如下操作：每次选择字符串中两个相同的字符删除，
 * 然后在字符串末尾添加一个任意的小写字母。
 *
 * 小U想知道，最少需要多少次操作才能使得字符串中的所有字母都不相同？
 */
public class MinOperationUnSameItemString {

    public static int solution(String S) {


        return 0;
    }

    public static void main(String[] args) {
        System.out.println(solution("abab") == 2);
        System.out.println(solution("aaaa") == 2);
        System.out.println(solution("abcabc") == 3);
    }
}
