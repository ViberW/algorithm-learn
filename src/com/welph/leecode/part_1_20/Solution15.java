package com.welph.leecode.part_1_20;

import java.util.*;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 *
 * @author: Admin
 * @date: 2019/5/15
 * @Description: {相关描述}
 */
public class Solution15 {

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));
        System.out.println(threeSum01(nums));
        //说明: 这块优先遍历是需要的,这样能减少>0的判断
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> had = new ArrayList<>();
        int index;
        int k;
        Map<Integer, Integer> map;
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (had.contains(nums[i])) {
                continue;
            }
            map = new HashMap<>();
            for (int j = i + 1; j < len; j++) {
                if (nums[j] >= 0 && map.containsKey(nums[j])) {
                    index = map.get(nums[j]);
                    if (index > 0) {
                        lists.add(Arrays.asList(nums[i], nums[index], nums[j]));
                        map.put(nums[j], -1);
                    }
                } else {
                    k = nums[i] + nums[j];
                    if (k > 0) {
                        continue;
                    }
                    map.put(-k, j);
                }
            }
            had.add(nums[i]);
        }
        return lists;
    }

    /**
     * 由双指针法引出的多指针,不断拆分成双指针判断
     */
    public static List<List<Integer>> threeSum01(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<>();
        int len = nums.length;
        int l;
        int r;
        int s;
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i == 0 || nums[i] > nums[i - 1]) {
                l = i + 1;
                r = len - 1;
                while (l < r) {
                    s = nums[i] + nums[l] + nums[r];
                    if (s == 0) {
                        lists.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        do {
                            l++;
                        } while (l < r && nums[l] == nums[l - 1]);
                        do {
                            r--;
                        }
                        while (r > l && nums[r] == nums[r + 1]);

                    } else if (s > 0) {
                        r--;
                    } else {
                        l++;
                    }
                }
            }
        }
        return lists;
    }
}
