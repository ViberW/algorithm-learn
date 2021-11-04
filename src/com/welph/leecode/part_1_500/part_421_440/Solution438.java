package com.welph.leecode.part_1_500.part_421_440;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 * <p>
 * 示例 1:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * <p>
 * 示例 2:
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 * <p>
 * 提示:
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 */
public class Solution438 {

    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc"));
        System.out.println(findAnagrams("abab", "ab"));
    }

    public static List<Integer> findAnagrams(String s, String p) {
        int length = p.length();
        if (length > s.length()) {
            return Collections.emptyList();
        }
        int[] arr = new int[26];
        char[] pChars = p.toCharArray();
        for (char c : pChars) {
            arr[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (arr[i] == 0) {
                arr[i] = -1;
            }
        }
        List<Integer> ret = new ArrayList<>();
        char[] sChars = s.toCharArray();
        int match = 0;
        int index;
        int val;
        for (int i = 0; i < sChars.length; i++) {
            index = sChars[i] - 'a';
            val = arr[index];
            if (val < 0) {
                if (match > 0) {
                    for (int j = i - 1; match > 0; j--, match--) {
                        arr[sChars[j] - 'a']++;
                    }
                }
            } else {
                arr[index] = --val;
                if (++match == length && val == 0) {
                    ret.add(i - match + 1);
                    arr[sChars[i - match + 1] - 'a']++;
                    match--;
                } else {
                    if (val < 0) {
                        int idx;
                        for (int j = i - match + 1; j <= i; j++) {
                            idx = sChars[j] - 'a';
                            arr[idx]++;
                            match--;
                            if (index == idx) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }
}
