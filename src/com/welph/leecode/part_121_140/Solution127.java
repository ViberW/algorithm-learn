package com.welph.leecode.part_121_140;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定两个单词（beginWord和 endWord）和一个字典，
 * 找到从beginWord 到endWord 的最短转换序列的长度。转换需遵循如下规则：
 * <p>
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 * <p>
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例1:
 * <p>
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * <p>
 * 输出: 5
 * <p>
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * 返回它的长度 5。
 * 示例 2:
 * <p>
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * <p>
 * 输出:0
 * 解释:endWord "cog" 不在字典中，所以无法进行转换
 */
public class Solution127 {
    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));

        System.out.println(ladderLength(beginWord, endWord, wordList));
    }

    /**
     * {@link Solution126} 解出了,这题就很好完成
     */
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int endIndex = wordList.indexOf(endWord);
        if (endIndex == -1) {
            return 0;
        }
        wordList.add(beginWord);
        int size = wordList.size();
        int length = beginWord.length();

        //保存i与j的必须改变一次的对应关系
        List<Integer>[] convert = new List[wordList.size()];
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (canConvert(wordList, i, j, length)) {
                    fillData(convert, i, j);
                }
            }
        }

        LinkedList<Integer> expect = new LinkedList<>();
        LinkedList<Integer> expectSize = new LinkedList<>();
        expect.addLast(size - 1);
        expectSize.addLast(1);
        //广度优先
        List<Integer> integers;
        Integer lastIndex;
        Integer lastSize;
        int[] cost = new int[size];
        while (!expect.isEmpty()) {
            lastIndex = expect.pop();
            lastSize = expectSize.pop();
            if (lastIndex == endIndex) {
                return lastSize;
            } else if ((integers = convert[lastIndex]) != null) {
                //说明有数据信息存在对应了.
                for (Integer nextIndex : integers) {
                    if (cost[nextIndex] == 0 || cost[nextIndex] == lastSize) {
                        cost[nextIndex] = lastSize;
                        //保存到当前节点的值
                        expect.add(nextIndex);
                        expectSize.add(lastSize + 1);
                    }
                }
            }
        }
        return 0;
    }

    public static void fillData(List<Integer>[] convert, int i, int j) {
        List<Integer> list = convert[i];
        if (list == null) {
            list = new ArrayList<>();
            convert[i] = list;
        }
        list.add(j);

        list = convert[j];
        if (list == null) {
            list = new ArrayList<>();
            convert[j] = list;
        }
        list.add(i);
    }

    public static boolean canConvert(List<String> wordList, int i, int j, int length) {
        String s = wordList.get(i);
        String s1 = wordList.get(j);
        int change = 0;
        for (int i1 = 0; i1 < length; i1++) {
            if (s.charAt(i1) != s1.charAt(i1)) {
                change++;
            }
        }
        return change == 1;
    }
}
