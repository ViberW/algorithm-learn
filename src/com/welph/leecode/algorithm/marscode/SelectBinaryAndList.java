package com.welph.leecode.algorithm.marscode;

import java.util.*;

/**
 * 小E有一个长度为n的数组，她想从中选择一个或多个数，使得这些数的按位与（AND）的结果不为0，
 * 并且这个结果可以被2^m整除。她的目标是选取这样的数，使得整数m尽可能地大。
 * <p>
 * 你需要帮助小E找到能够使得m最大化的子集。
 */
public class SelectBinaryAndList {

    public static int solution(int n, int[] a) {
        int maxValue = Arrays.stream(a).max().getAsInt();
        int max = Integer.numberOfLeadingZeros(maxValue);
        int m = 31 - max;
        while (m > 0) {
            int val = 1 << m;
            int result = -1;
            for (int i = 0; i < n; i++) {
                if ((a[i] & val) == val) {
                    result &= a[i];
                }
            }
            if ((result & (val - 1)) == 0) {
                break;
            }
            m--;
        }
        return m;
    }

    public static void main(String[] args) {
        System.out.println(solution(5, new int[]{1, 2, 3, 20, 28}) == 2);
        System.out.println(solution(4, new int[]{16, 8, 4, 2}) == 4);
        System.out.println(solution(6, new int[]{7, 14, 28, 56, 112, 224}) == 5);
        System.out.println(solution(8, new int[]{14, 9, 11, 17, 1, 9, 17, 14}) == 3);
    }
}
