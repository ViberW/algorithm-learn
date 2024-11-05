package com.welph.leecode.algorithm.marscode;

/**
 * 小U在一款挂机游戏中拥有n个英雄。游戏中有一种历练升级机制，每天可以选择两个英雄进行历练，
 * 如果两位英雄的等级相同，则他们的等级都不会改变。
 * 如果英雄等级不同，那么等级较高的英雄会增加1级，而等级较低的英雄则保持不变。
 *
 * 小U希望至少有一个英雄能够达到2000000000000000级，他想知道有多少英雄有潜力通过历练达到这个等级。
 */
public class HeroCompare {

    public static int solution(int n, int[] u) {
        int min = u[0];
        for (int i = 1; i < u.length; i++) {
            if (min > u[i]) {
                min = u[i];
            }
        }
        int total = 0;
        for (int i = 0; i < u.length; i++) {
            if (min == u[i]) {
                total++;
            }
        }
        return u.length - total;
    }

    public static void main(String[] args) {
        System.out.println(solution(5, new int[]{1, 2, 3, 1, 2}) == 3);
        System.out.println(solution(4, new int[]{100000, 100000, 100000, 100000}) == 0);
        System.out.println(solution(6, new int[]{1, 1, 1, 2, 2, 2}) == 3);
    }
}
