package com.welph.leecode.part_1_500.part_201_220;

/**
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
 * 实现词典类 WordDictionary ：
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；
 * 否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
 * <p>
 * 示例：
 * 输入：
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * 输出：
 * [null,null,null,null,false,true,true,true]
 * <p>
 * 解释：
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 * <p>
 * 提示：
 * 1 <= word.length <= 500
 * addWord 中的 word 由小写英文字母组成
 * search 中的 word 由 '.' 或小写英文字母组成
 * 最调用多 50000 次 addWord 和 search
 */
public class Solution211 {

    public static void main(String[] args) {
        // Your WordDictionary object will be instantiated and called as such:
        WordDictionary obj = new WordDictionary();
        obj.addWord("bad");
        obj.addWord("dad");
        obj.addWord("mad");
        System.out.println(obj.search("pad"));
        System.out.println(obj.search("bad"));
        System.out.println(obj.search(".ad"));
        System.out.println(obj.search("b.."));
        System.out.println(obj.search("word"));
    }


    static class WordDictionary {

        public static class TrieNode {
            public char data;
            public TrieNode[] children = new TrieNode[26];
            public boolean isEndingChar = false;

            public TrieNode(char data) {
                this.data = data;
            }
        }

        TrieNode root;

        public WordDictionary() {
            root = new TrieNode('/');
        }

        public void addWord(String word) {
            TrieNode p = root;
            for (int i = 0; i < word.length(); i++) {
                int c = word.charAt(i) - 'a';
                if (p.children[c] == null) {
                    p.children[c] = new TrieNode(word.charAt(i));
                }
                p = p.children[c];
            }
            p.isEndingChar = true;
        }

        public boolean search(String word) {
            return search(word, 0, root);
        }

        boolean search(String word, int i, TrieNode p) {
            if (i == word.length()) {
                return p.isEndingChar;
            }
            char c = word.charAt(i);
            if (c == '.') {
                for (TrieNode child : p.children) {
                    if (child == null)
                        continue;
                    if (search(word, i + 1, child)) {
                        return true;
                    }
                }
                return false;
            } else {
                TrieNode child = p.children[c - 'a'];
                if (null == child) {
                    return false;
                }
                return search(word, i + 1, child);
            }
        }
    }
}
