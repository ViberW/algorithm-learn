package com.welph.leecode.part_500_1000.part_541_560;

/**
 * 给定一个字符串 s ，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * <p>
 * 示例 1：
 * 输入：s = "Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 * <p>
 * 示例 2:
 * 输入： s = "God Ding"
 * 输出："doG gniD"
 * <p>
 * 提示：
 * 1 <= s.length <= 5 * 10^4
 * s 包含可打印的 ASCII 字符。
 * s 不包含任何开头或结尾空格。
 * s 里 至少 有一个词。
 * s 中的所有单词都用一个空格隔开。
 */
public class Solution557 {

    public static void main(String[] args) {
        System.out.println(reverseWords("God Ding"));
        System.out.println(reverseWords("Let's take LeetCode contest"));
    }

    public static String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int l = 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == ' ') {
                reverse(chars, l, i - 1);
                l = i + 1;
            }
        }
        reverse(chars, l, chars.length - 1);
        return String.valueOf(chars);
    }

    private static void reverse(char[] chars, int l, int r) {
        char tmp;
        while (l < r) {
            tmp = chars[l];
            chars[l] = chars[r];
            chars[r] = tmp;
            l++;
            r--;
        }
    }
}
