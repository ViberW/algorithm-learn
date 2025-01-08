package com.welph.leecode.algorithm.marscode.hard;

import java.util.*;

/**
 * 小C拿到了一个空集。她准备进行以下操作：将 [l,r] 区间的每个整数添加进集合。
 *
 * 每次操作后，输出当前集合的 mex。定义集合的 mex 为：集合中最小的未出现的非负整数。
 */
public class MexMinNatureNumber {

    public static int[] solution(int q, int[][] queries) {
        TreeMap<Integer, Integer> tree = new TreeMap<>();
        tree.put(0, 0);
        int[] result = new int[q];
        for (int i = 0; i < q; i++) {
            int[] query = queries[i];
            Map.Entry<Integer, Integer> entry0 = tree.floorEntry(query[0]);
            Map.Entry<Integer, Integer> entry1 = tree.ceilingEntry(query[1]);

            boolean leftSide = null != entry0 && entry0.getValue() >= query[0];
            boolean rightSide = null != entry1 && entry1.getKey() <= query[1] + 1;
            if (!leftSide && !rightSide) {
                tree.put(query[0], query[1] + 1);
            } else {
                int left;
                int right;
                if (leftSide && rightSide) {
                    left = entry0.getKey();
                    right = entry1.getValue();
                } else if (leftSide) {
                    left = entry0.getKey();
                    //[8,17] 然后再来个[0,11] 所以需要找到这个范围内的最大值
                    right = Math.max(query[1] + 1, tree.floorEntry(query[1]).getValue());
                } else {
                    left = query[0];
                    right = entry1.getValue();
                }
                NavigableMap<Integer, Integer> subMap = tree.subMap(left, true, right, false);
                ArrayList<Integer> keys = new ArrayList<>(subMap.keySet());
                for (Integer k : keys) {
                    tree.remove(k);
                }
                tree.put(left, right);
            }

            result[i] = tree.get(0);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.equals(solution(4, new int[][]{{1, 3}, {7, 8}, {0, 5}, {3, 6}}), new int[]{0, 0, 6, 9}));
        System.out.println(Arrays.equals(solution(3, new int[][]{{0, 2}, {3, 4}, {6, 10}}), new int[]{3, 5, 5}));
        System.out.println(Arrays.equals(solution(2, new int[][]{{2, 5}, {7, 9}}), new int[]{0, 0}));
        System.out.println(Arrays.toString(solution(4, new int[][]{{8, 15}, {8, 17}, {0, 11}, {4, 4}})));
    }
}
