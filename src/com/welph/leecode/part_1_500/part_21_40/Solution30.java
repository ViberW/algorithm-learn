package com.welph.leecode.part_1_500.part_21_40;

import java.util.*;

/**
 * 给定一个字符串s和一些长度相同的单词words。找出 s 中恰好可以由words 中所有单词串联形成的子串的起始位置。
 * 注意子串要与words 中的单词完全匹配，中间不能有其他字符，但不需要考虑words中单词串联的顺序。
 * <p>
 * 示例 1：
 * 输入：
 * s = "barfoothefoobarman",
 * words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoor" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 示例 2：
 * <p>
 * 输入：
 * s = "wordgoodgoodgoodbestword",
 * words = ["word","good","best","word"]
 * 输出：[]
 */
public class Solution30 {

    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = new String[]{"foo", "bar"};
        System.out.println(findSubstring(s, words));
    }

    /**
     * 官方题解, 每个批次进行滑动
     */
    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>();
        int m = words.length, n = words[0].length(), ls = s.length();
        for (int i = 0; i < n; i++) {//每种代表着不同的(0~n)的开头有数据保留
            if (i + m * n > ls) {
                break;
            }
            Map<String, Integer> differ = new HashMap<String, Integer>();
            for (int j = 0; j < m; j++) {
                String word = s.substring(i + j * n, i + (j + 1) * n);
                differ.put(word, differ.getOrDefault(word, 0) + 1);
            }
            for (String word : words) {//当前开始的differ初始化
                differ.put(word, differ.getOrDefault(word, 0) - 1);
                if (differ.get(word) == 0) {
                    differ.remove(word);
                }
            }
            for (int start = i; start < ls - m * n + 1; start += n) {//开始不断向后滑
                if (start != i) {//跳过第一次
                    String word = s.substring(start + (m - 1) * n, start + m * n);//添加一个右边的新单词
                    differ.put(word, differ.getOrDefault(word, 0) + 1);
                    if (differ.get(word) == 0) {
                        differ.remove(word);
                    }
                    word = s.substring(start - n, start);//减去一个左边的老单词
                    differ.put(word, differ.getOrDefault(word, 0) - 1);
                    if (differ.get(word) == 0) {
                        differ.remove(word);
                    }
                }
                if (differ.isEmpty()) {//differ为空则说明匹配了
                    res.add(start);
                }
            }
        }
        return res;
    }

    /**
     * 在处理这个题目时，，又多次的超出时间限制，一开始思路一致，只是没有过滤掉不必要的便利。
     * 之后不断改进
     * 执行用时 : 27ms。 内存消耗 45.6MB
     * 整体类似滑动
     */
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> list = new ArrayList<>();
        if (null == s || s.isEmpty() || null == words
                || words.length == 0 || words[0].length() == 0) {
            return list;
        }
        int count = words.length;
        int len = words[0].length();
        int total = count * len;
        char[] chars = s.toCharArray();
        int length = chars.length;
        Map<String, Integer> indexs = new HashMap<>();
        Integer ind;
        for (String word : words) {
            ind = indexs.get(word);
            if (null != ind) {
                indexs.put(word, ind + 1);
                continue;
            }
            indexs.put(word, 1);
        }

        String str;
        String[] arr = new String[length];
        int[] in = new int[len];
        for (int i = 0; i < len; i++) {
            in[i] = length;
        }
        for (int i = length - len; i >= 0; i--) {
            str = s.substring(i, i + len);
            if (indexs.containsKey(str)) {
                arr[i] = str;
                if (in[i % len] - i >= total) {
                    int i1 = containAll(indexs, arr, i, count, len);
                    if (i1 < 0) {
                        list.add(i);
                    } else {
                        in[i % len] = i1;
                    }
                }
            } else {
                in[i % len] = i;
            }
        }
        return list;
    }

    private static int containAll(Map<String, Integer> indexs, String[] arr, int i, int count, int len) {
        Map<String, Integer> it = new HashMap<>(indexs);
        Integer v;
        String s;
        while (count-- > 0) {
            s = arr[i];
            v = it.get(s) - 1;
            if (v < 0) {
                return i;
            }
            it.put(s, v);
            i += len;
        }
        return -1;
    }

}
