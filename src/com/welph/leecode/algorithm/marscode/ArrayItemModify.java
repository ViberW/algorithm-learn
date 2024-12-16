package com.welph.leecode.algorithm.marscode;

/**
 * 小R拿到了一个数组，她可以进行如下操作：使得一个元素加1，另一个元素减1。
 *
 * 她希望最终数组的每个元素大小都在 [l, r] 的范围内。小R想知道，最少需要多少次操作可以达到目标。
 *
 * 如果无法通过有限次操作使所有元素都落在指定范围内，则返回 -1。
 */
public class ArrayItemModify {

    public static int solution(int n, int l, int r, int[] a) {
        if (l > r) {
            return -1;
        }
        int minAdd = 0;
        int maxAdd = 0;
        int minSubtract = 0;
        int maxSubtract = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] < r) {
                maxAdd += r - a[i];
                if (a[i] < l) {
                    minAdd += l - a[i];
                }
            }

            if (a[i] > l) {
                maxSubtract += a[i] - l;
                if (a[i] > r) {
                    minSubtract += a[i] - r;
                }
            }
        }
        if (minAdd > maxSubtract || minSubtract > maxAdd) {
            return -1;
        }
        return Math.max(minAdd, minSubtract);
    }

    public static void main(String[] args) {
//        System.out.println(solution(2, 3, 5, new int[]{1, 2}) == -1);
//        System.out.println(solution(3, 4, 6, new int[]{3, 6, 5}) == 1);
//        System.out.println(solution(4, 2, 8, new int[]{1, 10, 2, 6}) == 2);
        System.out.println(solution(3, 15, 11, new int[]{11, 15, 11}) == -1);
    }
}
