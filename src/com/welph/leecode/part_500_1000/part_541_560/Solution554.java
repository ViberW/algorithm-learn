package com.welph.leecode.part_500_1000.part_541_560;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 你的面前有一堵矩形的、由 n 行砖块组成的砖墙。这些砖块高度相同（也就是一个单位高）但是宽度不同。每一行砖块的宽度之和相等。
 * 你现在要画一条 自顶向下 的、穿过 最少 砖块的垂线。如果你画的线只是从砖块的边缘经过，
 * 就不算穿过这块砖。你不能沿着墙的两个垂直边缘之一画线，这样显然是没有穿过一块砖的。
 * <p>
 * 给你一个二维数组 wall ，该数组包含这堵墙的相关信息。
 * 其中，wall[i] 是一个代表从左至右每块砖的宽度的数组。你需要找出怎样画才能使这条线 穿过的砖块数量最少 ，并且返回 穿过的砖块数量 。
 * <p>
 * 示例 1：
 * 输入：wall = [[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：wall = [[1],[1],[1]]
 * 输出：3
 * <p>
 * 提示：
 * n == wall.length
 * 1 <= n <= 10^4
 * 1 <= wall[i].length <= 10^4
 * 1 <= sum(wall[i].length) <= 2 * 10^4
 * 对于每一行 i ，sum(wall[i]) 是相同的
 * 1 <= wall[i][j] <= 2^31 - 1
 */
public class Solution554 {

    public static void main(String[] args) {
        //[[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]
        System.out.println(leastBricks(Arrays.asList(
                Arrays.asList(1, 2, 2, 1),
                Arrays.asList(3, 1, 2),
                Arrays.asList(1, 3, 2),
                Arrays.asList(2, 4),
                Arrays.asList(3, 1, 2),
                Arrays.asList(1, 3, 1, 1)
        )));
    }

    /**
     * RangeContains
     * 每个到达的点的频率越高 就越大
     */
    public static int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (List<Integer> w : wall) {
            int sum = 0;
            for (int i = 0; i < w.size() - 1; i++) { //考虑最后一位不处理
                sum += w.get(i);
                int f = map.getOrDefault(sum, 0) + 1;
                max = Math.max(max, f);
                map.put(sum, f);
            }
        }
        return wall.size() - max;
    }
}
