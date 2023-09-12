package com.welph.leecode.part_1_500.part_121_140;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

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

        // 保存i与j的必须改变一次的对应关系
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
        // 广度优先
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
                // 说明有数据信息存在对应了.
                for (Integer nextIndex : integers) {
                    if (cost[nextIndex] == 0 || cost[nextIndex] == lastSize) {
                        cost[nextIndex] = lastSize;
                        // 保存到当前节点的值
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

    /* 官方题解 能够帮助优化{@link Solution126} */

    // 广度有限+优化建图
    Map<String, Integer> wordId = new HashMap<String, Integer>();
    List<List<Integer>> edge = new ArrayList<List<Integer>>();
    int nodeNum = 0;

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        for (String word : wordList) {
            addEdge(word);
        }
        addEdge(beginWord);
        if (!wordId.containsKey(endWord)) {
            return 0;
        }
        int[] dis = new int[nodeNum];//到每个id的距离
        Arrays.fill(dis, Integer.MAX_VALUE);
        int beginId = wordId.get(beginWord), endId = wordId.get(endWord);
        dis[beginId] = 0;

        Queue<Integer> que = new LinkedList<Integer>();
        que.offer(beginId);
        while (!que.isEmpty()) {
            int x = que.poll();
            //"hit"          -> "hot"          -> "dot"          -> "dog"          -> "cog"
            //"hit" -> "h*t" -> "hot" -> "*ot" -> "dot" -> "do*" -> "dog" -> "*og" -> "cog"
            if (x == endId) {
                return dis[endId] / 2 + 1;
            }
            for (int it : edge.get(x)) {
                if (dis[it] == Integer.MAX_VALUE) {
                    dis[it] = dis[x] + 1;
                    que.offer(it);
                }
            }
        }
        return 0;
    }

    /*
     * 用*代替 有点想马拉车算法的逻辑
     */
    public void addEdge(String word) {
        addWord(word);
        int id1 = wordId.get(word);
        char[] array = word.toCharArray();
        int length = array.length;
        for (int i = 0; i < length; ++i) {
            char tmp = array[i];
            array[i] = '*';  //好方法 通过用*替换 能够通过*去帮助快速判断是否相等!!! 
            String newWord = new String(array);
            addWord(newWord);// *bc a*c ab*
            int id2 = wordId.get(newWord);
            // 构建双向指针
            edge.get(id1).add(id2);
            edge.get(id2).add(id1);
            array[i] = tmp;
        }
    }

    // 标记每个单词在edge的位置
    public void addWord(String word) {
        if (!wordId.containsKey(word)) {
            wordId.put(word, nodeNum++);
            edge.add(new ArrayList<Integer>());
        }
    }

    // 双向广度优先搜索 --减少广度搜索的空间使用,对上面的优化

    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        for (String word : wordList) {
            addEdge(word);
        }
        addEdge(beginWord);
        if (!wordId.containsKey(endWord)) {
            return 0;
        }

        int[] disBegin = new int[nodeNum];
        Arrays.fill(disBegin, Integer.MAX_VALUE);
        int beginId = wordId.get(beginWord);
        disBegin[beginId] = 0;
        Queue<Integer> queBegin = new LinkedList<Integer>();
        queBegin.offer(beginId);//从开始出发
        
        int[] disEnd = new int[nodeNum];
        Arrays.fill(disEnd, Integer.MAX_VALUE);
        int endId = wordId.get(endWord);
        disEnd[endId] = 0;
        Queue<Integer> queEnd = new LinkedList<Integer>();
        queEnd.offer(endId);//从结束出发

        while (!queBegin.isEmpty() && !queEnd.isEmpty()) {
            int queBeginSize = queBegin.size();
            for (int i = 0; i < queBeginSize; ++i) {
                int nodeBegin = queBegin.poll();
                if (disEnd[nodeBegin] != Integer.MAX_VALUE) {//是否已经end判断过了
                    return (disBegin[nodeBegin] + disEnd[nodeBegin]) / 2 + 1;
                }
                for (int it : edge.get(nodeBegin)) {
                    if (disBegin[it] == Integer.MAX_VALUE) {
                        disBegin[it] = disBegin[nodeBegin] + 1;
                        queBegin.offer(it);
                    }
                }
            }

            int queEndSize = queEnd.size();
            for (int i = 0; i < queEndSize; ++i) {
                int nodeEnd = queEnd.poll();
                if (disBegin[nodeEnd] != Integer.MAX_VALUE) {//是否已经begin判断过了
                    return (disBegin[nodeEnd] + disEnd[nodeEnd]) / 2 + 1;
                }
                for (int it : edge.get(nodeEnd)) {
                    if (disEnd[it] == Integer.MAX_VALUE) {
                        disEnd[it] = disEnd[nodeEnd] + 1;
                        queEnd.offer(it);
                    }
                }
            }
        }
        return 0;
    }

}
