package com.welph.leecode.part_201_220;

/**
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 * <p>
 * 示例:
 * Trie trie = new Trie();
 * <p>
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 */
public class Solution208 {

    public static void main(String[] args) {
        //Your Trie object will be instantiated and called as such:
        Trie obj = new Trie();
        obj.insert("word");
        System.out.println(obj.search("wordk"));
        System.out.println(obj.startsWith("word"));
    }

    //字典树
    static class Trie {

        public class TrieNode {
            public char data;
            public TrieNode[] children = new TrieNode[26];
            public boolean isEndingChar = false;

            public TrieNode(char data) {
                this.data = data;
            }
        }

        TrieNode root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            root = new TrieNode('/');
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            int length = word.length();
            TrieNode p = root;
            for (int i = 0; i < length; i++) {
                int c = word.charAt(i) - 'a';
                if (p.children[c] == null) {
                    p.children[c] = new TrieNode(word.charAt(i));
                }
                p = p.children[c];
            }
            p.isEndingChar = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            return search(word, true);
        }

        private boolean search(String word, boolean isEndingChar) {
            int length = word.length();
            TrieNode p = root;
            for (int i = 0; i < length; i++) {
                TrieNode child = p.children[word.charAt(i) - 'a'];
                if (child == null) {
                    return false;
                }
                p = child;
            }
            return !isEndingChar || p.isEndingChar;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            return search(prefix, false);
        }
    }

}
