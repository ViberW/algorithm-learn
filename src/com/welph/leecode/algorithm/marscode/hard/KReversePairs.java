package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小R最近在研究一个由0和1组成的数组。
 * 他有一个大小为N的二进制数组A，其中每个元素要么是0，要么是1，并且编号从0到N - 1。
 * 他发现，如果某个索引i小于索引j，并且满足A[i] > A[j]，则可以说这对(i, j)形成了一个反转。
 *
 * 此外，小R还给定了一个整数K，现在他想知道，二进制数组A中有多少子数组正好包含K个反转。
 *
 * 你需要帮助小R计算出包含K个反转的子数组数量，并返回结果。
 */
public class KReversePairs {

    public static int solution(int N, int K, int[] A) {
        int result = 0;
        int pre = 0;
        for (int i = 0; i < N; i++) {
            if (A[i] == 1) {
                result += pre;
                continue;
            }
            int countPair = 0;
            int cnt = 0;
            for (int j = i, k = 0; j >= 0 && k <= K; j--) {
                if (A[j] == 0) {
                    k++;
                } else {
                    countPair += k;
                    if (countPair > K) {
                        break;
                    }
                }
                if (countPair == K) {
                    cnt++;
                }
            }
            result += cnt;
            pre = cnt;
        }
        return result;
    }

    public static void main(String[] args) {
        //100   110   0110
        System.out.println(solution(5, 2, new int[]{0, 1, 1, 0, 0}) == 3);
        System.out.println(solution(6, 3, new int[]{1, 0, 0, 1, 1, 0}) == 0);
        System.out.println(solution(4, 1, new int[]{1, 0, 1, 0}) == 4);
    }
}
