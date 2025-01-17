package com.welph.leecode.algorithm.marscode.middle;

import java.util.Arrays;

/**
 * 小S正在玩一个关于石子的游戏，给定了一些石子，
 * 它们位于一维数轴的不同位置，位置用数组 stones 表示。
 *
 * 如果某个石子处于最小或最大的一个位置，我们称其为端点石子。
 * 在每个回合，小S可以将一颗端点石子移动到一个未占用的位置，使其不再是端点石子。
 * 游戏继续，直到石子的位置变得连续，无法再进行任何移动操作。
 *
 * 你需要帮助小S找到可以移动的最大次数。
 */
public class ContinuousStones {

    public static int solution(int[] stones) {
        Arrays.sort(stones);
        //两端,每次移动,都需要紧靠另一端点
        int res = 0;
        // 记录左右端点，方便取最值
        int l = 0, r = 0;
        for (int i = 1; i < stones.length; i++) {
            // 左端点
            if (i == 1) l = stones[i] - stones[i - 1] - 1;
                // 右端点
            else if (i == stones.length - 1) r = stones[i] - stones[i - 1] - 1;
                // 非端点，直接统计
            else res += stones[i] - stones[i - 1] - 1;
        }
        // 返回结果
        return res + Math.max(l, r);
    }

    public static int solution1(int[] stones) {
        int n = stones.length;
        if (n < 3) {
            return 0;
        }
        Arrays.sort(stones);
        //两端,每次移动,都需要紧靠另一端点
        int totalEmpty = stones[n - 1] - stones[0] + 1 - n; //中间的间隔
        int leftGap = stones[1] - stones[0] - 1; //左端点
        int rightGap = stones[n - 1] - stones[n - 2] - 1; //右端点
        return totalEmpty - Math.min(leftGap, rightGap);
    }

    public static void main(String[] args) {
        //最小移动是1 最大移动次数是2
        System.out.println(solution(new int[]{7, 4, 9}) == 2);
        System.out.println(solution(new int[]{6, 5, 4, 3, 10}) == 3);
        System.out.println(solution(new int[]{1, 2, 3, 4, 5}) == 0);
    }
}
