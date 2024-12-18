package com.welph.leecode.algorithm.marscode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 小U有一个数组，她可以通过选择一个元素并将其除以2（向下取整）的操作来改变数组。
 *
 * 小U想知道，最少需要多少次这样的操作才能使得所有数组元素相等。
 */
public class Divide2IntoEqualArray {

    //二进制?
    public static int solution(int n, int[] a) {
        //问题回到了如何寻找到相同的二进制前缀
        int prefix = a[0];
        int[] bits = new int[32 - Integer.numberOfLeadingZeros(a[0])];
        int size = bits.length - 1;
        for (int i = size; i >= 0; i--) {
            bits[i] = prefix & 1;
            prefix >>= 1;
        }
        for (int i = 1; i < n; i++) {
            int val = a[i];
            int bit = 31 - Integer.numberOfLeadingZeros(val);
            size = Math.min(size, bit);
            int j = 0;
            for (; j <= size; j++) {
                if ((val >> (bit - j) & 1) != bits[j]) {
                    size = j - 1;
                    break;
                }
            }
        }
        size++;
        //说明又共同的前缀且长度为size
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += 32 - Integer.numberOfLeadingZeros(a[i]) - size;
        }
        return result;
    }

    //效率不高
    public static int solution1(int n, int[] a) {
        //bfs
        //倒叙排列
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(v -> -v));
        int target = a[0];
        for (int i = 0; i < n; i++) {
            queue.add(a[i]);
            if (a[i] < target) {
                target = a[i];
            }
        }
        int result = 0;
        int val;
        while (target != (val = queue.poll())) {
            while (val > target) {
                val >>= 1;
                result++;
            }
            if (val < target) {
                target = val;
            }
            queue.add(val);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(4, new int[]{1, 2, 1, 3}) == 2);
        System.out.println(solution(1, new int[]{114514}) == 0);
        System.out.println(solution(5, new int[]{16, 8, 4, 2, 1}) == 10);
        System.out.println(solution(5, new int[]{12, 17, 10, 5, 11}) == 15);
    }
}
