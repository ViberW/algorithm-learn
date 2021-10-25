package com.welph.leecode.part_1_500.part_401_420;

/**
 * 给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。
 * <p>
 * 示例 1：
 * 输入：[3, 2, 1]
 * 输出：1
 * 解释：第三大的数是 1 。
 * <p>
 * 示例 2：
 * 输入：[1, 2]
 * 输出：2
 * 解释：第三大的数不存在, 所以返回最大的数 2 。
 * <p>
 * 示例 3：
 * 输入：[2, 2, 3, 1]
 * 输出：1
 * 解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
 * 此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。
 * <p>
 * 提示：
 * 1 <= nums.length <= 104
 * -2^31 <= nums[i] <= 2^31 - 1
 * 进阶：你能设计一个时间复杂度 O(n) 的解决方案吗？
 */
public class Solution414 {

    public static void main(String[] args) {
        System.out.println(thirdMax(new int[]{3, 2, 1, 2, 1, 0, -1}));
        System.out.println(thirdMax(new int[]{1, 2}));
        System.out.println(thirdMax(new int[]{2, 2, 3, 1}));
    }

    /**
     * 类似第k大数字, 这里为了方便就直接使用三个值来代表
     */
    public static int thirdMax(int[] nums) {
        int[] arr = new int[3];
        int size = 0;
        LABEL:
        for (int num : nums) {
            for (int i = 0; i < size; i++) {
                if (arr[i] == num) {
                    continue LABEL;
                } else if (arr[i] < num) {
                    int tmp = num;
                    num = arr[i];
                    arr[i] = tmp;
                }
            }
            if (size < 3) {
                arr[size] = num;
                size++;
            }
        }
        return size < 3 ? arr[0] : arr[2];
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
