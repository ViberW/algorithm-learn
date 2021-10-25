package com.welph.leecode.part_1_500.part_201_220;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。
 * <p>
 * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * * 同一个单元格内的字母在一个单词中不允许被重复使用。
 * <p>
 * 示例 1：
 * 输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]],
 * words = ["oath","pea","eat","rain"]
 * 输出：["eat","oath"]
 * <p>
 * 示例 2：
 * 输入：board = [["a","b"],["c","d"]], words = ["abcb"]
 * 输出：[]
 * <p>
 * 提示：
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] 是一个小写英文字母
 * 1 <= words.length <= 3 * 104
 * 1 <= words[i].length <= 10
 * words[i] 由小写英文字母组成
 * words 中的所有字符串互不相同
 */
public class Solution212 {

    public static void main(String[] args) {
        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = {"oath", "pea", "eat", "rain"};
        System.out.println(findWords(board, words));
    }

    /**
     * 类同{@link com.welph.leecode.part_1_500.part_61_80.Solution79} 回溯+字典树.
     * 因为worlds中的字符串各不相同. 用字典树来保存.true or false ?
     */
    public static List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        //words 构建字典树
        TrieNode root = buildTrie(words);

        int len = board.length;
        int length = board[0].length;
        boolean[][] exists = new boolean[len][length];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < length; j++) {
                find(result, board, i, j, len, length, exists, root);
            }
        }
        return result;
    }

    //o a b n
    //o t a e
    //a h k r
    //a f i v
    private static void find(List<String> result, char[][] board,
                             int i, int j, int len, int length,
                             boolean[][] exists, TrieNode... tries) {
        if (null == tries || tries.length == 0) {
            return;
        }
        if (i < 0 || j < 0 || i >= len || j >= length) {
            return;
        }
        if (exists[i][j]) {
            return;
        }
        exists[i][j] = true;
        //看tries的子中是否存在对应的数据结点
        int c = board[i][j] - 'a';
        List<TrieNode> nodes = new ArrayList<>();
        for (TrieNode t : tries) {
            TrieNode child = t.children[c];
            if (child != null) {
                if (child.isEndingChar && !child.exit) {
                    result.add(child.str);
                    child.exit = true;
                }
                nodes.add(child);
            }
        }
        TrieNode[] trieNodes = nodes.toArray(new TrieNode[nodes.size()]);
        find(result, board, i + 1, j, len, length, exists, trieNodes);
        find(result, board, i - 1, j, len, length, exists, trieNodes);
        find(result, board, i, j + 1, len, length, exists, trieNodes);
        find(result, board, i, j - 1, len, length, exists, trieNodes);
        exists[i][j] = false;
    }

    public static TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode p = root;
            for (int i = 0; i < word.length(); i++) {
                int c = word.charAt(i) - 'a';
                if (p.children[c] == null) {
                    p.children[c] = new TrieNode();
                }
                p = p.children[c];
            }
            p.isEndingChar = true;
            p.str = word;
        }
        return root;
    }

    //Trie树
    static class TrieNode {
        public TrieNode[] children = new TrieNode[26];
        public boolean isEndingChar = false;
        public String str;
        public boolean exit;
    }
}
