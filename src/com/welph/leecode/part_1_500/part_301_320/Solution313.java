package com.welph.leecode.part_1_500.part_301_320;

import java.util.Arrays;

/**
 * 编写一段程序来查找第 n 个超级丑数。
 * 超级丑数是指其所有质因数都是长度为k的质数列表primes中的正整数。
 * <p>
 * 示例:
 * 输入: n = 12, primes = [2,7,13,19]
 * 输出: 32
 * 解释: 给定长度为 4 的质数列表 primes = [2,7,13,19]，前 12
 * 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
 * 说明:
 * <p>
 * 1是任何给定primes的超级丑数。
 * 给定primes中的数字以升序排列。
 * 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000 。
 * 第n个超级丑数确保在 32 位有符整数范围内。
 */
public class Solution313 {

    public static void main(String[] args) {
        /*
         * int[] primes = {2, 7, 13, 19};
         * System.out.println(nthSuperUglyNumber(100, primes));
         */
        // 25
        /*
         * int[] primes1 = {3, 5, 7, 17, 19, 23, 29, 43, 47, 53};
         * System.out.println(nthSuperUglyNumber(25, primes1));
         */
        int[] primes2 = { 2 };
        System.out.println(nthSuperUglyNumber(2, primes2));
    }

    /*
     * {@link Solution263}
     * {@link Solution264}
     */
    // 这里相当于一遍遍计算 类似上面引用的两道题方法, 使用优先队列装载
    public static int nthSuperUglyNumber(int n, int[] primes) {
        if (n == 1) {
            return 1;
        }
        int[] res = new int[n];
        res[0] = 1;
        Heap heap = new Heap(primes.length);
        for (int prime : primes) {
            heap.push(new Entry(prime));
        }
        for (int i = 1; i < n; i++) {
            // 比较primes[size] 用heap存放最小值的乘积 每次哪个用到了就重新乘积放进去
            Entry pop = heap.pop();
            res[i] = pop.value;
            while (heap.size > 0 && heap.peek().value == pop.value) {
                Entry rePop = heap.pop();
                reCalculate(rePop, res, i);
                heap.push(rePop);
            }
            reCalculate(pop, res, i);
            heap.push(pop);
        }
        return res[n - 1];
    }

    private static void reCalculate(Entry pop, int[] res, int i) {
        if (pop.index == 0) {
            pop.index = i;
            pop.origin = res[i];
        } else {
            pop.index++;
        }
        // 若是计算的值存在,则说明重复, 需要重新计算
        pop.value = pop.origin * res[pop.index];
    }

    static class Heap {

        Entry[] values;
        int size = 0;
        int capacity;

        public Heap(int capacity) {
            this.capacity = capacity + 1;
            values = new Entry[this.capacity];
        }

        /**
         * 放入堆中
         */
        public void push(Entry value) {
            // 放到最后, 并不断变更
            values[++size] = value;
            // 自下向上
            int i = size;
            while (i / 2 > 0 && values[i].value < values[i / 2].value) {
                swap(values, i, i / 2);
                i = i / 2;
            }
        }

        private void swap(Entry[] heap, int i, int j) {
            Entry tmp = heap[i];
            heap[i] = heap[j];
            heap[j] = tmp;
        }

        public Entry peek() {
            return values[1];
        }

        public Entry pop() {
            // 自上而下
            Entry result = values[1];
            values[1] = values[size--];
            int i = 1;
            int min;
            while (i < capacity) {
                min = i;
                if (i * 2 <= size && values[i * 2].value < values[min].value) {
                    min = 2 * i;
                }
                if (i * 2 + 1 <= size && values[i * 2 + 1].value < values[min].value) {
                    min = 2 * i + 1;
                }
                if (min == i) {
                    break;
                }
                swap(values, i, min);
                i = min;
            }
            return result;
        }
    }

    static class Entry {
        public Entry(int value) {
            this.value = value;
        }

        int origin;
        int index;
        int value;
    }

    /* 官方题解 */
    // 动态规划
    public int nthSuperUglyNumber2(int n, int[] primes) {
        int[] dp = new int[n + 1];
        int m = primes.length;
        int[] pointers = new int[m];
        int[] nums = new int[m];
        Arrays.fill(nums, 1);
        for (int i = 1; i <= n; i++) {
            int minNum = Arrays.stream(nums).min().getAsInt();
            dp[i] = minNum;
            for (int j = 0; j < m; j++) {
                if (nums[j] == minNum) {
                    pointers[j]++;
                    nums[j] = dp[pointers[j]] * primes[j];
                }
            }
        }
        return dp[n];
    }

}
