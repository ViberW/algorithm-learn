package com.welph.leecode.part_500_1000.part_501_520;

/**
 * 我们定义，在以下情况时，单词的大写用法是正确的：
 * <p>
 * 全部字母都是大写，比如 "USA" 。
 * 单词中所有字母都不是大写，比如 "leetcode" 。
 * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google" 。
 * 给你一个字符串 word 。如果大写用法正确，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * 输入：word = "USA"
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：word = "FlaG"
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * 1 <= word.length <= 100
 * word 由小写和大写英文字母组成
 */
public class Solution520 {

    public static void main(String[] args) {
        System.out.println(detectCapitalUse("USA"));
        System.out.println(detectCapitalUse("FlaG"));
        System.out.println(detectCapitalUse("g"));
    }

    public static boolean detectCapitalUse(String word) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) <= 'Z') {
                count++;
            }
        }
        return count == 0 || count == word.length()
                || (count == 1 && word.charAt(0) <= 'Z');
    }
}
