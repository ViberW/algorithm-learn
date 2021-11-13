package com.welph.leecode.part_1_500.part_441_460;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
 * 找到所有出现两次的元素。
 * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
 * <p>
 * 示例：
 * 输入:
 * [4,3,2,7,8,2,3,1]
 * <p>
 * 输出:
 * [2,3]
 */
public class Solution442 {

    public static void main(String[] args) {
        System.out.println(findDuplicates(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }

    /**
     * {@link com.welph.leecode.part_1_500.part_121_140.Solution136}
     * 1 ≤ a[i] ≤ n （n为数组长度）
     * ------------------这里可以使用哈希表,范更加好的方法就是, 按照a[i]存放到对应的位置上去
     */
    public static List<Integer> findDuplicates(int[] nums) {
        int len = nums.length;
        List<Integer> ret = new ArrayList<>();
        int cur;
        for (int i = 1; i <= len; ) {
            cur = nums[i - 1];
            if (cur != i) {
                if (nums[cur - 1] != cur) {
                    swap(nums, cur - 1, i - 1);
                    if (nums[i - 1] == -1) {
                        i++;
                    }
                } else {
                    ret.add(cur);
                    nums[i - 1] = -1;
                    i++;
                }
            } else {
                i++;
            }
        }
        return ret;
    }

    private static void swap(int[] nums, int cur, int i) {
        int temp = nums[i];
        nums[i] = nums[cur];
        nums[cur] = temp;
    }
}
