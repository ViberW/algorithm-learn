package com.welph.leecode.part_1_500.part_441_460;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * 给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，
 * 并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
 * 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,4]
 * 输出：false
 * 解释：序列中不存在 132 模式的子序列。
 * <p>
 * 示例 2：
 * 输入：nums = [3,1,4,2]
 * 输出：true
 * 解释：序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
 * <p>
 * 示例 3：
 * 输入：nums = [-1,3,2,0]
 * 输出：true
 * 解释：序列中有 3 个 132 模式的的子序列：[-1, 3, 2]、[-1, 3, 0] 和 [-1, 2, 0] 。
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 2 * 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class Solution456 {

    public static void main(String[] args) {
        System.out.println(find132pattern(new int[] { 1, 2, 3, 4 }));
        System.out.println(find132pattern(new int[] { 3, 1, 4, 2 }));
        System.out.println(find132pattern(new int[] { -1, 3, 2, 0 }));
    }

    /**
     * i < j < k
     * nums[i] < nums[k] < nums[j]
     * -----------并不一定是连续的: 凸
     * -----------没有思考出来 todo 官方题解思路
     * //固定j , 此时记录[0,j-1]范围内的最小值作为i
     * 那么只需要从[j+1, n-1]范围内是否存在nums[i] < nums[k] < nums[j]
     */
    public static boolean find132pattern(int[] nums) {
        int length = nums.length;
        if (length < 3) {
            return false;
        }
        int numi = nums[0];
        TreeMap<Integer, Integer> tree = new TreeMap<>();
        for (int i = 2; i < length; i++) {
            tree.put(nums[i], tree.getOrDefault(nums[i], 0) + 1);
        }
        for (int j = 1; j < length - 1; j++) {
            if (nums[j] > numi + 1) {
                // 查找j的右侧数据是否存在大于numi的最小值,
                Integer numk = tree.ceilingKey(numi + 1);
                if (numk != null && numk < nums[j]) {
                    return true;
                }
            }
            if (nums[j] < numi) {
                numi = nums[j];
            }
            Integer integer = tree.get(nums[j + 1]);
            if (integer == 1) {
                tree.remove(nums[j + 1]);
            } else {
                tree.put(nums[j + 1], integer - 1);
            }
        }
        return false;
    }

    public boolean find132pattern1(int[] nums) {
        int n = nums.length;
        // 单调栈(减)
        Deque<Integer> candidateK = new LinkedList<Integer>();
        candidateK.push(nums[n - 1]);
        int maxK = Integer.MIN_VALUE;

        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] < maxK) { // 上一次遍历找到的maxK的结果
                return true;
            }
            // 这里相当于是将i 放入单调减栈中, maxK 则能够代表 j<k 且nums[k]<nums[j]
            while (!candidateK.isEmpty() && nums[i] > candidateK.peek()) {
                // 这一步相当于是不断抬高k和j的值,这样i的可选阈值就提高
                maxK = candidateK.pop();
            }
            // 这里结合上面抬高k和j来看, 相当于是保证栈中数值不会减少
            if (nums[i] > maxK) {
                candidateK.push(nums[i]);// 保存maxK
            }
        }
        return false;
    }

    // 有时间再看
    public boolean find132pattern2(int[] nums) {
        int n = nums.length;
        List<Integer> candidateI = new ArrayList<Integer>();
        candidateI.add(nums[0]);
        List<Integer> candidateJ = new ArrayList<Integer>();
        candidateJ.add(nums[0]);

        for (int k = 1; k < n; ++k) {
            int idxI = binarySearchFirst(candidateI, nums[k]);
            int idxJ = binarySearchLast(candidateJ, nums[k]);
            if (idxI >= 0 && idxJ >= 0) {
                if (idxI <= idxJ) {
                    return true;
                }
            }

            if (nums[k] < candidateI.get(candidateI.size() - 1)) {
                candidateI.add(nums[k]);
                candidateJ.add(nums[k]);
            } else if (nums[k] > candidateJ.get(candidateJ.size() - 1)) {
                int lastI = candidateI.get(candidateI.size() - 1);
                while (!candidateJ.isEmpty() && nums[k] > candidateJ.get(candidateJ.size() - 1)) {
                    candidateI.remove(candidateI.size() - 1);
                    candidateJ.remove(candidateJ.size() - 1);
                }
                candidateI.add(lastI);
                candidateJ.add(nums[k]);
            }
        }

        return false;
    }

    public int binarySearchFirst(List<Integer> candidate, int target) {
        int low = 0, high = candidate.size() - 1;
        if (candidate.get(high) >= target) {
            return -1;
        }
        while (low < high) {
            int mid = (high - low) / 2 + low;
            int num = candidate.get(mid);
            if (num >= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public int binarySearchLast(List<Integer> candidate, int target) {
        int low = 0, high = candidate.size() - 1;
        if (candidate.get(low) <= target) {
            return -1;
        }
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            int num = candidate.get(mid);
            if (num <= target) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }
        return low;
    }

}
