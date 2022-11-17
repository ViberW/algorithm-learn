package com.welph.leecode.algorithm.binaryquery;

/**
 * 二分查找 `变种`
 */
public class BinaryQuery {

    public static void main(String[] args) {
        int[] arr = {1, 3, 3, 3, 3, 5, 6};
        System.out.println(firstEqual(arr, 3));
        System.out.println(lastEqual(arr, 3));
        System.out.println(firstGreate(arr, 4));
        System.out.println(lastGreate(arr, 2));
    }

    //第一个相等
    public static int firstEqual(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;
        int mid;
        int index = -1;
        while (l <= r) {
            mid = l + ((r - l) >> 1);
            if (arr[mid] > target) {
                r = mid - 1;
            } else if (arr[mid] < target) {
                l = mid + 1;
            } else {
                //因为是找到第一个相等的,所以尝试往前找
                index = mid;
                r = mid - 1;
            }
        }
        return index;
    }

    //第一个相最后一个相等
    public static int lastEqual(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;
        int mid;
        int index = -1;
        while (l <= r) {
            mid = l + ((r - l) >> 1);
            if (arr[mid] > target) {
                r = mid - 1;
            } else if (arr[mid] < target) {
                l = mid + 1;
            } else {
                //因为是找到最后一个相等的,所以尝试往前找
                index = mid;
                l = mid + 1;
            }
        }
        return index;
    }

    //第一个大于等于
    public static int firstGreate(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;
        int mid;
        while (l < r) {
            mid = (l + r - 1) / 2;
            if (arr[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }

    //最后一个大于等于
    public static int lastGreate(int[] arr, int target) {
        int mid;
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            mid = (l + r + 1) / 2;
            if (arr[mid] <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
}
