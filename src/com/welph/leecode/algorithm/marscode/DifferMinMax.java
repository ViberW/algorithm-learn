package com.welph.leecode.algorithm.marscode;

/**
 * 小B拥有一个数组 a，她使用这个数组构造了一个新数组 b。其中，a[i] 表示在新数组 b 中有 a[i] 个 i+1。
 * 例如，若 a = [2, 3, 1]，那么新数组 b = [1, 1, 2, 2, 2, 3]，
 * 因为 a[0] = 2 代表数组 b 中有 2 个 1，a[1] = 3 代表数组 b 中有 3 个 2，a[2] = 1 代表数组 b 中有 1 个 3。
 * <p>
 * 现在，你需要帮助小B求出 b 数组中所有连续子数组的极差之和。由于答案可能非常大，请对 100000007 取模。
 * <p>
 * 数组的极差定义为子数组的最大值减去最小值。
 */
public class DifferMinMax {
    public static int solution(int n, int[] a) {
        // write code here
        int[] lastsum = new int[n + 1]; //后缀和
        int sum = 0;
        for (int i = n - 1; i > 0; i--) {
            lastsum[i] = a[i] + lastsum[i + 1];
            sum += lastsum[i];
        }
        int ret = 0;
        for (int i = 0; i < n - 1; i++) {
            ret += a[i] * sum;
            sum -= lastsum[i + 1];
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(solution(2, new int[]{2, 1}) == 2);
        System.out.println(solution(3, new int[]{1, 2, 1}) == 6);
        System.out.println(solution(4, new int[]{2, 3, 1, 1}) == 26);
    }
}
