package com.welph.leecode.part_1_500.part_341_360;

import java.util.HashSet;
import java.util.Set;

/**
 * 写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 * <p>
 * 示例 1：
 * 输入："hello"
 * 输出："holle"
 * <p>
 * 示例 2：
 * 输入："leetcode"
 * 输出："leotcede"
 * <p>
 * 提示：
 * 元音字母不包含字母 "y" 。
 */
public class Solution345 {

    public static void main(String[] args) {
        System.out.println(reverseVowels("hello"));
    }

    static Set<Character> characters = new HashSet<>();

    static {
        String tool = "aoeiuAOEIU";
        for (int i = 0; i < tool.length(); i++) {
            characters.add(tool.charAt(i));
        }
    }

    //这些属于元音字母: aoeiuAOEIU
    public static String reverseVowels(String s) {
        int l = 0;
        char[] chars = s.toCharArray();
        int r = chars.length - 1;
        char tmp;
        while (l < r) {
            while (!characters.contains(chars[l]) && l < r) {
                l++;
            }
            while (!characters.contains(chars[r]) && l < r) {
                r--;
            }
            if (l < r) {
                tmp = chars[l];
                chars[l] = chars[r];
                chars[r] = tmp;
                l++;
                r--;
            }
        }
        return new String(chars);
    }
}
