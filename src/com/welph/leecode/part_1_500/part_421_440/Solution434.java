package com.welph.leecode.part_1_500.part_421_440;

/**
 * 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
 * 请注意，你可以假定字符串里不包括任何不可打印的字符。
 * <p>
 * 示例:
 * 输入: "Hello, my name is John"
 * 输出: 5
 * 解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。
 */
public class Solution434 {

    public static void main(String[] args) {
        System.out.println(countSegments("Hello, my name is John"));
    }

    public static int countSegments(String s) {
        int ret = 0;
        char[] chars = s.toCharArray();
        char c;
        boolean word = false;
        for (int i = 0; i < chars.length; i++) {
            c = chars[i];
            if (c == ' ') {
                word = false;
            } else {
                if (!word) {
                    ret++;
                }
                word = true;
            }
        }
        return ret;
    }

    // 简洁的写法
    public int countSegments2(String s) {
        int segmentCount = 0;

        for (int i = 0; i < s.length(); i++) {
            if ((i == 0 || s.charAt(i - 1) == ' ') && s.charAt(i) != ' ') {
                segmentCount++;
            }
        }

        return segmentCount;
    }
}
