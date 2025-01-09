package com.welph.leecode.algorithm.marscode.middle;

/**
 * 小S拿到了一个长度为 n 的环形数组，并定义了两个下标 i 和 j 的贡献值公式为：
 * f(i, j) = (a_i + a_j) × dist(i, j)
 *
 * 其中 dist(i, j) 是下标 i 和 j 在数组中的最短距离。
 * 小S希望找到一对下标，使得它们的贡献值尽可能大。
 * 环形数组的特点是最左和最右的元素也是相邻的。
 *
 * 你需要帮助她找到最大贡献值。
 *
 * 例如，给定数组 [1, 2, 3]，由于是环形数组，任意两个下标的距离都是1，
 * 因此 f(2,3)=(2+3)×1=5。
 *
 * 输入:
 *
 * n : 数组长度
 * a : 环形数组
 * 约束条件:
 *
 * n >= 1
 * 1 <= a[i] <= 1000
 */
public class ContributionFunctionForRingArray {

    public static int solution(int n, int[] a) {
        //环形数组, 左右连接
        //所以两个点之间的距离 = min(i-j, n-i+j)  i>j
        int result = 0;
        for (int i = 1; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                result = Math.max(result, (a[i] + a[j]) * Math.min(i - j, n - i + j));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(3, new int[]{1, 2, 3}) == 5);
        System.out.println(solution(4, new int[]{4, 1, 2, 3}) == 12);
        System.out.println(solution(5, new int[]{1, 5, 3, 7, 2}) == 24);
    }
}
