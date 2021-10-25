package com.welph.leecode.part_1_500.part_301_320;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质：
 * counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 * <p>
 * 示例：
 * 输入：nums = [5,2,6,1]
 * 输出：[2,1,1,0]
 * 解释：
 * 5 的右侧有 2 个更小的元素 (2 和 1)
 * 2 的右侧仅有 1 个更小的元素 (1)
 * 6 的右侧有 1 个更小的元素 (1)
 * 1 的右侧有 0 个更小的元素
 * <p>
 * 提示：
 * 0 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 */
public class Solution315 {

    public static void main(String[] args) {
        int[] nums = {5, 2, 6, 1};
        //  System.out.println(countSmaller(nums));
        System.out.println(countSmaller1(nums));
    }

    /**
     * 二分+(插入/移动)
     * 时间太长了
     *
     * @param nums
     * @return
     */
    public static List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        //升序
        int length = nums.length;
        int[] numbers = new int[length];
        int size = 0;
        for (int i = length - 1; i >= 0; i--) {
            //二分查找数量
            int k = binarySearch(numbers, 0, size - 1, nums[i]); //表名需要插入的位置
            res.add(k);
            //插入排序 --超时了
          /*  for (int j = size - 1; j >= k; j--) {
                numbers[j + 1] = numbers[j];
            }*/
            //用System.copyArray移动吧
            System.arraycopy(numbers, k, numbers, k + 1, size - k);
            numbers[k] = nums[i];
            size++;
        }
        Collections.reverse(res);
        return res;
    }

    private static int binarySearch(int[] numbers, int l, int r, int value) {
        int mid;
        while (l <= r) {
            mid = (l + r) / 2;
            if (numbers[mid] >= value) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        //找到最近的大于值的下标
        return l;
    }

    //////////////////////////////////////////////

    /**
     * 归并排序时 合并时处理
     */
    public static List<Integer> countSmaller1(int[] nums) {
        int[][] numbers = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            numbers[i][0] = i;
            numbers[i][1] = nums[i];
        }
        int[] res = new int[nums.length];
        countSmaller(numbers, 0, nums.length, res);
        return Arrays.stream(res).boxed().collect(Collectors.toList());
    }

    public static void countSmaller(int[][] nums, int l, int r, int[] res) {
        if (r - l < 2) {
            return;
        }
        int middle = (l + r) / 2;
        countSmaller(nums, l, middle, res);
        countSmaller(nums, middle, r, res);
        merge(nums, l, middle, r, res);
    }

    private static void merge(int[][] nums, int l, int middle, int r, int[] res) {
        //两个排序好的数据,
        int[][] result = new int[r - l][];
        int i = 0;
        int ml = l;
        int mr = middle;
        while (ml < middle && mr < r) {
            if (nums[ml][1] > nums[mr][1]) {
                result[i++] = nums[ml];
                //仅仅需要关注右边就好了
                res[nums[ml][0]] += (r - mr);
                ml++;
            } else {
                result[i++] = nums[mr++];
            }
        }
        for (; ml < middle; ml++) {
            result[i++] = nums[ml];
        }
        for (; mr < r; mr++) {
            result[i++] = nums[mr];
        }
        System.arraycopy(result, 0, nums, l, result.length);
    }


    /**
     * {@link com.welph.leecode.algorithm.thinking.SegmentTree_14}
     * 线段树,  从右向左移动, 每一次动, 对当前值及子节点(比当前值大)的进行更新.
     * 树状数组 todo
     * 二叉搜索树  todo
     */
    public static List<Integer> countSmaller100(int[] nums) {
        List<Integer> res = new ArrayList<>();
        //todo
        return res;
    }

}
