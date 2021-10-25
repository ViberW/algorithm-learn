package com.welph.leecode.part_1_500.part_41_60;

/**
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
 * <p>
 * 如果不存在最后一个单词，请返回 0 。
 * <p>
 * 说明：一个单词是指由字母组成，但不包含任何空格的字符串。
 * <p>
 * 示例:
 * <p>
 * 输入: "Hello World"
 * 输出: 5
 */
public class Solution58 {

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("Hello World"));
    }

    public static int lengthOfLastWord(String s) {
        char[] chars = s.toCharArray();
        int total = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (' ' == chars[i]) {
                if (total > 0)
                    break;
                continue;
            }
            total++;
        }
        return total;
    }
}
