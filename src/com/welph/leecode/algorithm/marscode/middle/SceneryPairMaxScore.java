package com.welph.leecode.algorithm.marscode.middle;

/**
 * 小R正在研究一组观光景点，每个景点都有一个评分，保存在数组 values 中，
 * 其中 values[i] 表示第 i 个观光景点的评分。同时，景点之间的距离由它们的下标差 j - i 表示。
 *
 * 一对景点 (i < j) 的观光组合得分为 values[i] + values[j] + i - j，也就是两者评分之和减去它们之间的距离。
 *
 * 小R想知道，在哪种情况下能够获得观光景点组合的最高得分。
 *
 * 约束条件：
 * 2 <= values.length
 * 1 <= values[i] <= 1000
 */
public class SceneryPairMaxScore {

    public static int solution(int[] values) {
        //对于一个最大值, 往右移动就减少了1
        int max = values[0];
        int result = 0;
        for (int i = 1; i < values.length; i++) {
            max--;
            result = Math.max(values[i] + max, result);
            max = Math.max(values[i], max); //当前位置及以前的能够得到的最大值
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{8, 3, 5, 5, 6}) == 11 ? 1 : 0);
        System.out.println(solution(new int[]{10, 4, 8, 7}) == 16 ? 1 : 0);
        System.out.println(solution(new int[]{1, 2, 3, 4, 5}) == 8 ? 1 : 0);
    }
}
