package com.welph.leecode.part_1_500.part_321_340;

import java.util.Arrays;
import java.util.Random;

/**
 * 给你一个整数数组nums，将它重新排列成nums[0] < nums[1] > nums[2] < nums[3]...的顺序。
 * 你可以假设所有输入数组都可以得到满足题目要求的结果。
 * <p>
 * 示例 1：
 * 输入：nums = [1,5,1,1,6,4]
 * 输出：[1,6,1,5,1,4]
 * 解释：[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受。
 * <p>
 * 示例 2：
 * 输入：nums = [1,3,2,2,3,1]
 * 输出：[2,3,1,3,1,2]
 * <p>
 * 提示：
 * 1 <= nums.length <= 5 * 104
 * 0 <= nums[i] <= 5000
 * 题目数据保证，对于给定的输入 nums ，总能产生满足题目要求的结果
 * <p>
 * 进阶：你能用O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
 */
public class Solution324 {

    public static void main(String[] args) {
        /*
         * int[] nums = {1, 5, 1, 1, 6, 4, 7, 3, 2, 2, 4, 7};
         * wiggleSort(nums);
         * System.out.println(Arrays.toString(nums));
         * int[] nums1 = {1, 1, 2, 1, 2, 2, 1};
         * wiggleSort(nums1);
         * System.out.println(Arrays.toString(nums1));
         */
        /*
         * int[] nums2 = {4, 5, 5, 6};
         * wiggleSort(nums2);
         * System.out.println(Arrays.toString(nums2));
         */
        int[] nums3 = { 5, 3, 1, 2, 6, 7, 8, 5, 5 };
        wiggleSort1(nums3);
        System.out.println(Arrays.toString(nums3));
    }

    // 先把排序加合并的写下吧
    public static void wiggleSort1(int[] nums) {
        Arrays.sort(nums);
        reverse(nums, 0, nums.length - 1);
        int mid = nums.length / 2;
        int[] newest = new int[nums.length];
        int j = mid;
        int k = 0;
        for (int i = 0; i < mid; i++, j++) {
            newest[k++] = nums[j];
            newest[k++] = nums[i];
        }
        if (j < nums.length) {
            newest[k] = nums[j];
        }
        System.arraycopy(newest, 0, nums, 0, nums.length);
    }

    private static void reverse(int[] nums, int l, int r) {
        int mid = (l + r) / 2;
        for (; l <= mid; l++, r--) {
            swap(nums, l, r);
        }
    }

    // 排序, 分成两边. 随后一个升序 一个降序空间内 再合并? 但空间不是O(1)
    // 选择一个点,将快排分成两边. 仅仅需要保证能够找到一个中点, 并不要求严格排序,仅仅需要知道中间点
    // ------------好像还是要排序的好, 否则会导致-todo h还是没想好怎么处理O(1)及时间复杂度O(N)定位问题
    public static void wiggleSort(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        int target = (l + r) / 2;
        while (l < r) {
            int pivot = partition(nums, l, r);
            // 看pivot是否在中间位置.
            if (pivot == target) {
                break;
            } else if (pivot > target) {
                r = pivot - 1;
            } else {
                l = pivot + 1;
            }
        }
        // 已经是倒序了.[target,len);[0,target);
        // todo ....
    }

    private static int partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        int target = l;
        for (int i = l; i < r; i++) {
            if (nums[i] > pivot) {
                swap(nums, target, i);
                target++;
            }
        }
        swap(nums, r, target);
        return target;
    }

    private static void swap(int[] nums, int i, int k) {
        int tmp = nums[k];
        nums[k] = nums[i];
        nums[i] = tmp;
    }

    /* 官方题解 */
    // 排序思想. 相较于我的思路 这里更好看, 也不需要翻转数组
    public void wiggleSort3(int[] nums) {
        int[] arr = nums.clone();
        Arrays.sort(arr);
        int n = nums.length;
        int x = (n + 1) / 2;
        for (int i = 0, j = x - 1, k = n - 1; i < n; i += 2, j--, k--) {
            nums[i] = arr[j];
            if (i + 1 < n) {
                nums[i + 1] = arr[k];
            }
        }
    }

    //todo 还有三种官方解法......
    
    //三向切分
    Random random = new Random();

    public void wiggleSort4(int[] nums) {
        int n = nums.length;
        int x = (n + 1) / 2;
        int mid = x - 1;
        int target = findKthLargest(nums, n - mid);
        for (int k = 0, i = 0, j = n - 1; k <= j; k++) {
            if (nums[k] > target) {
                while (j > k && nums[j] > target) {
                    j--;
                }
                swap(nums, k, j--);
            }
            if (nums[k] < target) {
                swap(nums, k, i++);
            }
        }
        int[] arr = nums.clone();
        for (int i = 0, j = x - 1, k = n - 1; i < n; i += 2, j--, k--) {
            nums[i] = arr[j];
            if (i + 1 < n) {
                nums[i + 1] = arr[k];
            }
        }
    }

    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    public int quickSelect(int[] a, int l, int r, int index) {
        int q = randomPartition(a, l, r);
        if (q == index) {
            return a[q];
        } else {
            return q < index ? quickSelect(a, q + 1, r, index) : quickSelect(a, l, q - 1, index);
        }
    }

    public int randomPartition(int[] a, int l, int r) {
        int i = random.nextInt(r - l + 1) + l;
        swap(a, i, r);
        return partition(a, l, r);
    }


}
