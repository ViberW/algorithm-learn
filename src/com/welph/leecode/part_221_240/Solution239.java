package com.welph.leecode.part_221_240;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 * <p>
 * 示例 1：
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * .1 [3  -1  -3] 5  3  6  7       3
 * .1  3 [-1  -3  5] 3  6  7       5
 * .1  3  -1 [-3  5  3] 6  7       5
 * .1  3  -1  -3 [5  3  6] 7       6
 * .1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * 示例 2：
 * 输入：nums = [1], k = 1
 * 输出：[1]
 * <p>
 * 示例 3：
 * 输入：nums = [1,-1], k = 1
 * 输出：[1,-1]
 * <p>
 * 示例 4：
 * 输入：nums = [9,11], k = 2
 * 输出：[11]
 * <p>
 * 示例 5：
 * 输入：nums = [4,-2], k = 2
 * 输出：[4]
 * <p>
 * 提示：
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 */
public class Solution239 {

    public static void main(String[] args) {
        int[] nums = {1, -1};
        System.out.println(Arrays.toString(maxSlidingWindow(nums, 1)));
    }

    //尴尬: 时间: 5.01%   空间:30.56%   也算是自己的思路吧
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int[] res = new int[len - k + 1];
        int reK = k - 1;

        //使用最大栈  每次获取最大栈的最大值, 并去除最前一个值, 添加当前值
        TreeMap<Integer, Integer> tree = new TreeMap<>();
        int num;
        for (int i = 0; i < len; i++) {
            num = nums[i];
            tree.put(num, tree.getOrDefault(num, 0) + 1);
            if (i >= reK) {
                if (i - k >= 0) {
                    num = nums[i - k];
                    int orDefault = tree.getOrDefault(num, 0) - 1;
                    if (orDefault <= 0) {
                        tree.remove(num);
                    } else {
                        tree.put(num, orDefault);
                    }
                }
                //....
                res[i - reK] = tree.lastKey();
            }
        }
        return res;
    }

    /**
     * 官方的题解:
     * 1. 滑动窗口  --- 类似我这的treeMap  使用了PriorityQueue来代替
     * 2.单调队列
     * 3.分块+预处理
     */

    /**
     * 单调队列 ..  想象nums[i] 为k-j范围内的最大值,  此时向右移动一位, 仅仅需要比较num[j]与num[i]的大小关系
     * <p>
     * 只需要保证到达 j 处时, 单调队列中存在大于num[j]的数据值即可.
     */
    public static int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i < k; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }

    /**
     * 分块+预处理   --- 牛
     * <p>
     * 1. 如果 i 是 k 的倍数，那么 nums[i] 到 nums[i+k−1]
     * 恰好是一个分组。我们只要预处理出每个分组中的最大值，即可得到答案；
     * 2.如果 i不是 k 的倍数，那么 nums[i] 到 nums[i+k−1]
     * 会跨越两个分组，占有第一个分组的后缀以及第二个分组的前缀。
     * 假设 j 是 k 的倍数，并且满足 i < j≤i+k−1，
     * 那么 nums[i] 到 nums[j−1] 就是第一个分组的后缀，nums[j] 到 nums[i+k−1] 就是第二个分组的前缀。
     * 如果我们能够预处理出每个分组中的前缀最大值以及后缀最大值，同样可以在 O(1)O(1) 的时间得到答案。
     */
    /**
     * .prefixMax[i] :
     * .    1. nums[i]   i是k的倍数
     * .    2. max(prefixMax[i-1], nums[i])
     * .suffixMax[i] :
     * .    1. nums[i]   i+1是k的倍数
     * .    2.max(suffixMax[i+1], nums[i])
     * <p>
     * ////  i ... (suffix) (prefix)... i+k-1
     */
    public static int[] maxSlidingWindow3(int[] nums, int k) {
        int n = nums.length;
        int[] prefixMax = new int[n];
        int[] suffixMax = new int[n];
        for (int i = 0; i < n; ++i) {
            if (i % k == 0) {
                prefixMax[i] = nums[i];
            } else {
                prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            if (i == n - 1 || (i + 1) % k == 0) {
                suffixMax[i] = nums[i];
            } else {
                suffixMax[i] = Math.max(suffixMax[i + 1], nums[i]);
            }
        }

        int[] ans = new int[n - k + 1];
        for (int i = 0; i <= n - k; ++i) {
            ans[i] = Math.max(suffixMax[i], prefixMax[i + k - 1]);
        }
        return ans;
    }
}
