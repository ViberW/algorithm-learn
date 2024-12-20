package com.welph.leecode.algorithm.marscode;

/**
 * 小C有两个长度为 N 的数组 A 和 B。他可以进行以下两种操作，来将数组 A 转换为数组 B：
 *
 * 反转数组 A，即使数组 A 的元素顺序完全颠倒。
 * 在 [1, N] 范围内选择一个整数 i，然后可以对 A[i] 添加或减去任意值。
 * 你的任务是帮助小C找到使数组 A 等于数组 B 所需的最小操作次数。
 *
 * 例如：当 N = 3，A = [1, 2, 5]，B = [4, 2, 1] 时，最佳操作如下：
 *
 * 第一步反转数组 A，得到新数组 A = [5, 2, 1]。
 * 第二步从位置 1 减去 1，得到新数组 A = [4, 2, 1]。
 * 因此，答案是 2。
 */
public class MinStepToNewArray {

    public static int solution(int N, int[] A, int[] B) {
        //有且最多仅有一次反转
        int revertResult = 1;
        int result = 0;
        for (int i = 0, j = N - 1; i < N; i++, j--) {
            if (A[i] != B[i]) {
                result++;
            }
            if (A[i] != B[j]) {
                revertResult++;
            }
        }
        return Math.min(revertResult, result);
    }

    public static void main(String[] args) {
        System.out.println(solution(3, new int[]{1, 2, 5}, new int[]{4, 2, 1}) == 2);
        System.out.println(solution(4, new int[]{7, 8, 6, 2}, new int[]{6, 2, 8, 7}) == 3);
        System.out.println(solution(2, new int[]{3, 9}, new int[]{9, 3}) == 1);
    }
}
