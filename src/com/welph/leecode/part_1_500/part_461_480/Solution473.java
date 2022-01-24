package com.welph.leecode.part_1_500.part_461_480;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 还记得童话《卖火柴的小女孩》吗？现在，你知道小女孩有多少根火柴，请找出一种能使用所有火柴拼成一个正方形的方法。
 * 不能折断火柴，可以把火柴连接起来，并且每根火柴都要用到。
 * 输入为小女孩拥有火柴的数目，每根火柴用其长度表示。输出即为是否能用所有的火柴拼成正方形。
 * <p>
 * 示例1:
 * 输入: [1,1,2,2,2]
 * 输出: true
 * 解释: 能拼成一个边长为2的正方形，每边两根火柴。
 * <p>
 * 示例2:
 * 输入: [3,3,3,3,4]
 * 输出: false
 * 解释: 不能用所有火柴拼成一个正方形。
 * <p>
 * 注意:
 * 给定的火柴长度和在0到10^9之间。
 * 火柴数组的长度不超过15。
 */
public class Solution473 {

    public static void main(String[] args) {
        System.out.println(makesquare1(new int[]{1, 1, 2, 2, 2}));
        System.out.println(makesquare1(new int[]{3, 3, 3, 3, 4}));
    }

    /**
     * 取总数, 并判断每条边应该具有多大的长度
     */
    public static boolean makesquare1(int[] matchsticks) {
        int sum = 0;
        for (int matchstick : matchsticks) {
            sum += matchstick;
        }
        if (sum % 4 != 0) {
            return false;
        }
        //每条边预期的长度 = sum/4
        Arrays.sort(matchsticks);
        return makesquareEdge(matchsticks, matchsticks.length - 1, new int[4], sum / 4);
    }

    private static boolean makesquareEdge(int[] matchsticks, int index, int[] ints, int avg) {
        //判断长度适合那条边
        if (index == -1) {
            return ints[0] == avg && ints[1] == avg && ints[2] == avg && ints[3] == avg;
        }
        for (int i = 0; i < 4; i++) {
            if (ints[i] + matchsticks[index] > avg) {
                continue;
            }
            ints[i] += matchsticks[index];
            if (makesquareEdge(matchsticks, index - 1, ints, avg)) {
                return true;
            }
            ints[i] -= matchsticks[index];
        }
        return false;
    }

    /**
     * 每个火柴分别在4个边上, 查看每个边的
     * ---------------
     * 下面这种递归的方式会时间超出限制
     */
    public static boolean makesquare(int[] matchsticks) {
        if (matchsticks.length == 0) {
            return false;
        }
        int len = matchsticks[0];
        return makeEdgeSquare(matchsticks, 1, len, 0, 0, 0)
                || makeEdgeSquare(matchsticks, 1, 0, len, 0, 0)
                || makeEdgeSquare(matchsticks, 1, 0, 0, len, 0)
                || makeEdgeSquare(matchsticks, 1, 0, 0, 0, len);
    }

    public static boolean makeEdgeSquare(int[] matchsticks, int i, int l, int r, int u, int d) {
        if (i == matchsticks.length) {
            return l == r && u == d && l == u;
        }
        int len = matchsticks[i];
        return makeEdgeSquare(matchsticks, i + 1, l + len, r, u, d)
                || makeEdgeSquare(matchsticks, i + 1, l, r + len, u, d)
                || makeEdgeSquare(matchsticks, i + 1, l, r, u + len, d)
                || makeEdgeSquare(matchsticks, i + 1, l, r, u, d + len);
    }
}
