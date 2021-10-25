package com.welph.leecode.part_1_500.part_121_140;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，
 * 在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 * 说明：
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * .[
 * .     "cats and dog",
 * .     "cat sand dog"
 * .]
 * <p>
 * 示例 2：
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * .[
 * .     "pine apple pen apple",
 * .     "pineapple pen apple",
 * .     "pine applepen apple"
 * .]
 * 解释: 注意你可以重复使用字典中的单词。
 * <p>
 * 示例 3：
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 */
public class Solution140 {

    public static void main(String[] args) {
        Solution140 solution140 = new Solution140();
        List<String> wordDict = new ArrayList<>(Arrays.asList("cat", "cats", "and", "sand", "dog"));
        System.out.println(solution140.wordBreak("catsanddog", wordDict));
        wordDict = new ArrayList<>(Arrays.asList("apple", "pen", "applepen", "pine", "pineapple"));
        System.out.println(solution140.wordBreak("pineapplepenapple", wordDict));
        wordDict = new ArrayList<>(Arrays.asList("cats", "dog", "sand", "and", "cat"));
        System.out.println(solution140.wordBreak("catsandog", wordDict));
    }

    /**
     * 根据{@link Solution139} 理解思路
     * 动态规划 dp[0][i] = dp[0][x] 与 dp[x+1][i] 凑手dp[x+1][i]应该是单个单词
     * <p>
     * --时间很快 92% 但内存6.9%...
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
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

        List<Integer>[][] dp = new List[length][length + 1]; //保存最后一次的分割的位置(分割线的后面的索引)
        dp[0][0] = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            for (int j = minLen; j <= maxLen; j++) {
                if (i + 1 >= j && dp[0][i - j + 1] != null
                        && findTrie(root, chars, i - j + 1, i + 1)) {
                    if (dp[0][i + 1] == null) {
                        dp[0][i + 1] = new ArrayList<>();
                    }
                    dp[0][i + 1].add(i - j + 1);
                }
            }
        }
        List<String> result = new ArrayList<>();
        LinkedList<Integer> indexs = new LinkedList<>();
        deepIndex(indexs, dp, length, s, result);
        return result;
    }

    //回溯算法构建结果集
    public void deepIndex(LinkedList<Integer> indexs, List<Integer>[][] dp, int index, String s, List<String> result) {
        if (index == 0) {
            StringBuilder stringBuilder = new StringBuilder(s);
            for (int i = indexs.size() - 1; i > 0; i--) {
                stringBuilder.insert(indexs.get(i), " ");
            }
            result.add(stringBuilder.toString());
            return;
        }
        List<Integer> list = dp[0][index];
        if (list == null) {
            return;
        }
        for (Integer i : list) {
            indexs.addFirst(i);
            deepIndex(indexs, dp, i, s, result);
            indexs.removeFirst();
        }
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
        public String word;

        public Trie(char data) {
            this.data = data;
        }
    }
}
