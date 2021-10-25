package com.welph.leecode.part_1_500.part_41_60;

import java.util.LinkedList;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * <p>
 */
public class Solution45 {

    public static void main(String[] args) {
        int[] nums = {8, 2, 4, 4, 4, 9, 5, 2, 5, 8, 8, 0, 8, 6, 9, 1, 1, 6, 3, 5, 1, 2, 6, 6, 0, 4, 8, 6, 0, 3, 2, 8, 7, 6, 5, 1, 7, 0, 3, 4, 8, 3, 5, 9, 0, 4, 0, 1, 0, 5, 9, 2, 0, 7, 0, 2, 1, 0, 8, 2, 5, 1, 2, 3, 9, 7, 4, 7, 0, 0, 1, 8, 5, 6, 7, 5, 1, 9, 9, 3, 5, 0, 7, 5};
        //int[] nums = {0};
        System.out.println(jump2(nums));
    }

    /**
     * 执行时间较长，因为每一步尽可能的跳向所有位置了，
     *
     * @param nums
     * @return
     */
    public static int jump1(int[] nums) {
        /**
         * 为了解决下面的重复循环，维护一个数组，保存当前点是否有到达
         */
        int length = nums.length;
        boolean[] steps = new boolean[length];
        int step = 0;
        int oldQueue = 1;
        int currQueue;
        Integer pop;
        int num;
        int i;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.push(0);
        while (step < length) {
            step++;
            currQueue = 0;
            while (oldQueue-- > 0) {
                pop = queue.pop();
                num = nums[pop];
                i = length - 1 - pop;
                while (num > 0) {
                    if (num == i) {
                        return step;
                    } else if (num < i) {
                        if (!steps[pop + num]) {
                            queue.addLast(pop + num);
                            steps[pop + num] = true;
                            currQueue++;
                        }
                    }
                    num--;
                }
            }
            oldQueue = currQueue;
        }
        return 0;
    }

    /**
     * 太慢了 >_<，其中包含的0应该去除掉，上面方法一解决
     *
     * @param nums
     * @return
     */
    public static int jump(int[] nums) {
        int length = nums.length;
        int step = 0;
        int oldQueue = 1;
        int currQueue;
        Integer pop;
        int num;
        int i;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.push(0);
        while (step < length) {
            currQueue = 0;
            while (oldQueue-- > 0) {
                pop = queue.pop();
                num = nums[pop];
                i = length - 1 - pop;
                while (num > 0) {
                    if (num == i) {
                        return ++step;
                    } else if (num < i) {
                        queue.addLast(pop + num);
                        currQueue++;
                    }
                    num--;
                }
            }
            oldQueue = currQueue;
            step++;
        }
        return 0;
    }

    ////////贪心算法下面///////////

    /**
     * 依靠讨论的贪心算法，每一步尽可能到达最远的位置
     *
     * @param nums
     */
    public static int jump2(int[] nums) {
        int step = 0;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            //找能跳的最远的
            maxPosition = Math.max(maxPosition, nums[i] + i);
            if (i == end) { //遇到边界，就更新边界，并且步数加一
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    /**
     * 从后往前找，反向查找
     *
     * @param nums
     * @return
     */
    public int jump4(int[] nums) {
        int position = nums.length - 1; //要找的位置
        int steps = 0;
        while (position != 0) { //是否到了第 0 个位置
            for (int i = 0; i < position; i++) {
                if (nums[i] >= position - i) {
                    position = i; //更新要找的位置
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }
}
