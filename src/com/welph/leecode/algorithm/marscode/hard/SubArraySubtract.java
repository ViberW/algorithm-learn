package com.welph.leecode.algorithm.marscode.hard;

import java.util.Arrays;

/**
 * 小C有一个整数数组 nums 和一个整数 k。
 * 他希望将数组 nums 划分为 k 个相同大小的子集，使得每个子集中的元素互不相同。
 *
 * 每个子集的极差定义为该子集中的最大值与最小值的差。
 * 小C想知道，将数组划分成 k 个子集后，各子集极差之和的最小值是多少。
 *
 * 如果无法划分成 k 个子集，返回 -1。
 *
 * 子集的定义是数组中某些数字的集合，集合中的元素顺序无关紧要。
 */
public class SubArraySubtract {

    //对solution1的部分情况的回溯
    public static int solution(int[] nums, int k) {
        if (nums.length == 0 || nums.length % k != 0) {
            return -1;
        }
        Arrays.sort(nums);
        int[][] state = new int[k][3];
        if (!recursion(nums, state, k, nums.length / k, 0, 0)) {
            return -1;
        }
        int result = 0;
        for (int[] ints : state) {
            result += ints[2] - ints[1];
        }
        return result;
    }

    private static boolean recursion(int[] nums, int[][] state, int k, int size, int index, int i) {
        if (i >= nums.length) {
            return true;
        }
        if (index >= k) {
            return false;
        }
        if (state[index][0] == 0 || (state[index][0] < size && nums[i] != state[index][2])) {
            state[index][0]++;
            if (state[index][0] == 1) {
                state[index][1] = nums[i];
            }
            int prev = state[index][2];
            state[index][2] = nums[i];
            if (recursion(nums, state, k, size, 0, i + 1)) {
                return true;
            }
            state[index][0]--;
            state[index][2] = prev;
        }
        //要么和前一值相等 要么是前一个已经满了 跳过
        return recursion(nums, state, k, size, index + 1, i);
    }

    @Deprecated
    public static int solution1(int[] nums, int k) {
        int n = nums.length;
        if (n % k != 0) {
            return -1;
        }
        int sub = n / k;
        //就是进行填充, 优先填满, 若是某个数字重复, 则填充到下一个
        Arrays.sort(nums);
        int[][] state = new int[k][3]; //[0]长度, 最小值. 最大值
        int notFull = 0;
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                index++;
                if (index >= k) { //不能这么处理,否则会有问题 , 因为[11,12,4,4,7,12,6,6,4,7] 其实最后的12能放到其他地方.
                    return -1;
                }
            } else {
                index = notFull;
            }
            state[index][0]++;
            if (state[index][0] == 1) {
                state[index][1] = nums[i];
            }
            state[index][2] = nums[i];
            if (state[index][0] == sub) {
                notFull++;
            }
        }
        int result = 0;
        for (int[] ints : state) {
            result += ints[2] - ints[1];
        }
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(solution(new int[]{1, 2, 1, 4}, 2) == 4);
//        System.out.println(solution(new int[]{6, 3, 8, 1, 3, 1, 2, 7}, 4) == 7);
//        System.out.println(solution(new int[]{1, 2, 3, 4, 5, 6}, 3) == 3);
//        System.out.println(solution(new int[]{13, 7, 2, 9, 6, 4, 3, 9}, 8));
        System.out.println(solution(new int[]{11, 12, 4, 4, 7, 12, 6, 6, 4, 7}, 5));
        System.out.println(solution(new int[]{4, 11, 6, 11, 10, 3, 14, 12, 6, 9, 13, 14, 9}, 1));
    }
}
