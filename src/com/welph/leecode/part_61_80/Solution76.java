package com.welph.leecode.part_61_80;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 给你一个字符串 S、一个字符串 T,请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 * <p>
 * 示例：
 * <p>
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 * <p>
 * 如果 S 中不存这样的子串,则返回空字符串 ""。
 * 如果 S 中存在这样的子串,我们保证它是唯一的答案。
 */
public class Solution76 {

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }

    public static String minWindow(String s, String t) {
        //最小字串 说明不考虑t的重复字段
        Map<Character, Integer> characters = new HashMap<>();
        Integer in;
        char c;
        for (int i = 0; i < t.length(); i++) {
            c = t.charAt(i);
            in = characters.get(c);
            if (null == in) {
                characters.put(c, 1);
            } else {
                characters.put(c, in + 1);
            }
        }
        Map<Character, Integer> currentSize = new HashMap<>();
        LinkedList<Integer> stack = new LinkedList<>();
        int size = 0;
        int l = 0;
        int r = 0;
        Integer current;
        int index;
        int minLen = s.length() + 1;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            Integer integer = characters.get(c);
            if (null == integer) {
                continue;
            }
            stack.add(i);
            in = currentSize.get(c);
            if (in == null) {
                in = 0;
            }
            in++;
            if (integer.equals(in)) {
                size++;
            }
            currentSize.put(c, in);
            if (size == characters.size()) {
                do {
                    current = stack.pollFirst();
                    if (current == null) {
                        break;
                    }
                    if (i - current + 1 <= minLen) {
                        minLen = i - current + 1;
                        l = current;
                        r = i + 1;
                    }
                    c = s.charAt(current);
                    index = currentSize.get(c);
                    index--;
                    currentSize.put(c, index);
                    if (index < characters.get(c)) {
                        size--;
                        break;
                    }
                } while (current <= i);
            }
        }
        return s.substring(l, r);
    }
}
