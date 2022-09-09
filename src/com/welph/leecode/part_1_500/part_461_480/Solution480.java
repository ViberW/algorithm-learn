package com.welph.leecode.part_1_500.part_461_480;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 中位数是有序序列最中间的那个数。如果序列的长度是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
 * 例如：
 * [2,3,4]，中位数是3
 * [2,3]，中位数是 (2 + 3) / 2 = 2.5
 * 给你一个数组 nums，有一个长度为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。
 * 你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
 * <p>
 * 示例：
 * 给出nums = [1,3,-1,-3,5,3,6,7]，以及k = 3。
 * 窗口位置                      中位数
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 * 1 [3  -1  -3] 5  3  6  7      -1
 * 1  3 [-1  -3  5] 3  6  7      -1
 * 1  3  -1 [-3  5  3] 6  7       3
 * 1  3  -1  -3 [5  3  6] 7       5
 * 1  3  -1  -3  5 [3  6  7]      6
 * 因此，返回该滑动窗口的中位数数组[1,-1,-1,3,5,6]。
 * <p>
 * 提示：
 * 你可以假设k始终有效，即：k 始终小于等于输入的非空数组的元素个数。
 * 与真实值误差在 10 ^ -5 以内的答案将被视作正确答案。
 */
public class Solution480 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
        System.out.println(Arrays.toString(medianSlidingWindow(
                new int[]{-2147483648, -2147483648,
                        2147483647, -2147483648, -2147483648, -2147483648, 2147483647, 2147483647, 2147483647, 2147483647, -2147483648, 2147483647, -2147483648
                }, 3)));

        //[-2147483648.0,-2147483648.0,-2147483648.0,-2147483648.0,-2147483648.0,2147483647.0,2147483647.0,2147483647.0,2147483647.0,2147483647.0,-2147483648.0]
        //[-2147483648.0,-2147483648.0,-2147483648.0,-2147483648.0,2147483647.0,2147483647.0,2147483647.0,2147483647.0,-2147483648.0,-2147483648.0,-2147483648.0]

    }

    /**
     * {@link com.welph.leecode.part_1_500.part_281_300.Solution295}
     * 这里需要排序, 优先级队列.
     */
    public static double[] medianSlidingWindow(int[] nums, int k) {
        double[] ret = new double[nums.length - k + 1];
        boolean odd = k % 2 == 1;
        //由于这里需要删除过去的节点.
        int minLen = k / 2 + k % 2;
        int maxLen = k / 2;
        PriorityQueue<Integer> min = new PriorityQueue<>(minLen, (o1, o2) -> Integer.compare(o2, o1));
        PriorityQueue<Integer> max = new PriorityQueue<>(maxLen == 0 ? 1 : maxLen, Integer::compareTo);
        int num;
        for (int i = 0; i < k; i++) {
            num = nums[i];
            fillNum(min, max, num);
        }
        //获取最新的一条记录
        ret[0] = mid(min, max, odd);
        int lNum;
        for (int i = k; i < nums.length; i++) {
            //找到删除的  新增一个新的
            num = nums[i];
            lNum = nums[i - k];
            if (min.peek() >= lNum) {
                min.remove(lNum);
                if (k > 1 && min.isEmpty()) {
                    min.offer(max.poll());
                }
                fillNum(min, max, num);
            } else {
                max.remove(lNum);
                fillNum(min, max, num);
            }
            ret[i - k + 1] = mid(min, max, odd);
        }
        return ret;
    }

    private static void fillNum(PriorityQueue<Integer> min, PriorityQueue<Integer> max,
                                int num) {
        if (min.isEmpty() || num <= min.peek()) {
            min.offer(num);
            if (max.size() + 1 < min.size()) {
                max.offer(min.poll());
            }
        } else {
            max.offer(num);
            if (max.size() > min.size()) {
                min.offer(max.poll());
            }
        }
    }

    private static double mid(PriorityQueue<Integer> min, PriorityQueue<Integer> max, boolean odd) {
        if (odd) {
            return min.peek();
        } else {
            return (min.peek().longValue() + max.peek().longValue()) / 2.0;
        }
    }
}
