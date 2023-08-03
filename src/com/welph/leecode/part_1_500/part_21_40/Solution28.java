package com.welph.leecode.part_1_500.part_21_40;

/**
 * 实现strStr()函数。
 * <p>
 * 给定一个haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回 -1。
 * <p>
 * 示例 1:
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 * 当needle是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当needle是空字符串时我们应当返回 0 。这与C语言的strstr()以及 Java的indexOf()定义相符。
 */
public class Solution28 {

    public static void main(String[] args) {
        System.out.println(strStr("hello", "ll"));
    }

    public static int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }
        char[] chars = haystack.toCharArray();
        int size = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == needle.charAt(size)) {
                size++;
                if (size == needle.length()) {
                    return i - size + 1;
                }
            } else {
                i = i - size;
                size = 0;
            }
        }
        return -1;
    }

}
