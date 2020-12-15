package com.welph.leecode.part_121_140;

import com.welph.leecode.algorithm.string.common.Trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * <p>
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 * 注意你可以重复使用字典中的单词。
 * <p>
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 */
public class Solution139 {

    public static void main(String[] args) {
        Solution139 solution139 = new Solution139();
        List<String> wordDict = new ArrayList<>(Arrays.asList("cats", "dog", "sand", "and", "cat"));
        System.out.println(solution139.wordBreak("catsandog", wordDict));
        wordDict = new ArrayList<>(Arrays.asList("leet", "code"));
        System.out.println(solution139.wordBreak("leetcode", wordDict));
        wordDict = new ArrayList<>(Arrays.asList("apple", "pen"));
        System.out.println(solution139.wordBreak("applepenapple", wordDict));
    }

    /**
     * 这种题目瞬间想到了动态规划了.
     * dp[0][i] => dp[0][x]&dp[x+1][i]  此时dp[x+1][i] 仅能够满足一个单词
     * //使用字典树帮助快速匹配
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        char[] chars = s.toCharArray();
        int length = chars.length;

        Trie root = new Trie('/');
        int minLen = Integer.MAX_VALUE; //假设计算出了最小移动距离
        int maxLen = 0;
        for (String word : wordDict) {
            int i = insetTrie(root, word);
            minLen = Math.min(i, minLen);
            maxLen = Math.max(i, maxLen);
        }

        boolean[][] dp = new boolean[length][length + 1]; //左闭右开
        dp[0][0] = true;
        for (int i = 0; i < length; i++) {
            for (int j = minLen; j <= maxLen; j++) {
                if (i + 1 >= j && dp[0][i - j + 1]
                        && findTrie(root, chars, i - j + 1, i + 1)) {
                    dp[0][i + 1] = true;
                }
            }
        }
        return dp[0][length];
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
        return length;
    }

    public boolean findTrie(Trie root, char[] chars, int start, int end) {
        Trie p = root;
        for (int i = start; i < end; ++i) {
            int index = chars[i] - 'a';
            if (p.children[index] == null) {
                return false;
            }
            p = p.children[index];
        }
        return p.isEndingChar;
    }

    class Trie {
        public char data;
        public Trie[] children = new Trie[26];
        public boolean isEndingChar = false; //是否为叶子节点

        public Trie(char data) {
            this.data = data;
        }
    }
}
