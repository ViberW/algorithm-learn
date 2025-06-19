package com.welph.leecode.part_500_1000.part_661_680;

/**
 * 设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 互不相同 。
 * 如果给出一个单词，请判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于你构建的字典中。
 *
 * 实现 MagicDictionary 类：
 * MagicDictionary() 初始化对象
 * void buildDict(String[] dictionary) 使用字符串数组 dictionary 设定该数据结构，dictionary 中的字符串互不相同
 * bool search(String searchWord) 给定一个字符串 searchWord ，判定能否只将字符串中 一个 字母换成另一个字母，
 * 使得所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 true ；否则，返回 false 。
 *
 * 示例：
 * 输入
 * ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
 * [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
 * 输出
 * [null, null, false, true, false, false]
 *
 * 解释
 * MagicDictionary magicDictionary = new MagicDictionary();
 * magicDictionary.buildDict(["hello", "leetcode"]);
 * magicDictionary.search("hello"); // 返回 False
 * magicDictionary.search("hhllo"); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
 * magicDictionary.search("hell"); // 返回 False
 * magicDictionary.search("leetcoded"); // 返回 False
 *
 * 提示：
 * 1 <= dictionary.length <= 100
 * 1 <= dictionary[i].length <= 100
 * dictionary[i] 仅由小写英文字母组成
 * dictionary 中的所有字符串 互不相同
 * 1 <= searchWord.length <= 100
 * searchWord 仅由小写英文字母组成
 * buildDict 仅在 search 之前调用一次
 * 最多调用 100 次 search
 */
public class Solution676 {

    public static void main(String[] args) {
        MagicDictionary magicDictionary = new MagicDictionary();
        magicDictionary.buildDict(new String[]{"hello", "leetcode"});
        magicDictionary.search("hello"); // 返回 False
        magicDictionary.search("hhllo"); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
        magicDictionary.search("hell"); // 返回 False
        magicDictionary.search("leetcoded"); // 返回 False
    }


    static class MagicDictionary {
        TrieNode root;

        public MagicDictionary() {
            root = new TrieNode();
        }

        public void buildDict(String[] dictionary) {
            for (String dic : dictionary) {
                TrieNode curr = root;
                for (int i = 0; i < dic.length(); i++) {
                    int index = dic.charAt(i) - 'a';
                    if (curr.children[index] == null) {
                        curr.children[index] = new TrieNode();
                    }
                    curr = curr.children[index];
                }
                curr.isEndingChar = true;
            }
        }

        public boolean search(String searchWord) {
            return search(searchWord, root, 0, false);
        }

        public boolean search(String searchWord, TrieNode root, int i, boolean vague) {
            if (i == searchWord.length()) {
                return root.isEndingChar && vague;
            }
            int index = searchWord.charAt(i) - 'a';
            if (root.children[index] != null) {
                if (search(searchWord, root.children[index], i + 1, vague)) {
                    return true;
                }
            }
            if (!vague) { //未修改过
                for (int j = 0; j < 26; j++) {
                    if (j != index && root.children[j] != null) { //进行模糊操作
                        if (search(searchWord, root.children[j], i + 1, true)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        public static class TrieNode {
            public TrieNode[] children = new TrieNode[26];
            public boolean isEndingChar = false;
        }
    }
}
