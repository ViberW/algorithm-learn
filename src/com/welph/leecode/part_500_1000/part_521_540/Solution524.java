package com.welph.leecode.part_500_1000.part_521_540;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 给你一个字符串 s 和一个字符串数组 dictionary ，找出并返回 dictionary 中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
 * 如果答案不止一个，返回长度最长且字母序最小的字符串。如果答案不存在，则返回空字符串。
 * <p>
 * 示例 1：
 * 输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
 * 输出："apple"
 * <p>
 * 示例 2：
 * 输入：s = "abpcplea", dictionary = ["a","b","c"]
 * 输出："a"
 * <p>
 * 提示：
 * 1 <= s.length <= 1000
 * 1 <= dictionary.length <= 1000
 * 1 <= dictionary[i].length <= 1000
 * s 和 dictionary[i] 仅由小写英文字母组成
 */
public class Solution524 {

    public static void main(String[] args) {
        System.out.println(findLongestWord("abpcplea", Arrays.asList("ale", "apple", "monkey", "plea")));
        System.out.println(findLongestWord("abpcplea", Arrays.asList("a", "b", "c")));
    }

    public static String findLongestWord(String s, List<String> dictionary) {
        dictionary.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int i = o2.length() - o1.length();
                return i == 0 ? o1.compareTo(o2) : i;
            }
        });
        for (String s1 : dictionary) {
            if (isSubStr(s1, s)) {
                return s1;
            }
        }
        return "";//如果答案不存在，则返回空字符串
    }

    public static boolean isSubStr(String str1, String str2) {
        if (str1.length() > str2.length()) {
            return false;
        }
        int i1 = 0;
        int i2 = 0;
        while (i1 < str1.length() && i2 < str2.length()) {
            if (str1.charAt(i1) == str2.charAt(i2)) {
                i1++;
            }
            i2++;
        }
        return i1 == str1.length();
    }
}
