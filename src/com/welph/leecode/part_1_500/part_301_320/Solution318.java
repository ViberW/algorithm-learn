package com.welph.leecode.part_1_500.part_301_320;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串数组words，找到length(word[i]) * length(word[j])的最大值，
 * 并且这两个单词不含有公共字母。你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。
 * <p>
 * 示例1:
 * 输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
 * 输出: 16
 * 解释: 这两个单词为 "abcw", "xtfn"。
 * <p>
 * 示例 2:
 * 输入: ["a","ab","abc","d","cd","bcd","abcd"]
 * 输出: 4
 * 解释: 这两个单词为 "ab", "cd"。
 * <p>
 * 示例 3:
 * 输入: ["a","aa","aaa","aaaa"]
 * 输出: 0
 * 解释: 不存在这样的两个单词。
 */
public class Solution318 {

    public static void main(String[] args) {
        String[] words = { "abcw", "baz", "foo", "bar", "xtfn", "abcdef" };
        System.out.println(maxProduct(words));
    }

    /**
     * 判断是否存在相同的字母, 26bit位
     */
    public static int maxProduct(String[] words) {
        int len = words.length;
        int max = 0;
        int[] v = new int[len];
        for (int i = 0; i < len; i++) {
            v[i] = buildBit(words[i]);
        }
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if ((v[i] & v[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }

    private static int buildBit(String word) {
        int bit = 0;
        for (int i = 0; i < word.length(); i++) {
            bit |= (1 << (word.charAt(i) - 'A'));
        }
        return bit;
    }

    /* 官方题解 */
    // 位运算优化: 主要是meet和met 这种中间有重复字符的相同位掩码, 只需要用哈希表记录最大长度的相同位掩码
    public int maxProduct2(String[] words) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int length = words.length;
        for (int i = 0; i < length; i++) {
            int mask = 0;
            String word = words[i];
            int wordLength = word.length();
            for (int j = 0; j < wordLength; j++) {
                mask |= 1 << (word.charAt(j) - 'a');
            }
            if (wordLength > map.getOrDefault(mask, 0)) { // 保留最长即可
                map.put(mask, wordLength);
            }
        }
        int maxProd = 0;
        Set<Integer> maskSet = map.keySet();
        for (int mask1 : maskSet) {
            int wordLength1 = map.get(mask1);
            for (int mask2 : maskSet) {// 但其实这里还可以优化, 应该从mask1的后面开始, 可以用list代替set, 按照index缩小范围
                if ((mask1 & mask2) == 0) {
                    int wordLength2 = map.get(mask2);
                    maxProd = Math.max(maxProd, wordLength1 * wordLength2);
                }
            }
        }
        return maxProd;
    }

}
