package com.welph.leecode.part_1_500.part_121_140;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * 示例 1:
 * <p>
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "race a car"
 * 输出: false
 */
public class Solution125 {

    public static void main(String[] args) {
        //System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        //System.out.println(isPalindrome("race a car"));
        System.out.println(isPalindrome(".;ab"));
    }

    /**
     * 双指针法
     *
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        int len = s.length();
        if (len == 0) {
            return true;
        }
        int l = 0;
        int r = len - 1;
        char[] chars = s.toCharArray();
        while (l < r) {
            while (l < r && !isNormal(chars[l])) {
                l++;
            }
            while (r > l && !isNormal(chars[r])) {
                r--;
            }
            if (lower(chars[r]) != lower(chars[l])) {
                return false;
            }
            r--;
            l++;
        }
        return true;
    }

    private static char lower(char c) {
        return c >= 65 && c <= 90 ? (char) (c + 32) : c;
    }

    private static boolean isNormal(char c) {
        return (c >= 48 && c <= 57)
                || (c >= 65 && c <= 90)
                || (c >= 97 && c <= 122);
    }
}
