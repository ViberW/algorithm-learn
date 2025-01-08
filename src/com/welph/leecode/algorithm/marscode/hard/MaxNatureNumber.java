package com.welph.leecode.algorithm.marscode.hard;

import java.util.Arrays;

/**
 * 小F手中有一个包含n个自然数的集合（集合中的数字可能会重复出现）。他可以对集合中的数字进行若干次操作，
 * 每次操作可以选择集合中的一个数字x，并将其替换为一个大于x的数字 y。
 * 小F的目标是通过这些操作，使得集合中最大的未出现的自然数K尽可能大。
 *
 * 你的任务是计算出这个最大的K值。
 */
public class MaxNatureNumber {

    public static int solution(int n, int[] a) {
        //排序,然后记录多出来的数量,并填充到空缺的位置
        Arrays.sort(a);
        int addition = 0;
        int expected = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0 && a[i] < expected) {
                addition++;
            }
            if (a[i] != expected) {
                if (addition == 0) {
                    break;
                }
                addition--;
            }
            expected++;
        }
        return expected;
    }

    public static void main(String[] args) {
        System.out.println(solution(5, new int[]{5, 0, 0, 2, 2}) == 4);
        System.out.println(solution(6, new int[]{1, 0, 3, 4, 4, 5}) == 2);
        System.out.println(solution(4, new int[]{0, 1, 2, 3}) == 4);
        System.out.println(solution(7, new int[]{7, 6, 5, 4, 3, 2, 1}) == 0);
        System.out.println(solution(5, new int[]{0, 1, 2, 1, 3}) == 5);
    }

}
