package com.welph.leecode.part_1_500.part_161_180;

/**
 * 给定一组非负整数 nums，重新排列它们每个数字的顺序（每个数字不可拆分）使之组成一个最大的整数。
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * <p>
 * 示例 1：
 * 输入：nums = [10,2]
 * 输出："210"
 * <p>
 * 示例 2：
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 * <p>
 * 示例 3：
 * 输入：nums = [1]
 * 输出："1"
 * <p>
 * 示例 4：
 * 输入：nums = [10]
 * 输出："10"
 * <p>
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 109
 */
public class Solution179 {

    public static void main(String[] args) {
        int[] nums = {3, 30, 34, 5, 9};
        System.out.println(largestNumber(nums));
    }

    //按照字母的前后顺序添加. 长度越短, 比较时越大
    public static String largestNumber(int[] nums) {
        sort(nums, 0, nums.length - 1);
        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(num);
        }
        int i = 0;
        int length = sb.length();
        while (i < length && sb.charAt(i) == '0') {
            i++;
        }
        return i == length ? "0" : sb.substring(i);
    }

    private static void sort(int[] nums, int l, int r) {
        if (l < r) {
            int pivot = partition(nums, l, r);
            sort(nums, l, pivot - 1);
            sort(nums, pivot + 1, r);
        }
    }

    private static int partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        int target = l;
        for (int i = l; i < r; i++) {
            if (compare(nums[i], pivot)) {
                swap(nums, i, target);
                target++;
            }
        }
        swap(nums, r, target);
        return target;
    }

    private static boolean compare(int num, int pivot) {
        //这里也可以先转化为string, 根据index比较, 就不用了太多的空间了
        return new StringBuilder().append(num).append(pivot).toString()
                .compareTo(new StringBuilder().append(pivot).append(num).toString()) >= 0;
    }

    private static void swap(int[] nums, int l, int target) {
        if (l == target) {
            return;
        }
        int tmp = nums[l];
        nums[l] = nums[target];
        nums[target] = tmp;
    }
}
