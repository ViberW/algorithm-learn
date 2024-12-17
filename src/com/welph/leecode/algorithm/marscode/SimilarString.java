package com.welph.leecode.algorithm.marscode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 小U认为两个字符串相似，当且仅当它们每个字母的个数都相等。
 * 例如，"abcbd" 和 "dbcba" 是相似的，"abcd" 和 "abcd" 也是相似的；
 * 而 "abb" 和 "aab" 不相似，"ac" 和 "cca" 也不相似。
 *
 * 现在小U手中有 n 个字符串，她想知道有多少对字符串是相似的。
 */
public class SimilarString {

    public static int solution(int n, String[] strings) {
        //找出相似的字符串. 并计数
        Map<String, Integer> counts = new HashMap<>();
        for (String string : strings) {
            char[] charArray = string.toCharArray();
            Arrays.sort(charArray);
            String s = new String(charArray);
            counts.put(s, counts.getOrDefault(s, 0) + 1);
        }
        return counts.entrySet().stream().mapToInt(e -> {
            Integer value = e.getValue();
            if (value > 1) { //c(m,2)
                return value * (value - 1) / 2;
            }
            return 0;
        }).sum();
    }

    public static void main(String[] args) {
        System.out.println(solution(7, new String[]{"abcbd", "dbcba", "abcd", "abcd", "adbc", "aa", "aa"}) == 5);
        System.out.println(solution(3, new String[]{"aab", "bba", "baa"}) == 1);
        System.out.println(solution(5, new String[]{"abc", "def", "ghi", "jkl", "mno"}) == 0);
    }
}
