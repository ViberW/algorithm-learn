package com.welph.leecode.part_1_500.part_381_400;

import java.util.Arrays;

/**
 * 给定一个长度为 n 的整数数组 A 。
 * 假设 Bk 是数组 A 顺时针旋转 k 个位置后的数组，我们定义 A 的“旋转函数” F 为：
 * <p>
 * F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1]。
 * 计算F(0), F(1), ..., F(n-1)中的最大值。
 * <p>
 * 注意:
 * 可以认为 n 的值小于 105。
 * <p>
 * 示例:
 * A = [4, 3, 2, 6]
 * <p>
 * F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
 * F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
 * F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
 * F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
 * <p>
 * 所以 F(0), F(1), F(2), F(3) 中的最大值是 F(3) = 26
 */
public class Solution396 {

    public static void main(String[] args) {
        System.out.println(maxRotateFunction(new int[] { 4, 3, 2, 6 }));
    }

    /**
     * f{n} = f{n-1}+(0~n-2)的总值-(n-1)*(最后一位的值)
     * f(1) 为
     */
    public static int maxRotateFunction(int[] nums) {
        int length = nums.length;
        int sum = 0;
        int pre = 0;
        for (int i = 0; i < length; i++) {
            sum += nums[i];
            pre += i * nums[i];
        }
        int ans = pre;
        int cur;
        int k = length - 1;
        for (int i = 1; i < length; i++, k--) {
            cur = pre + sum - length * nums[k];
            ans = Math.max(ans, cur);
            pre = cur;
        }
        return ans;
    }

    /* 官方题解 */
    public int maxRotateFunction2(int[] nums) {
        int f = 0, n = nums.length, numSum = Arrays.stream(nums).sum();
        for (int i = 0; i < n; i++) {
            f += i * nums[i];
        }
        int res = f;
        for (int i = n - 1; i > 0; i--) { // 代码简化
            f += numSum - n * nums[i];
            res = Math.max(res, f);
        }
        return res;
    }
}
