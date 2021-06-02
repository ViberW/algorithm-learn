package com.welph.leecode.algorithm.z7z8;

import java.util.Arrays;

/**
 * 100个人回答五道题，有81人答对第一题，91人答对第二题，85人答对第三题，79人答对第四题，74人答对第五题。
 * 给出最少多少人答对3题
 */
public class LeastThreeAnswer {
    public static void main(String[] args) {
        int[] arr = {81, 91, 85, 79, 74};
        System.out.println(100 - least(arr));
    }

    /**
     * 根据答错的人数, 排序, 通过 [贪心算法] 每次找出最大错误的3道题(说明这些人不会是结果数)的人
     * > 类似的题目如: 有5个矩形，顺序可随意。要切出宽度为3的N个矩形，要求总体叠加要尽量的高，最高有多少?
     */
    private static int least(int[] arr) {
        int len = arr.length;
        int[] errors = new int[len];
        for (int i = 0; i < len; i++) {
            errors[i] = 100 - arr[i];
        }
        int total = 0;
        while (true) {
            Arrays.sort(errors);
            //每次取前三个的最小值
            int current = errors[len - 3];
            if (current == 0) {
                break;
            }
            for (int i = len - 1; i > len - 4; i--) {
                errors[i] = errors[i] - current;
            }
            total += current;
        }
        return total;
    }

}
