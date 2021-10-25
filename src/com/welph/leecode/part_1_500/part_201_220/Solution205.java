package com.welph.leecode.part_1_500.part_201_220;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
 * <p>
 * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。
 * 不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
 * <p>
 * 示例 1:
 * 输入：s = "egg", t = "add"
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：s = "foo", t = "bar"
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：s = "paper", t = "title"
 * 输出：true
 * <p>
 * 提示：
 * 可以假设 s 和 t 长度相同。
 */
public class Solution205 {
    public static void main(String[] args) {
    /*    System.out.println(isIsomorphic("egg", "add"));
        System.out.println(isIsomorphic("foo", "bar"));
        System.out.println(isIsomorphic("paper", "title"));*/
        System.out.println(isIsomorphic("ab", "aa"));
    }

    public static boolean isIsomorphic(String s, String t) {
        int length = s.length();
        Map<Character, Character> map = new HashMap<>();
        Map<Character, Character> rmap = new HashMap<>();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            char c1 = t.charAt(i);
            if (!map.computeIfAbsent(c, ch -> c1).equals(c1)) {
                return false;
            }
            if (!rmap.computeIfAbsent(c1, ch -> c).equals(c)) {
                return false;
            }
        }
        return true;
    }
}
