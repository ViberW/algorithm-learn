package com.welph.leecode.part_1_500.part_481_500;

import java.util.Arrays;

/**
 * 神奇字符串 s 仅由 '1' 和 '2' 组成，并需要遵守下面的规则：
 * <p>
 * 神奇字符串 s 的神奇之处在于，串联字符串中 '1' 和 '2' 的连续出现次数可以生成该字符串。
 * s 的前几个元素是 s = "1221121221221121122……" 。如果将 s 中连续的若干 1 和 2 进行分组，
 * 可以得到 "1 22 11 2 1 22 1 22 11 2 11 22 ......" 。
 * 每组中 1 或者 2 的出现次数分别是 "1 2 2 1 1 2 1 2 2 1 2 2 ......" 。上面的出现次数正是 s 自身。
 * <p>
 * 给你一个整数 n ，返回在神奇字符串 s 的前 n 个数字中 1 的数目。
 * <p>
 * 示例 1：
 * 输入：n = 6
 * 输出：3
 * 解释：神奇字符串 s 的前 6 个元素是 “122112”，它包含三个 1，因此返回 3 。
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * <p>
 * 提示：
 * 1 <= n <= 10^5
 */
public class Solution481 {

    public static void main(String[] args) {
        System.out.println(magicalString(19));
    }

    /**
     * 用于数组保存每个点, 双指针指向前后的值
     */
    public static int magicalString(int n) {
        if (n <= 3) {
            return 1;
        }
        int[] nums = new int[n];
        nums[0] = 1;
        nums[1] = 2;
        nums[2] = 2;
        int left = 2; // 存储下一批次应该有多少重复的1或2
        int right = 3; // 存储下一次数据应该从哪里开始存储
        int ret = 1;
        int count;
        int val;
        LABEL: for (; right < n;) {
            count = nums[left];
            val = nums[right - 1] == 1 ? 2 : 1; // 和上一次相反的数组并存储count的次
            if (val == 1) {
                ret += Math.min(count, n - right);
            }
            for (int i = 0; i < count; i++) {
                if (right >= n) {
                    break LABEL;
                }
                nums[right++] = val;
            }
            left++;
        }
        return ret;
    }
}
