package com.welph.leecode.part_1_500.part_1_20;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * @author: Admin
 * @date: 2019/5/5
 * @Description: {相关描述}
 */
public class Solution04 {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4,5,6,7,8,9};
        System.out.println(findMedianSortedArrays(nums1, nums2));
        System.out.println(findMedianSortedArrays01(nums1,nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //需要将两个数组顺序合并 之后按照奇偶数不同获取,  到达中间时候就可以准备跳出循环了
        int len1 = nums1.length;
        int len2 = nums2.length;
        int mid = (len1 + len2) / 2;
        int max = mid;
        if ((len1 + len2) % 2 == 0) {
            mid = mid - 1;
        }
        int cur = 0;
        int i = 0, j = 0;
        double result = 0D;
        //O(n+m/2)
        while (true) {
            if (i >= len1) {
                if (cur >= mid) {
                    result += nums2[j];
                }
                j++;
            } else if (j >= len2) {
                if (cur >= mid) {
                    result += nums1[i];
                }
                i++;
            } else if (nums1[i] <= nums2[j]) {
                if (cur >= mid) {
                    result += nums1[i];
                }
                i++;
            } else {
                if (cur >= mid) {
                    result += nums2[j];
                }
                j++;
            }
            cur++;
            if (cur > max) {
                break;
            }
        }
        return result / ((len1 + len2) % 2 == 0 ? 2 : 1);
    }

    //todo 节省循环次数 O(logn+m)
    public static double findMedianSortedArrays01(int[] A, int[] B) {
        //基于中位切分,中位数始终是左右两个part的最大值和最小值的整合数
        int m = A.length;
        int n = B.length;
        if (m > n) {
            //始终保证n>=m
            int[] temp = A;
            A = B;
            B = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            //以小队列作为基准
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            System.out.println(iMin+"=="+iMax+"==="+i+"=-=="+j);
            if (i < iMax && B[j - 1] > A[i]) {
                //右边 一半 大于 左边 一半
                //最小值移动到 中间  提高值的范围
                iMin = i + 1;
            } else if (i > iMin && A[i - 1] > B[j]) {
                //右边 一半 小于 左边 一半
                //将最大值 移动到中间, 降低值的范围
                iMax = i - 1;
            } else {
                //要么一开始在边界 要么已经趋近于中间位置了
                int maxLeft = 0;
                if (i == 0) {
                    //左边界
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    //右边界
                    maxLeft = A[i - 1];
                } else {
                    //
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    //说明是奇数 则取一个就ok
                    return maxLeft;
                }
                //说明是偶数,则向右再取一个
                int minRight = 0;
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}
