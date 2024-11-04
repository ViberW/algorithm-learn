package com.welph.leecode.algorithm.z7z8;

import java.util.Arrays;

/**
 * 现在有一个2n服务器数组, 需要两两关闭,
 * 若在一对中, [d1, d2] 若关闭较小值,则cost+1, 关闭较大值则不变
 * <p>
 * 此时关闭后的剩余综合sum>=k且cost最小, 该如何关闭?
 */
public class CloseServer {

    public static void main(String[] args) {
        /*int[] data = {1, 2, 3, 5, 7, 8};
        int k = 14;
        Arrays.sort(data);
        int cost = 0;
        int last = 0;
        int[] fn = new int[data.length + 1];
        for (int i = 1; i < data.length; i++) {
            fn[i + 1] = fn[i - 1] + data[i - 1];
        }
        //可以使用二分法优化, 从cost=0 到cost=length/2
        for (; ; ) {
            //默认情况下,排序后相邻两两一组,关闭较大值, 则代表cost为0时,能够获取到的最大总和
            //若是当前cost不满足,则将第一个和最后一个为一组(关闭最小值,cost+1), 中间的还是相邻为一组, 获取能够最大总和, 不断判断
            int sum = last + fn[data.length - cost] - fn[cost];
            if (sum >= k) {
                System.out.println(cost);
                return;
            }
            cost++;
            last += data[data.length - cost];
        }*/
        int[] data = {1, 2, 3, 5, 7, 8};
        int k = 14;
        Arrays.sort(data);
        int[] fn = new int[data.length + 1];
        for (int i = 1; i < data.length; i++) {
            fn[i + 1] = fn[i - 1] + data[i - 1];
        }
        int l = 0, r = data.length / 2;
        int mid;
        while (l <= r) {
            mid = (l + r) / 2;
            int sum = fn[data.length - mid] - fn[mid];
            for (int i = data.length - mid; i < data.length; i++) {
                sum += data[i];
            }
            if (sum >= k) {
                r--;
            } else {
                l++;
            }
        }
        System.out.println(l);
        binary();
    }

    private static void binary() {
        int[] data = {1, 2, 3, 5, 7, 8};
        int k = 14;
        Arrays.sort(data);
        int[] fn = new int[data.length + 1];
        for (int i = 1; i < data.length; i++) { //以i为结尾的区间和
            fn[i + 1] = fn[i - 1] + data[i - 1];

        }
        int[] last = new int[data.length / 2 + 1]; //后缀和
        for (int i = 1; i < last.length; i++) {
            last[i] = last[i - 1] + data[data.length - i];
        }
        int l = 0, r = data.length / 2;
        int mid;
        while (l <= r) {
            mid = (l + r) / 2;
            if (fn[data.length - mid] - fn[mid] + last[mid] >= k) {
                r--;
            } else {
                l++;
            }
        }
        System.out.println(l);
    }

}
