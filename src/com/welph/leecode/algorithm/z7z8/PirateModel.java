package com.welph.leecode.algorithm.z7z8;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 海盗分金币：在大海上，有5个海盗抢得100枚金币，他们决定每一个人按顺序依次提出自己的分配方案，
 * 如果提出的方案没有获得半数或半数以上的人的同意，则这个提出方案的人就被扔到海里喂鲨鱼。
 * 那么第一个提出方案的人要怎么做，才能使自己的利益最大化？
 */
public class PirateModel {

    // 金币数量
    private int coinCount;
    // 海盗数量
    private int pirateCount;

    private PirateModel(int coinCount, int pirateCount) {
        this.coinCount = coinCount;
        this.pirateCount = pirateCount;
    }

    private int[] resolvePuzzle() {
        return getDistribution(pirateCount);
    }

    /**
     * 获取当前海盗数量下，对提出方案的海盗来说，其余海盗每人都得到满足的需求量
     *
     * @param currPirateCountTotal 当前的海盗数量
     * @return 其余海盗每人都得到满足的需求量
     */
    private int[] getDistribution(int currPirateCountTotal) {
        if (currPirateCountTotal == 1) {
            return new int[] { coinCount };
        } else {
            int[] minsufficient = getDistribution(currPirateCountTotal - 1);
            return getDistributionByMin(minsufficient);
        }
    }

    /**
     * 获取在已知所有其他海盗的最小满足量时的最佳分配方案
     *
     * @param othersMinDistribution 已知所有其他海盗的最小满足量
     * @return 分配方案
     */
    private int[] getDistributionByMin(int[] othersMinDistribution) {
        // 总共需要的票数
        int countToatal = (othersMinDistribution.length + 1) / 2 + 1;
        int[] finalResolve = new int[othersMinDistribution.length + 1];
        int[] others = findBestSolution(othersMinDistribution, countToatal - 1);

        int othersSum = 0;
        for (int i = 0; i < others.length; i++) {
            finalResolve[i + 1] = others[i];
            othersSum += others[i];
        }
        finalResolve[0] = coinCount - othersSum;
        return finalResolve;
    }

    /**
     * 从数组中找出指定个数的元素，使它们的和最小，然后将那些元素+1
     * 待完善：本解法不考虑多种方案的情况，只找出一个方案
     *
     * @param array 待选择数组
     * @param count 指定的元素个数
     * @return +1之后的数组
     */
    private int[] findBestSolution(int[] array, int count) {
        int minTest = 0;
        int found = 0;
        Set<Integer> indexSet = new HashSet<>();// 保存被分配的海盗的坐标
        while (found < count) {
            for (int j = array.length - 1; j >= 0; j--) {
                if (minTest == array[j]) {
                    found++;
                    indexSet.add(j);
                }
                if (found == count) {
                    break;
                }
            }
            minTest++;
        }

        // 将其余的都置为0
        for (int i = 0; i < array.length; i++) {
            if (!indexSet.contains(i)) {
                array[i] = 0;
            } else if (array[i] < coinCount) {
                array[i] += 1;
            }
        }

        return array;
    }

    public static void main(String[] args) {
        PirateModel pp = new PirateModel(100, 5);
        int[] solution = pp.resolvePuzzle();
        if (solution[0] < 0) {
            System.out.println("必死无疑！！！");
        } else {
            System.out.println(pp.pirateCount + "人时分配方案：" + Arrays.toString(solution));
        }
    }

}
