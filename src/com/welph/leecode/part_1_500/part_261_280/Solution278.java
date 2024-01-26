package com.welph.leecode.part_1_500.part_261_280;

/**
 * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。
 * 由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
 * <p>
 * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
 * 你可以通过调用bool isBadVersion(version)接口来判断版本号 version 是否在单元测试中出错。
 * 实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
 * <p>
 * 示例:
 * 给定 n = 5，并且 version = 4 是第一个错误的版本。
 * 调用 isBadVersion(3) -> false
 * 调用 isBadVersion(5)-> true
 * 调用 isBadVersion(4)-> true
 * 所以，4 是第一个错误的版本。
 */
public class Solution278 {

    public static void main(String[] args) {
        // 2126753390
        // 1702766719
        System.out.println(firstBadVersion(2126753390));
        System.out.println(firstBadVersion(8));
    }

    // 二分法
    public static int firstBadVersion(int n) {
        int l = 1;
        int r = n;
        int mid;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (isBadVersion(mid)) {
                /*
                 * if (mid > 1 && !isBadVersion(mid - 1)) {
                 * return mid;
                 * }
                 */
                r = mid - 1;
            } else {
                /*
                 * if (mid < n && isBadVersion(mid + 1)) {
                 * return mid + 1;
                 * }
                 */
                l = mid + 1;
            }
        }
        return l + (r - l) / 2;
    }

    // 这是个假设方法, 用于测试
    public static boolean isBadVersion(int n) {
        return n >= 4;
    }

    // 这种写法更好些
    public int firstBadVersion2(int n) {
        int left = 1, right = n;
        while (left < right) { // 循环直至区间左右端点相同
            int mid = left + (right - left) / 2; // 防止计算时溢出
            if (isBadVersion(mid)) {
                right = mid; // 答案在区间 [left, mid] 中
            } else {
                left = mid + 1; // 答案在区间 [mid+1, right] 中
            }
        }
        // 此时有 left == right，区间缩为一个点，即为答案
        return left;
    }

}
