package com.welph.leecode.part_1_500.part_321_340;

/**
 * 给定一个含有 n 个正数的数组 x。从点 (0,0) 开始，先向北移动 x[0] 米，
 * 然后向西移动 x[1] 米，向南移动 x[2] 米，向东移动 x[3] 米，持续移动。
 * 也就是说，每次移动后你的方位会发生逆时针变化。
 * <p>
 * 编写一个 O(1) 空间复杂度的一趟扫描算法，判断你所经过的路径是否相交。
 * <p>
 * * 示例 1:
 * * ┌───┐
 * * │   │
 * * └───┼──>
 * *     │
 * * 输入: [2,1,1,2]
 * * 输出: true
 * *
 * * 示例 2:
 * * ┌──────┐
 * * │      │
 * * │
 * * │
 * * └────────────>
 * * 输入: [1,2,3,4]
 * * 输出: false
 * *
 * * 示例 3:
 * * ┌───┐
 * * │   │
 * * └───┼>
 * * 输入: [1,1,1,1]
 * * 输出: true
 */
public class Solution335 {

    public static void main(String[] args) {
        System.out.println(isSelfCrossing(new int[]{2, 1, 1, 2}));
        System.out.println(isSelfCrossing(new int[]{1, 2, 3, 4}));
        System.out.println(isSelfCrossing(new int[]{1, 1, 1, 1}));
    }

    /**
     * 逆时针移动  todo 感觉绕了..._(¦3」∠)_
     */
    public static boolean isSelfCrossing(int[] distance) {

        return false;
    }

    /* 官方题解 */
    //归纳法
    /*
     * @link https://leetcode.cn/problems/self-crossing/solutions/1069749/lu-jing-jiao-cha-by-leetcode-solution-dekx/
     */
    public boolean isSelfCrossing2(int[] distance) {
        int n = distance.length;
        for (int i = 3; i < n; ++i) {
            // 第 1 类路径交叉的情况
            if ((distance[i] >= distance[i - 2] ) && (distance[i - 1] <= distance[i - 3])) {
                return true;
            }

            // 第 2 类路径交叉的情况
            if (i == 4 && (distance[3] == distance[1]
                && distance[4] >= distance[2] - distance[0])) {
                return true;
            }

            // 第 3 类路径交叉的情况
            if (i >= 5 && (distance[i - 3] - distance[i - 5] <= distance[i - 1]
                && distance[i - 1] <= distance[i - 3]
                && distance[i] >= distance[i - 2] - distance[i - 4]
                && distance[i - 2] > distance[i - 4])) {
                return true;
            }
        }
        return false;
    }
}
