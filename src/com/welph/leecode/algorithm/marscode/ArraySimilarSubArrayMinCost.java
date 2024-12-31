package com.welph.leecode.algorithm.marscode;

/**
 * 在一个奇幻的世界里，两个探险家，小冬和小林，正在探索两个神秘的宝物阵列。
 * 第一个阵列 goal 包含一些独特的宝物，每个宝物的编号都是不同的；
 * 第二个阵列 treasures 可能包含重复的宝物编号。
 *
 * 小冬和小林的目标是通过尽量少的操作次数，将 goal 阵列变成 treasures 阵列的一个子序列。
 *
 * 每次操作中，他们可以在 treasures 的任意位置插入一个新的宝物编号。
 * 一个子序列是指通过删除某些宝物而保持其余宝物的相对顺序不变，所得到的新的宝物阵列。
 *
 * 你需要帮助小冬和小林计算出，最少需要多少次插入操作，才能使 goal 成为 treasures 的一个子序列。
 */
public class ArraySimilarSubArrayMinCost {

    public static int solution(int[] goal, int[] treasures) {
        //动态规划.

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{7, 1, 3}, new int[]{7, 2, 3, 5, 8}) == 1);
        System.out.println(solution(new int[]{1, 3, 6}, new int[]{9, 1, 3, 6, 5}) == 0);
        System.out.println(solution(new int[]{4, 6, 9, 11}, new int[]{1, 2, 4, 5, 6}) == 2);
    }
}
