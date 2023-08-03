package com.welph.leecode.part_1_500.part_101_120;

import java.util.ArrayList;
import java.util.List;

/**
 * .给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * .
 * .相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * .
 * .例如，给定三角形：
 * .
 * .[
 * .     [2],
 * .    [3,4],
 * .   [6,5,7],
 * .  [4,1,8,3]
 * .]
 * .自顶向下的最小路径和为11（即，2+3+5+1= 11）。
 * .
 * .说明：
 * .
 * .如果你可以只使用 O(n)的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 */
public class Solution120 {

    public static void main(String[] args) {
        //[[-1],[3,2],[-3,1,-1]]
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(new ArrayList<Integer>() {{
            add(-1);
        }});
        triangle.add(new ArrayList<Integer>() {{
            add(3);
            add(2);
        }});
        triangle.add(new ArrayList<Integer>() {{
            add(-3);
            add(1);
            add(-1);
        }});

      /*  triangle.add(new ArrayList<Integer>() {{
            add(2);
        }});
        triangle.add(new ArrayList<Integer>() {{
            add(3);
            add(4);
        }});
        triangle.add(new ArrayList<Integer>() {{
            add(6);
            add(5);
            add(7);
        }});
        triangle.add(new ArrayList<Integer>() {{
            add(4);
            add(1);
            add(8);
            add(3);
        }});*/


        System.out.println(minimumTotal(triangle));
    }

    /**
     * 相邻节点 ：
     * dp动态规划： dp[i] 到达当前位置的最小路径和 = math.min(dp[i],dp[i+1],dp[i-1])+a[i];
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        if (len == 0) {
            return 0;
        }
        List<Integer> list = triangle.get(triangle.size() - 1);
        Integer[] dp = new Integer[list.size()];
        Integer pre;
        Integer cur;
        for (int i = 0; i < len; i++) {
            list = triangle.get(i);
            pre = null;
            for (int j = 0; j < list.size(); j++) {
                cur = dp[j];
                dp[j] = min(cur, pre) + list.get(j);
                pre = cur;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            if (dp[i] < min) {
                min = dp[i];
            }
        }
        return min;
    }

    private static int min(Integer a, Integer b) {
        a = a == null || (b != null && a > b) ? b : a;
        return null == a ? 0 : a;
    }
}
