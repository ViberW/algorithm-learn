package com.welph.leecode.algorithm.marscode.middle;

import java.util.HashSet;
import java.util.Set;

/**
 * 小R有一个字符串 word，该字符串由数字和小写英文字母组成。小
 * R想用空格替换每一个不是数字的字符。然后，他希望统计在替换后剩下的整数中，不同整数的数目。
 *
 * 例如，给定字符串 "a123bc34d8ef34"，替换后形成的字符串是 " 123 34 8 34"，
 * 剩下的整数是 "123"、"34"、"8" 和 "34"。不同的整数有三个，即 "123"、"34" 和 "8"。
 *
 * 注意，只有当两个整数的不含前导零的十进制表示不同，才认为它们是不同的整数。
 */
public class CountAnotherWords {
    public static int solution(String word) {
        Set<String> set = new HashSet<>();
        int l = 0;
        int r = 0;
        boolean number = false;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c >= '0' && c <= '9') {
                if (c == '0' && l == r) {
                    l = r = i + 1;
                } else {
                    r++;
                }
                number = true;
            } else {
                if (number) {
                    set.add(word.substring(l, r));
                }
                number = false;
                l = r = i + 1;
            }
        }
        if (l != r) {
            set.add(word.substring(l, r));
        }
        return set.size();
    }

    public static void main(String[] args) {
        System.out.println(solution("a123bc34d8ef34") == 3);
        System.out.println(solution("t1234c23456") == 2);
        System.out.println(solution("a1b01c001d4") == 2);
        System.out.println(solution("zgw5f0k7rj4q4tsk4dos1u"));
    }
}
