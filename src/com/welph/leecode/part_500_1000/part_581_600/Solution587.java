package com.welph.leecode.part_500_1000.part_581_600;

import java.util.*;

/**
 * 给定一个数组 trees，其中 trees[i] = [xi, yi] 表示树在花园中的位置。
 * 你被要求用最短长度的绳子把整个花园围起来，因为绳子很贵。只有把 所有的树都围起来，花园才围得很好。
 * 返回恰好位于围栏周边的树木的坐标。
 * <p>
 * 示例 1:
 * 输入: points = [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
 * 输出: [[1,1],[2,0],[3,3],[2,4],[4,2]]
 * <p>
 * 示例 2:
 * 输入: points = [[1,2],[2,2],[4,2]]
 * 输出: [[4,2],[2,2],[1,2]]
 * <p>
 * 注意:
 * 1 <= points.length <= 3000
 * points[i].length == 2
 * 0 <= xi, yi <= 100
 * 所有给定的点都是 唯一 的。
 */
public class Solution587 {

    public static void main(String[] args) {
        int[][] ints = outerTrees(new int[][]{{1, 1}, {2, 2}, {2, 0}, {2, 4}, {3, 3}, {4, 2}});
        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }
        System.out.println("====================");
        ints = outerTrees(new int[][]{{1, 2}, {2, 2}, {4, 2}});
        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }
        System.out.println("====================");
        ints = outerTrees(new int[][]{{0, 2}, {1, 1}, {2, 2}, {2, 4}, {4, 2}, {3, 3}});
        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }
        System.out.println("====================");
        ints = outerTrees(new int[][]{{1, 5}});
        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }
    }

    /**
     * 几何体 {@link com.welph.leecode.part_1_500.part_441_460.Solution456}
     * 下方找凹
     * 上方找凸
     * ----- 很好的方法
     */
    public static int[][] outerTrees(int[][] trees) {
        Arrays.sort(trees, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int compare = o1[0] - o2[0];
                return compare == 0 ? o1[1] - o2[1] : compare;
            }
        });
        Set<int[]> set = new HashSet<>();
        int length = trees.length;
        int equal = 0;
        List<int[]> upper = new ArrayList<>();
        List<int[]> lower = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if (trees[equal][0] != trees[i][0]) {
                lower.add(trees[equal]);
                upper.add(trees[i - 1]);
                if (equal == 0) {
                    for (int k = 0; k < i; k++) {
                        set.add(trees[k]);
                    }
                } else {
                    match(upper, lower);
                }
                equal = i;
            }
        }
        //最后一位
        for (int k = equal; k < length; k++) {
            set.add(trees[k]);
        }
        lower.add(trees[equal]);
        upper.add(trees[length - 1]);
        match(upper, lower);

        set.addAll(upper);
        set.addAll(lower);
        return set.toArray(new int[set.size()][2]);
    }

    private static void match(List<int[]> upper, List<int[]> lower) {
        int[] l;
        int[] r;
        int[] m;
        //下方找凹
        while (lower.size() > 2) {
            r = lower.get(lower.size() - 1);
            m = lower.get(lower.size() - 2);
            l = lower.get(lower.size() - 3);
            if ((m[1] - l[1]) * (r[0] - m[0]) >
                    (r[1] - m[1]) * (m[0] - l[0])) {
                //说明是凸, 需要移除中间
                lower.remove(lower.size() - 2);
            } else {
                break;
            }
        }
        //上方找凸
        while (upper.size() > 2) {
            r = upper.get(upper.size() - 1);
            m = upper.get(upper.size() - 2);
            l = upper.get(upper.size() - 3);
            if ((m[1] - l[1]) * (r[0] - m[0]) <
                    (r[1] - m[1]) * (m[0] - l[0])) {
                //说明是凸, 需要移除中间
                upper.remove(upper.size() - 2);
            } else {
                break;
            }
        }
    }
}
