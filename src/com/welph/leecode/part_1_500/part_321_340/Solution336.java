package com.welph.leecode.part_1_500.part_321_340;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一组 互不相同 的单词， 找出所有 不同 的索引对 (i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
 * <p>
 * 示例 1：
 * 输入：words = ["abcd","dcba","lls","s","sssll"]
 * 输出：[[0,1],[1,0],[3,2],[2,4]]
 * 解释：可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
 * <p>
 * 示例 2：
 * 输入：words = ["bat","tab","cat"]
 * 输出：[[0,1],[1,0]]
 * 解释：可拼接成的回文串为 ["battab","tabbat"]
 * <p>
 * 示例 3：
 * 输入：words = ["a",""]
 * 输出：[[0,1],[1,0]]
 * <p>
 * 提示：
 * 1 <= words.length <= 5000
 * 0 <= words[i].length <= 300
 * words[i] 由小写英文字母组成
 */
public class Solution336 {

    public static void main(String[] args) {
        System.out.println(palindromePairs(new String[]{"abcd", "dcba", "lls", "s", "sssll"}));
        System.out.println(palindromePairs(new String[]{"bat", "tab", "cat"}));
        System.out.println(palindromePairs(new String[]{"a", ""}));

        //[[3,0],[1,3],[4,0],[2,4],[5,0],[0,5]]
        System.out.println(palindromePairs(new String[]{"a", "b", "c", "ab", "ac", "aa"}));
    }

    // 还有种方法, 效率更低:  不使用字典树, 计算每个hash. 对字符的所需hash计算, hash相等就比较字符是否合法

    /**
     * {@link com.welph.leecode.part_1_500.part_201_220.Solution214}
     * 一个单词 1. 若是本身能够有机会成为回文串的主体   2. 作为附属主体
     * //使用马拉车算法 对每个单词做处理 30.6%时间以及5.1%的空间..  //todo 抽时间对时间和空间优化下
     */
    public static List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        int len = words.length;

        //构建一个字典树,用于检查.
        Trie trie = new Trie(len);
        String word;
        for (int i = 0; i < len; i++) {
            word = words[i];
            trie.insert(word, i);
        }

        for (int i = 0; i < len; i++) {
            word = words[i];
            if (word.length() != 0) {
                init(res, words[i], i, trie);
            }
        }
        return res;
    }

    /**
     * 马拉车算法  将当前的.  ---这里仅仅考虑了右边的临界值;
     */
    private static void init(List<List<Integer>> res, String word, int offset, Trie trie) {
        String str = initStr(word);
        int length = str.length();
        int[] count = new int[length];
        int PR = -1;
        int index = 0;  //最大PR的中心点
        boolean existAll = false;
        for (int i = 0; i < length; i++) {
            count[i] = PR > i ? Math.min(PR - i, count[2 * index - i]) : 1;
            while (i + count[i] < length && i - count[i] > -1) {
                if (str.charAt(i + count[i]) == str.charAt(i - count[i])) {
                    count[i]++;
                } else {
                    break;
                }
            }
            if (i + count[i] > PR) {
                PR = i + count[i];
                index = i;
            }
            if (i + 1 == count[i]) { //最右边  --需要屏蔽掉#带来的误差.
                int left = (i + count[i]) / 2;
                Integer match = trie.match(word, left, word.length());
                if (null != match && offset != match) {
                    res.add(new ArrayList<Integer>(4) {{
                        add(match);
                        add(offset);
                    }});
                    //若是left ==0 说明右边全匹配. 将不考虑左全匹配
                    if (left == 0) {
                        existAll = true;
                    }
                }
            }
            if (i + count[i] == length) { //z最左边
                int right = (i - count[i] + 1) / 2;
                if (existAll && right == word.length()) {
                    continue;
                }
                Integer match = trie.match(word, 0, right);
                if (null != match && offset != match) {
                    res.add(new ArrayList<Integer>(4) {{
                        add(offset);
                        add(match);
                    }});
                }
            }
            //需要想办法处理下 左右度能够匹配的数据.
        }
    }

    private static String initStr(String s) {
        StringBuilder sb = new StringBuilder("#");
        char[] ss = s.toCharArray();
        for (char aChar : ss) {
            sb.append(aChar).append("#");
        }
        return sb.toString();
    }

    static class Trie { //构建字典树 --链式向量星
        int cnt = 1;
        int[][] next;
        Map<Integer, Integer> set;

        public Trie(int len) {
            set = new HashMap<>(len);
            next = new int[len + 2][26];
        }

        public void insert(String s, int index) {
            if (s.length() == 0) {
                set.put(0, index);
                return;
            }
            int cur = 1;
            int c;
            for (int i = 0; i < s.length(); i++) {
                if (cur >= next.length) {
                    expand();
                }
                c = s.charAt(i) - 'a';
                if (next[cur][c] == 0) {
                    next[cur][c] = ++cnt;
                }
                cur = next[cur][c];
            }
            set.put(cur, index);
        }

        private void expand() {
            int newLength = (int) (1.5 * next.length);
            int[][] tmp = new int[newLength][26];
            System.arraycopy(next, 0, tmp, 0, next.length);
            next = tmp;
        }

        public Integer match(String word, int left, int length) {
            if (left == length) {
                return set.get(0);
            }
            int cur = 1;
            int c;
            for (int j = length - 1; j >= left; j--) {
                if (cur >= next.length) {
                    return null;
                }
                c = word.charAt(j) - 'a';
                if (next[cur][c] == 0) {
                    return null;
                }
                cur = next[cur][c];
            }
            return set.get(cur);
        }
    }
}
