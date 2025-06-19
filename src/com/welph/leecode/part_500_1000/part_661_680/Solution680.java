package com.welph.leecode.part_500_1000.part_661_680;

/**
 * 给你一个字符串 s，最多 可以从中删除一个字符。
 * 请你判断 s 是否能成为回文字符串：如果能，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：s = "aba"
 * 输出：true
 *
 * 示例 2：
 * 输入：s = "abca"
 * 输出：true
 * 解释：你可以删除字符 'c' 。
 *
 * 示例 3：
 * 输入：s = "abc"
 * 输出：false
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * s 由小写英文字母组成
 */
public class Solution680 {

    public static void main(String[] args) {
//        System.out.println(validPalindrome("aba"));
//        System.out.println(validPalindrome("abca"));
//        System.out.println(validPalindrome("abc"));
        System.out.println(validPalindrome("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));
    }

    //可以不删除 可以最多删除1个
    public static boolean validPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return validPalindrome(s, l, r - 1) || validPalindrome(s, l + 1, r);
            } else {
                l++;
                r--;
            }
        }
        return true;
    }

    public static boolean validPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
