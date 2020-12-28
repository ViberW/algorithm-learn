package com.welph.leecode.part_161_180;

import com.welph.leecode.algorithm.sort.nlog_n.QuickSort;

import java.util.Arrays;

/**
 * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
 * 如果数组元素个数小于 2，则返回 0。
 * <p>
 * 示例 1:
 * 输入: [3,6,9,1]
 * 输出: 3
 * 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
 * <p>
 * 示例 2:
 * 输入: [10]
 * 输出: 0
 * 解释: 数组元素个数小于 2，因此返回 0。
 * 说明:
 * 你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
 * 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
 */
public class Solution164 {

    public static void main(String[] args) {
        int[] nums = {3, 6, 9, 1};
        System.out.println(maximumGap(nums));
        int[] nums1 = {2, 99999999};
        System.out.println(maximumGap(nums1));
        int[] nums2 = {1, 1, 1, 1};
        System.out.println(maximumGap(nums2));
        int[] nums3 = {15252, 16764, 27963, 7817, 26155, 20757, 3478, 22602, 20404, 6739, 16790, 10588, 16521, 6644, 20880, 15632, 27078, 25463, 20124, 15728, 30042, 16604, 17223, 4388, 23646, 32683, 23688, 12439, 30630, 3895, 7926, 22101, 32406, 21540, 31799, 3768, 26679, 21799, 23740};
        System.out.println(maximumGap(nums3));
    }

    //时间 42.15%  空间 5.23% -_-
    //尝试在线性时间和空间复杂度下解决
    // 桶排序 + 归并排序(找出当前桶的最大Gap)
    public static int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        int min;
        int max;
        max = min = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        int bucketSize = Math.max(4, (max - min) / 15);
        int bucketCount = (max - min) / bucketSize + 1;

        int[][] buckets = new int[bucketCount][0];

        for (int i = 0; i < length; i++) {
            int k = (nums[i] - min) / bucketSize;
            buckets[k] = arrAppend(buckets[k], nums[i]);
        }

        int maxGap = 0;
        int lastVal = nums[0];
        for (int i = 0; i < bucketCount; i++) {
            if (buckets[i].length == 0) {
                continue;
            }
            int[] bucket = buckets[i];
            int sortGap = sort(bucket);
            maxGap = Math.max(maxGap, Math.max(sortGap, bucket[0] - lastVal));
            lastVal = bucket[bucket.length - 1];
        }
        return maxGap;
    }

    private static int sort(int[] bucket) {
        //想办法排序 nlogn的排序 并能够返回相差的值,又避免使用额外空间 --快排
        return sort(bucket, 0, bucket.length - 1);
    }

    private static int sort(int[] bucket, int l, int r) {
        if (l < r) {
            int pivot = partition(bucket, l, r);
            int max = Math.max(sort(bucket, l, pivot - 1), sort(bucket, pivot + 1, r));

            if (pivot > l) {
                max = Math.max(max, bucket[pivot] - bucket[pivot - 1]);
            }
            if (pivot < r) {
                max = Math.max(max, bucket[pivot + 1] - bucket[pivot]);
            }
            return max;
        } else {
            return 0;
        }
    }

    private static int partition(int[] bucket, int l, int r) {
        int pivot = bucket[r];
        int target = l;
        for (int i = l; i < r; i++) {
            if (bucket[i] < pivot) {
                swap(bucket, i, target);
                target++;
            }
        }
        swap(bucket, r, target);
        return target;
    }

    private static void swap(int[] bucket, int i, int target) {
        if (i == target) {
            return;
        }
        int tmp = bucket[i];
        bucket[i] = bucket[target];
        bucket[target] = tmp;
    }


    private static int[] arrAppend(int[] bucket, int num) {
        bucket = Arrays.copyOf(bucket, bucket.length + 1);
        bucket[bucket.length - 1] = num;
        return bucket;
    }
}
