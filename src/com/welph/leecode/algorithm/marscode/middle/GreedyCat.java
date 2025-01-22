package com.welph.leecode.algorithm.marscode.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 在猫星球上，小R负责给一行排队的猫分发鱼干。每只猫有一个等级，等级越高的猫应该得到更多的鱼干。规则如下：
 *
 * 每只猫至少得到一斤鱼干。
 * 如果一只猫的等级高于它相邻的猫，它就应该得到比相邻的猫更多的鱼干。
 * 小R想知道，为了公平地满足所有猫的等级差异，他至少需要准备多少斤鱼干。
 */
public class GreedyCat {

    public static int solution(int n, List<Integer> catsLevels) {
        int ret = n;
        int[] levels = new int[n];
        for (int i = 1; i < n; i++) {
            if (catsLevels.get(i) > catsLevels.get(i - 1)) {
                levels[i] = levels[i - 1] + 1;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (catsLevels.get(i) > catsLevels.get(i + 1)) {
                levels[i] = Math.max(levels[i + 1] + 1, levels[i]);
            }
        }
        // Please write your code here
        return ret + Arrays.stream(levels).sum();
    }

    public static void main(String[] args) {
        List<Integer> catsLevels1 = new ArrayList<>();
        catsLevels1.add(1);
        catsLevels1.add(2);
        catsLevels1.add(2);

        List<Integer> catsLevels2 = new ArrayList<>();
        catsLevels2.add(6);
        catsLevels2.add(5);
        catsLevels2.add(4);
        catsLevels2.add(3);
        catsLevels2.add(2);
        catsLevels2.add(16);

        List<Integer> catsLevels3 = new ArrayList<>();
        catsLevels3.add(1);
        catsLevels3.add(2);
        catsLevels3.add(2);
        catsLevels3.add(3);
        catsLevels3.add(3);
        catsLevels3.add(20);
        catsLevels3.add(1);
        catsLevels3.add(2);
        catsLevels3.add(3);
        catsLevels3.add(3);
        catsLevels3.add(2);
        catsLevels3.add(1);
        catsLevels3.add(5);
        catsLevels3.add(6);
        catsLevels3.add(6);
        catsLevels3.add(5);
        catsLevels3.add(5);
        catsLevels3.add(7);
        catsLevels3.add(7);
        catsLevels3.add(4);

        System.out.println(solution(3, catsLevels1) == 4);
        System.out.println(solution(6, catsLevels2) == 17);
        System.out.println(solution(20, catsLevels3) == 35);
    }
}
