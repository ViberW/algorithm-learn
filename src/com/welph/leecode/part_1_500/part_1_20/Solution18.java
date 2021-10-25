package com.welph.leecode.part_1_500.part_1_20;

import java.util.*;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * 注意：答案中不可以包含重复的四元组。
 * 示例：
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 *
 * @author: Admin
 * @date: 2019/5/16
 * @Description: {相关描述}
 */
public class Solution18 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, -2, -5, -4, -3, 3, 3, 5};
        System.out.println(fourSum(nums, -11));
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        int i;
        int l;
        int r;
        int k;
        int len = nums.length;
        int org, cur, calc;
        List<List<Integer>> list = new ArrayList<>();
        for (i = 0; i < len; i++) {
            org = target - nums[i];
            //变成了找出所有的等于 target-num[i]的对象
            if (i == 0 || nums[i] != nums[i - 1]) {
                for (k = i + 1; k < len; k++) {
                    if (k == i + 1 || nums[k] != nums[k - 1]) {
                        cur = org - nums[k];
                        l = k + 1;
                        r = len - 1;
                        while (l < r) {
                            calc = nums[l] + nums[r];
                            if (calc == cur) {
                                list.add(Arrays.asList(nums[i], nums[k], nums[l], nums[r]));
                                do {
                                    l++;
                                } while (l < r && nums[l] == nums[l - 1]);
                                do {
                                    r--;
                                } while (l < r && nums[r] == nums[r + 1]);
                            } else if (calc > cur) {
                                r--;
                            } else {
                                l++;
                            }
                        }
                    }
                }
            }
        }
        return list;
    }


    public List<List<Integer>> fourSum01(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        if (len < 4) return Collections.emptyList();
        int max = nums[len - 1];
        if (4 * max < target) return Collections.emptyList();
        return kSum(nums, 0, 4, target);
    }

    private List<List<Integer>> kSum(int[] nums, int start, int k, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (k == 2) {
            int left = start, right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    List<Integer> twoSum = new LinkedList<>();
                    twoSum.add(nums[left]);
                    twoSum.add(nums[right]);
                    res.add(twoSum);
                    while (nums[left] == nums[++left] && left < right) ;
                    while (nums[right] == nums[--right] && left < right) ;
                } else if (sum < target) ++left;
                else --right;
            }
        } else {
            int i = start, end = nums.length - (k - 1), max = nums[nums.length - 1];
            while (i < end) {
                if (nums[i] * k > target) return res;
                if (nums[i] + (k - 1) * max < target) {
                    while (nums[i] == nums[++i] && i < end) ;
                    continue;
                }
                List<List<Integer>> temp = kSum(nums, i + 1, k - 1, target - nums[i]);
                for (List<Integer> t : temp) {
                    t.add(0, nums[i]);
                }
                res.addAll(temp);
                while (nums[i] == nums[++i] && i < end) ;
            }
        }
        return res;
    }
}
