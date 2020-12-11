package com.welph.leecode.part_121_140;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，
 * 找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：
 * <p>
 * 每次转换只能改变一个字母。
 * 转换后得到的单词必须是字典中的单词。
 * 说明:
 * .   如果不存在这样的转换序列，返回一个空列表。
 * .   所有单词具有相同的长度。
 * .   所有单词只由小写字母组成。
 * .   字典中不存在重复的单词。
 * .   你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * <p>
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 * <p>
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * <p>
 * 输出:
 * [
 * .  ["hit","hot","dot","dog","cog"],
 * .  ["hit","hot","lot","log","cog"]
 * ]
 * 示例 2:
 * <p>
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * <p>
 * 输出: []
 * <p>
 * 解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
 */
public class Solution126 {

    public static void main(String[] args) {
        String beginWord = "red";
        String endWord = "tax";
        List<String> wordList = new ArrayList<>(Arrays.asList("ted", "tex", "red", "tax", "tad", "den", "rex", "pee"));

        System.out.println(findLadders(beginWord, endWord, wordList));
        System.out.println(findLadders2(beginWord, endWord, wordList));
    }

    /**
     * 通过, 但执行时间11.83%  内存消耗38.49%
     */
    public static List<List<String>> findLadders2(String beginWord, String endWord,
                                                  List<String> wordList) {

        List<List<String>> result = new ArrayList<>();
        int endIndex = wordList.indexOf(endWord);
        if (endIndex == -1) {
            return result;
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

        LinkedList<List<Integer>> expect = new LinkedList<>();
        expect.addLast(new ArrayList<Integer>() {{
            add(size - 1);
        }}); //beginWorld
        //广度优先
        List<Integer> integers;
        Integer lastIndex;
        int[] cost = new int[size];
        while (!expect.isEmpty()) {
            List<Integer> pop = expect.pop();
            lastIndex = pop.get(pop.size() - 1);
            if (lastIndex == endIndex) {
                List<String> objects = new ArrayList<>();
                for (Integer index : pop) {
                    objects.add(wordList.get(index));
                }
                result.add(objects);
            } else if ((integers = convert[lastIndex]) != null) {
                //说明有数据信息存在对应了.
                for (Integer nextIndex : integers) {
                    //若第一次到达或者达到的上一次相同,即可进去下一次的广度搜索
                    if (cost[nextIndex] == 0 || cost[nextIndex] == pop.size()) {
                        cost[nextIndex] = pop.size();
                        //保存到当前节点的值
                        expect.add(new ArrayList<Integer>() {{
                            addAll(pop);
                            add(nextIndex);
                        }});
                    }
                }
            }
        }
        return result;
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

    /**
     * todo   数据量大时超时了. ..... 考虑下^~^
     * 要求:
     * 每次转换只能改变一个字母。
     * 转换后得到的单词必须是字典中的单词。 --- 意味着endWord一定存在wordList
     * 条件:
     * 如果不存在这样的转换序列，返回一个空列表。
     * 所有单词具有相同的长度。
     * 所有单词只由小写字母组成。
     * 字典中不存在重复的单词。
     * <p>
     * // 广度优先,回溯算法
     */
    public static List<List<String>> findLadders(String beginWord, String endWord,
                                                 List<String> wordList) {
        int i = wordList.indexOf(endWord);
        if (i == -1) {
            return Collections.emptyList();
        }

        Map<Integer, Map<Character, List<Integer>>> map = build(wordList);
        Set<Integer> used = new HashSet<>();
        List<Integer> expectStr = new ArrayList<>();
        expectStr.add(-1);
        LinkedList<List<Integer>> result = new LinkedList<>();
        result.add(new ArrayList<Integer>() {{
            add(-1);
        }});
        calculate(expectStr, used, beginWord.length(), map, wordList, i, beginWord, result,
                IntStream.range(0, wordList.size()).boxed().collect(Collectors.toList()));
        return result.stream().map(integers -> {
            List<String> item = new ArrayList<>();
            for (Integer integer : integers) {
                if (integer == -1) {
                    item.add(beginWord);
                } else {
                    item.add(wordList.get(integer));
                }
            }
            return item;
        }).collect(Collectors.toList());
    }

    public static void calculate(List<Integer> expectStr, Set<Integer> used, int length,
                                 Map<Integer, Map<Character, List<Integer>>> map,
                                 List<String> wordList, int endIndex, String beginWord,
                                 LinkedList<List<Integer>> result,
                                 List<Integer> defaultList) {
        Set<Integer> index;
        Set<Integer> tempIndex;
        int j;
        List<Integer> integers = null;
        List<Integer> nextExpectStr = new ArrayList<>();
        String s;
        boolean containEnd = false;
        for (Integer ind : expectStr) {
            used.add(ind);
            List<Integer> pop = result.pop();
            s = ind == -1 ? beginWord : wordList.get(ind);
            for (int i = 0; i < length; i++) {
                index = null;
                for (j = 0; j < length && (integers = i == j ? defaultList : map.get(j).get(s.charAt(j))) != null; j++) {
                    tempIndex = new HashSet<>();
                    if (index == null) {
                        tempIndex.addAll(integers);
                    } else {
                        for (Integer integer : integers) {
                            if (index.contains(integer)) {
                                tempIndex.add(integer);
                            }
                        }
                    }
                    index = tempIndex;
                }
                if (j == length && index != null) {
                    for (Integer integer : index) {
                        if (!used.contains(integer) || endIndex == integer) {
                            if (endIndex == integer) {
                                containEnd = true;
                            }
                            result.addLast(new ArrayList<Integer>() {{
                                addAll(pop);
                                add(integer);
                            }});
                            nextExpectStr.add(integer);
                        }
                    }
                }
            }
        }
        if (!nextExpectStr.isEmpty()) {
            if (containEnd) {
                result.removeIf(is -> is.get(is.size() - 1) != endIndex);
            } else {
                calculate(nextExpectStr, used, length, map, wordList, endIndex, beginWord, result, defaultList);
            }
        }
    }

    public static Map<Integer, Map<Character, List<Integer>>> build(List<String> wordList) {
        Map<Integer, Map<Character, List<Integer>>> map = new HashMap<>();
        String s;
        for (int i = 0; i < wordList.size(); i++) {
            s = wordList.get(i);
            for (int j = 0; j < s.length(); j++) {
                map.computeIfAbsent(j, HashMap::new)
                        .computeIfAbsent(s.charAt(j), ArrayList::new)
                        .add(i);
            }
        }
        return map;
    }

}
