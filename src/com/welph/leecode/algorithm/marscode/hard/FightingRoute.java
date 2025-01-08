package com.welph.leecode.algorithm.marscode.hard;

import java.util.Arrays;

/**
 * 小C遇到了n 个怪物，第i 个怪物的战斗力为ai，而小C的初始战斗力为0。他需要依次与这些怪物战斗。战斗时的规则如下：
 *
 * 如果小C的战斗力x 小于怪物的战斗力ai，小C会触发被动技能 "小C不怕困难"，
 * 将自己的战斗力提升至ai，并战胜这个怪物，同时小C的勇气值增加ai−x
 *
 * 如果小C的战斗力x 大于或等于怪物的战斗力ai，他会直接战胜这个怪物，战斗结束后，小C的战斗力会降低至ai
 *
 * 小C可以自由决定与怪物战斗的顺序。他想知道，在打败所有怪物后，自己能够累计提升的勇气值最大是多少。
 */
public class FightingRoute {

    public static int solution(int n, int[] a) {
        //x<ai -> x=ai,value+=(ai-x)
        //x>=ai -> x=ai
        Arrays.sort(a);
        int courage = 0;
        int l = -1;
        int x = 0;
        int r = n - 1;
        while (l < r) {
            courage += a[r] - x;
            r--;
            l++;
            x = a[l];
        }
        return courage;
    }

    public static void main(String[] args) {
        int[] a1 = {1, 2};
        System.out.println(solution(2, a1) == 2);

        int[] a2 = {1, 2, 2};
        System.out.println(solution(3, a2) == 3);

        int[] a3 = {3, 6, 2, 8};
        System.out.println(solution(4, a3) == 12);
    }
}
