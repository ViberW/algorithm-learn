package com.welph.leecode.part_500_1000.part_641_660;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个 排序好 的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。
 * 返回的结果必须要是按升序排好的。
 * <p>
 * 整数 a 比整数 b 更接近 x 需要满足：
 * |a - x| < |b - x| 或者
 * |a - x| == |b - x| 且 a < b
 * <p>
 * 示例 1：
 * 输入：arr = [1,2,3,4,5], k = 4, x = 3
 * 输出：[1,2,3,4]
 * <p>
 * 示例 2：
 * 输入：arr = [1,2,3,4,5], k = 4, x = -1
 * 输出：[1,2,3,4]
 * <p>
 * 提示：
 * 1 <= k <= arr.length
 * 1 <= arr.length <= 10^4
 * arr 按 升序 排列
 * -10^4 <= arr[i], x <= 10^4
 */
public class Solution658 {

    public static void main(String[] args) {
        System.out.println(findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, 3));
        System.out.println(findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, -1));
        System.out.println(findClosestElements(new int[]{0, 0, 1, 2, 3, 3, 4, 7, 7, 8}, 3, 5));
    }

    /**
     * 已经排好序了..
     * {@link com.welph.leecode.part_1_500.part_21_40.Solution21}
     */
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        //首先通过二分法找到最接近的数值
        int mid;
        int n = arr.length;
        int l = 0;
        int r = n - 1;
        while (l < r) {//找到第一个大于
            mid = (l + r - 1) / 2;
            if (arr[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        l = r - 1;
        int count = 0;
        //从中心点 .两分 分别找到前K个数值
        while (count < k && l >= 0 && r < n) {
            if (x - arr[l] > arr[r] - x) {
                r++;
            } else {
                l--;
            }
            count++;
        }
        if (count < k) {
            if (l >= 0) {
                while (count < k) {
                    l--;
                    count++;
                }
            } else {
                while (count < k) {
                    r++;
                    count++;
                }
            }
        }
        r = Math.min(n, r);
        List<Integer> ret = new ArrayList<>();
        for (int i = l < 0 ? 0 : l + 1; i < r; i++) {
            ret.add(arr[i]);
        }
        return ret;
    }
}
