package com.welph.leecode.part_1_500.part_81_100;

import java.util.*;

/**
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,2]
 * 输出:
 * [
 * [2],
 * [1],
 * [1,2,2],
 * [2,2],
 * [1,2],
 * []
 * ]
 */
public class Solution90 {

    public static void main(String[] args) {
        int[] nums = {4, 4, 4, 1, 4};
        int[] nums2 = {1, 1, 2, 2};
        //System.out.println(subsetsWithDup(nums2));
        System.out.println(subsetsWithDup2(nums));
        System.out.println(subsetsWithDup0(nums));
        System.out.println(subsetsWithDup2(nums2));
        System.out.println(subsetsWithDup0(nums2));
    }

    /**
     * 排序后比较好处理
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsetsWithDup0(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<>();
        int subsetNum = 1 << nums.length;
        List<Integer> list;
        for (int i = 0; i < subsetNum; i++) {
            list = new ArrayList<>();
            boolean illegal = false;
            for (int j = 0; j < nums.length; j++) {
                if ((i >> j & 1) == 1) {
                    //若前面没选择 后面也同样不选择,说明跳过当前值
                    if (j > 0 && nums[j] == nums[j - 1] && (i >> (j - 1) & 1) == 0) {
                        illegal = true;
                        break;
                    } else {
                        list.add(nums[j]);
                    }
                }
            }
            if (!illegal) {
                lists.add(list);
            }
        }
        return lists;
    }

    /**
     * 感觉没有排序 则处理不太顺畅。;排序后就可以和78题类似了  暂时没成功。。。
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsetsWithDup2(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>());
        Map<Integer, Integer> exists = new HashMap();
        subsets(subsets, nums, 0, nums.length - 1, exists);
        return subsets;
    }

    public static void subsets(List<List<Integer>> subsets, int[] nums, int index, int end,
                               Map<Integer, Integer> exists) {
        if (index > end) {
            return;
        }
        int num = nums[index];
        if (exists.containsKey(num)) {
            Set<Map.Entry<Integer, Integer>> entries = exists.entrySet();
            for (Map.Entry<Integer, Integer> entry : entries) {
                subsets.add(new ArrayList<Integer>(subsets.get(entry.getValue())) {{
                    add(num);
                }});
                exists.put(entry.getKey(), subsets.size() - 1);
            }
        } else {
            int size = subsets.size();
            for (int j = 0; j < size; j++) {
                subsets.add(new ArrayList<Integer>(subsets.get(j)) {{
                    add(num);
                }});
            }
            exists.put(num, subsets.size() - 1);
        }
        subsets(subsets, nums, index + 1, end, exists);
    }

  /*  public static List<List<Integer>> subsetsWithDup(int[] nums) {
        int length = nums.length;
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>());
        LinkedList<Integer> indexList = new LinkedList<>();
        //按照第一次循环的顺序认定大小
        Set<Integer> exists;
        indexList.add(-1);
        int num;
        int size;
        List<Integer> integers;
        int subIndex = 0;
        List<Integer> subset;
        for (int l = 1; l <= length; l++) {
            size = subsets.size();
            for (int i = subIndex; i < size; i++) {
                exists = new HashSet<>();
                subset = subsets.get(i);
                for (int j = indexList.removeFirst() + 1; j < length; j++) {
                    num = nums[j];
                    if (!exists.contains(num)
                            && (subset.isEmpty() || num >= subset.get(0))) {
                        integers = new ArrayList<>(subset);
                        integers.add(num);
                        subsets.add(integers);
                        indexList.addLast(j);
                        exists.add(num);
                    }
                }
            }
            subIndex = size;
        }
        return subsets;
    }*/

}
