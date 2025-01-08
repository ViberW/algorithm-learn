package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小U手上有两个长度为n的数组A和B。她需要找到所有的区间[L, R]，
 * 满足在这个区间内，数组A的元素和在范围[La, Ra]之间，
 * 同时数组B的元素和在范围[Lb, Rb]之间。
 *
 * 你能帮助她计算满足条件的区间数量吗？
 */
public class RangeSumPattern {

    public static int solution(int N, int[] A, int[] B, int La, int Ra, int Lb, int Rb) {
        int result = 0;
        //需要考虑数值位负数的情况....
        //最暴力方式, 前缀和+一个个匹配
        for (int i = 0; i < N; i++) {
            int sumA = 0;
            int sumB = 0;
            for (int j = i; j >= 0; j--) {
                sumA += A[j];
                sumB += B[j];
                if (sumA >= La && sumA <= Ra
                        && sumB >= Lb && sumB <= Rb) {
                    result++;
                }
            }
        }
        return result;
    }

    //前缀和也是可以的
    public static int solution1(int N, int[] A, int[] B, int La, int Ra, int Lb, int Rb) {
        int result = 0;

        // 构建前缀和数组
        int[] prefixSumA = new int[N + 1];
        int[] prefixSumB = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            prefixSumA[i] = prefixSumA[i - 1] + A[i - 1];
            prefixSumB[i] = prefixSumB[i - 1] + B[i - 1];
        }

        // 使用双指针或滑动窗口
        for (int left = 0; left < N; left++) {
            for (int right = left; right < N; right++) {
                int sumA = prefixSumA[right + 1] - prefixSumA[left];
                int sumB = prefixSumB[right + 1] - prefixSumB[left];
                if (sumA >= La && sumA <= Ra && sumB >= Lb && sumB <= Rb) {
                    result++;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(4, new int[]{1, 4, 2, 3}, new int[]{2, 4, 1, 1},
                3, 7, 4, 6) == 3);
        System.out.println(solution(5, new int[]{5, 1, 3, 2, 4}, new int[]{6, 3, 2, 1, 1},
                6, 10, 5, 9) == 3);
        System.out.println(solution(3, new int[]{2, 2, 1}, new int[]{3, 3, 1},
                4, 6, 7, 9) == 1);
    }
}
