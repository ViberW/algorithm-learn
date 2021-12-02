package com.welph.leecode.part_1_500.part_441_460;

/**
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。
 * 给定的字符串只含有小写英文字母，并且长度不超过10000。
 * <p>
 * 示例 1:
 * 输入: "abab"
 * 输出: True
 * 解释: 可由子字符串 "ab" 重复两次构成。
 * <p>
 * 示例 2:
 * 输入: "aba"
 * 输出: False
 * <p>
 * 示例 3:
 * 输入: "abcabcabcabc"
 * 输出: True
 * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 */
public class Solution459 {

    public static void main(String[] args) {
        System.out.println(repeatedSubstringPattern("abab"));
    }

    /**
     * 找到最开始和最结尾的字符串, 遍历找出合适的字符串
     * ------找到合适的位置, 从某个位置开始
     * todo 就离谱 看了题解
     */
    public static boolean repeatedSubstringPattern(String s) {
        String str = s + s;
        return str.substring(1, str.length() - 1).contains(s);
    }
}
