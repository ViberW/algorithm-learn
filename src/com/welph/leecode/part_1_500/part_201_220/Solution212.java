package com.welph.leecode.part_1_500.part_201_220;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。
 * <p>
 * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * * 同一个单元格内的字母在一个单词中不允许被重复使用。
 * <p>
 * 示例 1：
 * 输入：board =
 * [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]],
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
        char[][] board = { { 'o', 'a', 'a', 'n' }, { 'e', 't', 'a', 'e' }, { 'i', 'h', 'k', 'r' },
                { 'i', 'f', 'l', 'v' } };
        String[] words = { "oath", "pea", "eat", "rain" };
        System.out.println(findWords(board, words));
    }

    /**
     * 类同{@link com.welph.leecode.part_1_500.part_61_80.Solution79} 回溯+字典树.
     * 因为worlds中的字符串各不相同. 用字典树来保存.true or false ?
     */
    public static List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        // words 构建字典树
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

    // o a b n
    // o t a e
    // a h k r
    // a f i v
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
        // 看tries的子中是否存在对应的数据结点
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
        TrieNode[] trieNodes = nodes.toArray(new TrieNode[nodes.size()]); // 不如下面的方法, 本质上没必要保存的
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

    // Trie树
    static class TrieNode {
        public TrieNode[] children = new TrieNode[26];
        public boolean isEndingChar = false;
        public String str;
        public boolean exit;
    }

    /* 官方题解 */

    int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    // 回溯+字典树
    public List<String> findWords2(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        Set<String> ans = new HashSet<String>();
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                dfs(board, trie, i, j, ans);
            }
        }

        return new ArrayList<String>(ans);
    }

    public void dfs(char[][] board, Trie now, int i1, int j1, Set<String> ans) {
        if (!now.children.containsKey(board[i1][j1])) {
            return;
        }
        char ch = board[i1][j1];
        now = now.children.get(ch);
        if (!"".equals(now.word)) {
            ans.add(now.word);
        }

        board[i1][j1] = '#';// 代替我方法中的exists
        for (int[] dir : dirs) {
            int i2 = i1 + dir[0], j2 = j1 + dir[1];
            if (i2 >= 0 && i2 < board.length && j2 >= 0 && j2 < board[0].length) {
                dfs(board, now, i2, j2, ans);
            }
        }
        board[i1][j1] = ch;
    }

    class Trie {
        String word;
        Map<Character, Trie> children;
        boolean isWord;

        public Trie() {
            this.word = "";
            this.children = new HashMap<Character, Trie>();
        }

        public void insert(String word) {
            Trie cur = this;
            for (int i = 0; i < word.length(); ++i) {
                char c = word.charAt(i);
                if (!cur.children.containsKey(c)) {
                    cur.children.put(c, new Trie());
                }
                cur = cur.children.get(c);
            }
            cur.word = word;
        }

    }

    // 删除被匹配的单词
    // 假设给定一个所有单元格都是 a 的二维字符网格和单词列表 ["a", "aa", "aaa", "aaaa"] ,用上面方法会有大量的重复遍历
    // 所以在匹配的时候 将单词从前缀树中删除, 避免重复寻找
    public List<String> findWords3(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        Set<String> ans = new HashSet<String>();
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                dfs3(board, trie, i, j, ans);
            }
        }
        return new ArrayList<String>(ans);
    }

    public void dfs3(char[][] board, Trie now, int i1, int j1, Set<String> ans) {
        if (!now.children.containsKey(board[i1][j1])) {
            return;
        }
        char ch = board[i1][j1];
        Trie nxt = now.children.get(ch);
        if (!"".equals(nxt.word)) {
            ans.add(nxt.word);
            nxt.word = "";
        }

        if (!nxt.children.isEmpty()) {
            board[i1][j1] = '#';
            for (int[] dir : dirs) {
                int i2 = i1 + dir[0], j2 = j1 + dir[1];
                if (i2 >= 0 && i2 < board.length && j2 >= 0 && j2 < board[0].length) {
                    dfs(board, nxt, i2, j2, ans);
                }
            }
            board[i1][j1] = ch;
        }

        // 字典树的结尾, 代表了一个单词, 此时删除单词
        if (nxt.children.isEmpty()) {
            now.children.remove(ch);
        }
    }

}
