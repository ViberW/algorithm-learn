package com.welph.leecode.part_1_500.part_421_440;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。
 * 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。
 * <p>
 * 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。
 * 与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。
 * <p>
 * 现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，
 * 请找出能够使起始基因序列变化为目标基因序列所需的最少变化次数。如果无法实现目标变化，请返回 -1。
 * 注意：
 * .    起始基因序列默认是合法的，但是它并不一定会出现在基因库中。
 * .    如果一个起始基因序列需要多次变化，那么它每一次变化之后的基因序列都必须是合法的。
 * .    假定起始基因序列与目标基因序列是不一样的。
 * <p>
 * 示例 1：
 * start: "AACCGGTT"
 * end:   "AACCGGTA"
 * bank: ["AACCGGTA"]
 * 返回值: 1
 * <p>
 * 示例 2：
 * start: "AACCGGTT"
 * end:   "AAACGGTA"
 * bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
 * 返回值: 2
 * <p>
 * 示例 3：
 * start: "AAAAACCC"
 * end:   "AACCCCCC"
 * bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
 * 返回值: 3
 */
public class Solution433 {

    public static void main(String[] args) {
        /*System.out.println(minMutation("AACCGGTT", "AAACGGTA",
                new String[]{"AACCGGTA", "AACCGCTA", "AAACGGTA"}));*/
        System.out.println(minMutation("AACCGGTT", "AACCGGTA",
                new String[]{"AACCGGTA"}));
    }

    /**
     * {@link com.welph.leecode.part_1_500.part_121_140.Solution127}
     * -----------------
     * 和Solution127问题很类似
     */
    public static int minMutation(String start, String end,
                                  String[] bank) {
        if (bank.length == 0) {
            return -1;
        }
        int index = find(bank, end);
        if (index == -1) {
            return -1;
        }
        int startIndex = find(bank, start);
        if (startIndex == -1) {
            String[] newBank = new String[bank.length + 1];
            System.arraycopy(bank, 0, newBank, 0, bank.length);
            newBank[bank.length] = start;
            startIndex = bank.length;
            bank = newBank;
        }
        int length = bank.length;
        List<Integer>[] convert = new List[length];
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (canConvert(bank, i, j, 8)) {
                    fillData(convert, i, j);
                }
            }
        }
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(startIndex);
        boolean[] visited = new boolean[length];
        int ret = 0;
        List<Integer> nears;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Integer pop = queue.pop();
                if (pop == index) {
                    return ret;
                } else if ((nears = convert[pop]) != null) {
                    for (Integer near : nears) {
                        if (!visited[near]) {
                            visited[near] = true;
                            queue.add(near);
                        }
                    }
                }
            }
            ret++;
        }
        return -1;
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

    public static boolean canConvert(String[] bank, int i, int j, int length) {
        String s = bank[i];
        String s1 = bank[j];
        int change = 0;
        for (int i1 = 0; i1 < length; i1++) {
            if (s.charAt(i1) != s1.charAt(i1)) {
                change++;
            }
        }
        return change == 1;
    }

    private static int find(String[] bank, String end) {
        int index = -1;
        for (int i = 0; i < bank.length; i++) {
            if (end.equals(bank[i])) {
                index = i;
            }
        }
        return index;
    }
}
