package com.welph.leecode.part_221_240;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个无重复元素的有序整数数组 nums 。
 * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表。也就是说，
 * nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
 * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
 * "a->b" ，如果 a != b
 * "a" ，如果 a == b
 * <p>
 * 示例 1：
 * 输入：nums = [0,1,2,4,5,7]
 * 输出：["0->2","4->5","7"]
 * 解释：区间范围是：
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 * <p>
 * 示例 2：
 * 输入：nums = [0,2,3,4,6,8,9]
 * 输出：["0","2->4","6","8->9"]
 * 解释：区间范围是：
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 * <p>
 * 示例 3：
 * 输入：nums = []
 * 输出：[]
 * <p>
 * 示例 4：
 * 输入：nums = [-1]
 * 输出：["-1"]
 * <p>
 * 示例 5：
 * 输入：nums = [0]
 * 输出：["0"]
 * <p>
 * 提示：
 * 0 <= nums.length <= 20
 * -231 <= nums[i] <= 231 - 1
 * nums 中的所有值都 互不相同
 * nums 按升序排列
 */
public class Solution228 {

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 4, 5, 7};
        System.out.println(summaryRanges(nums));
        int[] nums1 = {0, 2, 3, 4, 6, 8, 9};
        System.out.println(summaryRanges(nums1));
        int[] nums2 = {-1};
        System.out.println(summaryRanges(nums2));
    }

    public static List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        int length = nums.length;
        if (length > 0) {
            int preIndex = 0;
            for (int i = 1; i < length; i++) {
                if (nums[i] != nums[i - 1] + 1) {
                    result.add(getResult(preIndex, nums, i - 1));
                    preIndex = i;
                }
            }
            result.add(getResult(preIndex, nums, length - 1));
        }
        return result;
    }

    public static String getResult(int preIndex, int[] nums, int i) {
        if (preIndex == i) {
            return nums[preIndex] + "";
        } else {
            return nums[preIndex] + "->" + nums[i];
        }
    }
}
