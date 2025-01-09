package com.welph.leecode.algorithm.marscode.middle;

/**
 * 对于一个有 N 个元素的数组，包含如下的元素 h1, h2, ..., hn，对于 k 个相邻的元素，
 *
 * 我们定义它的最大面积如下：
 * R(k)=k∗min(h[i],h[i+1],....,h[i+k−1])
 *
 * 求 R(k) 的最大值
 */
public class MaxAreaArrayRangeK {

    public static int solution(int n, int[] array) {
        int result = 0;
        for (int i = 0; i < n; i++) {
            int min = array[i];
            for (int j = i; j >= 0; j--) {
                min = Math.min(min, array[j]);
                result = Math.max(result, min * (i - j + 1));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // Add your test cases here

        System.out.println(solution(5, new int[]{1, 2, 3, 4, 5}) == 9);
    }
}
