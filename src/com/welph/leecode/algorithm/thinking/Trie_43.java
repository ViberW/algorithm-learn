package com.welph.leecode.algorithm.thinking;

/**
 * @author Viber
 * @version 1.0
 * @apiNote [字典树]
 * @see com.welph.leecode.algorithm.string.TrieDemo
 * @since 2021/8/3 17:52
 */
public class Trie_43 {

    public static void main(String[] args) {
        insert("hello");
        insert("hi");
        insert("haha");
        insert("hellotree");
        insert("hel");
        insert("hl");
        System.out.println(max);
        System.out.println(findPrefix("hl"));
        System.out.println(findPrefix("he"));
        System.out.println(findPrefix("hea"));
    }

    static int max = 1;
    static int cnt = 1;
    static int[][] next = new int[50][26];

    /**
     * 区别于TrieDemo的方式, 这里使用类似 [链式向量星] 方式构建字典树
     */
    public static void insert(String s) {
        int cur = 1;
        int c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i) - 'a';
            if (next[cur][c] == 0) {
                next[cur][c] = ++cnt;
            }
            cur = next[cur][c];//存储了下一个点将要存储的位置.
            max = Math.max(max, cur);
        }
    }

    public static boolean findPrefix(String prefix) {
        int cur = 1;
        int c;
        for (int i = 0; i < prefix.length(); i++) {
            c = prefix.charAt(i) - 'a';
            if (next[cur][c] == 0) {
                return false;
            }
            cur = next[cur][c];
        }
        return true;
    }
}
