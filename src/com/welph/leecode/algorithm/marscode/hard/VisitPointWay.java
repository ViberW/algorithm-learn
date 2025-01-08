package com.welph.leecode.algorithm.marscode.hard;

/**
 * 数轴上有n 个点ai，小U初始在原点。她希望按照一定的顺序访问这些点。
 *
 * 小U想知道在所有不同的访问顺序中，走过的路径的总和是多少。每种顺序对应的路径长度等于她从原点出发依次访问这些点所走的距离之和。
 *
 * 例如，有三个点[1,3,5]，按照顺序a1,a2,a3访问，走过的路径为：∣1−0∣+∣3−1∣+∣5−3∣=5。
 * 按照顺序 a1,a3,a2 访问，走过的路径为：∣1−0∣+∣5−1∣+∣3−5∣=7。
 * 所有的访问顺序有 n! 种。
 *
 * 你需要计算所有不同的访问顺序中走过的路径总和。答案对 1000000007取模。
 */
public class VisitPointWay {

    public static int solution(int n, int[] a) {
        final int MOD = 1000000007;
        /*
         * 这样思考, 由于总排列数为N! 那么对于随意选择两个数值, 它贡献的可能值选择有 (n-2)!种可能, 即固定第一位和第二位
         * 但随着[i][i+1]两个值不断位移, 从0到n, 有n-1的可能
         * =>  (n-2)! * (n-1) = (n-1)!
         */

        // 计算阶乘 (n-1)!
        long factorial = 1;
        for (int i = 1; i < n; i++) {
            factorial = (factorial * i) % MOD;
        }

        // 计算总路径长度
        long totalPathLength = 0;
        for (int i = 0; i < n; i++) {
            //从0到i的多种可能的总和
            totalPathLength = (totalPathLength + (long) Math.abs(a[i]) * factorial) % MOD;
            for (int j = i + 1; j < n; j++) {
                // 这里需要计算 a[j] - a[i] 的贡献
                // 每个点对的贡献是 (a[j] - a[i]) * factorial * 2
                // 因为每个点对在排列中会出现两次（a[i] -> a[j] 和 a[j] -> a[i]）
                long contribution = (long) Math.abs(a[j] - a[i]) * factorial * 2 % MOD;
                totalPathLength = (totalPathLength + contribution) % MOD;
            }
        }
        return (int) totalPathLength;
    }

    public static void main(String[] args) {
        System.out.println(solution(3, new int[]{1, 3, 5}) == 50);
        System.out.println(solution(4, new int[]{1, 2, 4, 7}) == 324);
        System.out.println(solution(2, new int[]{2, 6}) == 16);
    }

}
