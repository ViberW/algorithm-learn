package com.welph.leecode.part_1_500.part_321_340;

import java.util.Arrays;

/**
 * 给定一个非负整数num。对于0 ≤ i ≤ num 范围中的每个数字i，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * <p>
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,1]
 * <p>
 * 示例2:
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * <p>
 * 进阶:
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的__builtin_popcount）来执行此操作。
 */
public class Solution338 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(countBits(10)));
        System.out.println(Arrays.toString(countBits(2)));
        System.out.println(Arrays.toString(countBits(1)));
        System.out.println(Arrays.toString(countBits(0)));
    }

    public static int[] countBits(int n) {
        int[] ans = new int[n + 1];
        ans[0] = 0;
        if (n == 0) {
            return ans;
        }
        int max = 0;
        int val = 0;
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[val++] + 1;
            if (ans[i] > max) {
                max++;
                val = 0;
            }
        }
        return ans;
    }
}
