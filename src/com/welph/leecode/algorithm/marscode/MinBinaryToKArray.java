package com.welph.leecode.algorithm.marscode;

/**
 * 小U拿到了一个由n 个正整数组成的数组，她需要从中选择一个连续子数组，
 * 使得该子数组中所有元素的乘积在二进制表示中末尾至少有k 个连续的0。
 *
 * 也就是说，子数组的乘积必须是2^k的倍数。
 *
 * 你的任务是找到满足条件的最短连续子数组的长度。如果不存在这样的子数组，输出−1。
 *
 * 例如，给定数组 [1, 2, 3, 4, 5, 6] 和k=3，你需要找到一个连续子数组，
 * 它的乘积在二进制中尾部至少有3个0。取子数组 [2, 3, 4] 时，乘积2×3×4=24，其二进制表示为 11000，尾部有3个0。
 */
public class MinBinaryToKArray {

    public static int solution(int n, int k, int[] a) {
        // 找出每个数值的2的情况,
        int[] counts = new int[n];
        for (int i = 0; i < n; i++) {
            counts[i] = Integer.numberOfTrailingZeros(a[i]);
        }
        //因为需要连续子数组, 至少有k 且最短的
        int result = n + 1;
        int current = 0;
        for (int i = 0, l = 0; i < n; i++) {
            current += counts[i];
            while (l <= i && current >= k) {
                result = Math.min(result, i - l + 1);
                current -= counts[l];
                l++;
            }
        }
        return result > n ? -1 : result;
    }

    public static void main(String[] args) {
        System.out.println(solution(6, 3, new int[]{1, 2, 3, 4, 5, 6}) == 3);
        System.out.println(solution(5, 2, new int[]{10, 2, 8, 5, 4}) == 1);
        System.out.println(solution(4, 4, new int[]{1, 3, 5, 7}) == -1);
    }
}
