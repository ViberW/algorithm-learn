package com.welph.leecode.part_1_500.part_461_480;


import java.util.*;

/**
 * 给你一个 不含重复 单词的字符串数组 words ，请你找出并返回 words 中的所有 连接词 。
 * 连接词 定义为：一个完全由给定数组中的至少两个较短单词组成的字符串。
 * <p>
 * 示例 1：
 * 输入：words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * 输出：["catsdogcats","dogcatsdog","ratcatdogcat"]
 * 解释："catsdogcats" 由 "cats", "dog" 和 "cats" 组成;
 * "dogcatsdog" 由 "dog", "cats" 和 "dog" 组成;
 * "ratcatdogcat" 由 "rat", "cat", "dog" 和 "cat" 组成。
 * <p>
 * 示例 2：
 * 输入：words = ["cat","dog","catdog"]
 * 输出：["catdog"]
 * <p>
 * 提示：
 * 1 <= words.length <= 10^4
 * 0 <= words[i].length <= 1000
 * words[i] 仅由小写字母组成
 * 0 <= sum(words[i].length) <= 10^5
 */
public class Solution472 {

    public static void main(String[] args) {
        System.out.println(new Solution472().findAllConcatenatedWordsInADict(new String[]{"cat", "dog", "catdog"}));
        System.out.println(new Solution472().findAllConcatenatedWordsInADict(new String[]{"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"}));
        System.out.println(new Solution472().findAllConcatenatedWordsInADict(new String[]{"cat", "cats", "dog", "dogcatsdog"}));
        System.out.println(new Solution472().findAllConcatenatedWordsInADict(new String[]{""}));
    }

    /**
     * {@link com.welph.leecode.part_1_500.part_121_140.Solution140}
     * {@link com.welph.leecode.part_1_500.part_121_140.Solution139}
     * -----------
     * 先根据长度排序, 再构建字典树, 判断当前是否是有组合  ----------通过了 但慢的雅痞
     */
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        if (words.length == 0) {
            return Collections.emptyList();
        }
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int min = words[0].length();
        Trie trie = new Trie('/');
        List<String> result = new ArrayList<>();
        int max = min;
        int last = min;
        for (String word : words) {
            int length = word.length();
            if (last != length) {
                max = last;
            }
            if (length != 0) {
                boolean[][] dp = new boolean[length][length + 1]; //左闭右开
                dp[0][0] = true;
                for (int i = 0; i < length; i++) {
                    for (int j = min; j <= max; j++) {
                        if (i + 1 >= j && dp[0][i - j + 1]
                                && findTrie(trie, word, i - j + 1, i + 1)) {
                            dp[0][i + 1] = true;
                        }
                    }
                }
                if (dp[0][length]) {
                    result.add(word);
                }
                insetTrie(trie, word);
            }
            last = length;
        }
        return result;
    }

    public int insetTrie(Trie root, String text) {
        Trie p = root;
        int length = text.length();
        for (int i = 0; i < text.length(); ++i) {
            int index = text.charAt(i) - 'a';
            if (p.children[index] == null) {
                Trie newNode = new Trie(text.charAt(i));
                p.children[index] = newNode;
            }
            p = p.children[index];
        }
        p.isEndingChar = true;
        p.word = text;
        return length;
    }

    public boolean findTrie(Trie root, String s, int start, int end) {
        Trie p = root;
        for (int i = start; i < end; ++i) {
            int index = s.charAt(i) - 'a';
            if (p.children[index] == null) {
                return false;
            }
            p = p.children[index];
        }
        return p.isEndingChar;
    }

    static class Trie {
        public char data;
        public Trie[] children = new Trie[26];
        public boolean isEndingChar = false; //是否为叶子节点
        public String word;

        public Trie(char data) {
            this.data = data;
        }
    }
}
