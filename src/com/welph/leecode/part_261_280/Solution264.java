package com.welph.leecode.part_261_280;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * 编写一个程序，找出第 n 个丑数。
 * 丑数就是质因数只包含 2, 3, 5 的正整数。
 * <p>
 * 示例:
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * <p>
 * 说明:
 * 1 是丑数。
 * n 不超过1690。
 */
public class Solution264 {

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(10));
    }

    static Ugly1 ugly1 = new Ugly1();

    //动态规划  todo 一开始没想好 之后动态规划可以了
    public static int nthUglyNumber1(int n) {
        return ugly1.nums[n - 1];
    }

    static class Ugly1 {
        int[] nums = new int[1690];

        public Ugly1() {
            nums[0] = 1;
            int ugly, i2 = 0, i3 = 0, i5 = 0;
            for (int i = 1; i < 1690; ++i) {
                ugly = Math.min(Math.min(nums[i2] * 2, nums[i3] * 3), nums[i5] * 5);
                nums[i] = ugly;
                //每次选取最小值进行乘积
                if (ugly == nums[i2] * 2) ++i2;
                if (ugly == nums[i3] * 3) ++i3;
                if (ugly == nums[i5] * 5) ++i5;
            }
        }
    }


    static Ugly ugly = new Ugly();

    public static int nthUglyNumber(int n) {
        return ugly.nums[n - 1];
    }

    /**
     * 这种方法耗时较长
     */
    static class Ugly {
        int[] nums = new int[1690];

        public Ugly() {
            HashSet<Long> set = new HashSet<>();
            PriorityQueue<Long> queue = new PriorityQueue<>();
            set.add(1L);
            queue.add(1L);
            long currUgly, newUgly;
            int[] primes = new int[]{2, 3, 5};
            for (int i = 0; i < 1690; i++) {
                currUgly = queue.poll();
                nums[i] = (int) currUgly;
                for (int prime : primes) {
                    newUgly = currUgly * prime;
                    if (!set.contains(newUgly)) {
                        set.add(newUgly);
                        queue.add(newUgly);
                    }
                }
            }
        }
    }
}
